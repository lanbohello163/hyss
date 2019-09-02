package com.zxtech.platform.utils.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.types.Expiration;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxtech.ess.app.util.SysTools;
import com.zxtech.platform.constant.ResultConstants;
import com.zxtech.platform.utils.DateUtil;
import com.zxtech.platform.utils.GlobalFileUtil;
import com.zxtech.platform.utils.LimitApplicationRequestUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.cache.CacheTemplate;
import com.zxtech.platform.utils.page.PageFetchNext;
import com.zxtech.platform.utils.page.PageParameter;
import com.zxtech.platform.vo.ResultBean;

public class EasyExcelUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(EasyExcelUtil.class);

	public static final String EXPORT_GUID_HEAD = "export_guid_";
	
	// 导出数据量最大限制条数
	public static final int MAX_COUNT = 1_000_000;
	// 每页导出数据量，excel每个sheet数据量设置为此值的2倍，即200000
	public static final int PAGE_SIZE = 100_000;
	// 启用限流的数据量
	public static final int LIMIT_BIG_DATA = 10_000;
	// 默认下载进度
	public static final int DEFAULT_DOWNLOAD_PROGRESS = 5;

	/**
	 * @param FetchCount 总数
	 * @param fetchFunc  查询
	 * @param param      queryInfo
	 * @return ResultBean data->guid
	 */
	public static <T extends PageParameter> ResultBean writeExcelMoreSheetMoreWrite(ExportExcelFetchCount fetchCount,
			PageFetchNext fetchFunc, T param) {
		ResultBean resultBean = new ResultBean();
		String guidFile = SysTools.getGUID();
		String exportGuid = EXPORT_GUID_HEAD + guidFile;
		try {
			Objects.requireNonNull(fetchCount);
			Objects.requireNonNull(fetchFunc);
			Objects.requireNonNull(param.getDgFields());
			Objects.requireNonNull(param.getDgColumnTitles());
			long totalRowCount = fetchCount.getExportExcelFetchCount();
			if (totalRowCount > MAX_COUNT) {
				resultBean.setStatus(ResultConstants.OVER_MAX_CODE);
				resultBean.setMsg(ResultConstants.OVER_MAX_NAME);
				return resultBean;
			}
			if (totalRowCount == 0) {
				resultBean.setStatus(ResultConstants.WITHOUT_DATA_CODE);
				resultBean.setMsg(ResultConstants.WITHOUT_DATA_NAME);
				return resultBean;
			}
			if (totalRowCount > LIMIT_BIG_DATA && !LimitApplicationRequestUtil.checkLimitApplicationRequest(guidFile)) {
				resultBean.setStatus(ResultConstants.WAIT_CODE);
				resultBean.setMsg(ResultConstants.WAIT_NAME);
				return resultBean;
			}

			ResultBean fileResultBean = new ResultBean();
			fileResultBean.setData(DEFAULT_DOWNLOAD_PROGRESS);
			CacheTemplate.REDIS.execute((RedisConnection conn) -> {
				conn.set( exportGuid.getBytes(),fileResultBean.toString().getBytes(), Expiration.milliseconds(3_600), null);
				return null;
			});
			long writeCount = totalRowCount % EasyExcelUtil.PAGE_SIZE == 0 ? (totalRowCount / EasyExcelUtil.PAGE_SIZE)
					: (totalRowCount / EasyExcelUtil.PAGE_SIZE + 1);
			param.setPageQeury(true);
			param.setRows(EasyExcelUtil.PAGE_SIZE);
			new Thread(() -> {
				try (OutputStream out = new FileOutputStream(
						GlobalFileUtil.getDownloadPath() + File.separator + guidFile + ".xlsx")) {
					Table table = new Table(1);
					
					Gson gson = new Gson();
					List<List<String>> titles = new ArrayList<>();
					if ("1".equals(param.getDgMultiTitles())) {
						titles = gson.fromJson(param.getDgColumnTitles(), new TypeToken<List<List<String>>>() {}.getType());
					} else {
						List<String> showArr = gson.fromJson(param.getDgColumnTitles(), new TypeToken<List<String>>() {}.getType());
						titles = showArr.stream().map((str) -> Arrays.asList(str))
								.collect(Collectors.toList());
					}
					table.setHead(titles);
					List<String> nameArr = gson.fromJson(param.getDgFields(), new TypeToken<List<String>>() {}.getType());
					int sheetNum = 0;
					ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
					for (int i = 1; i <= writeCount; i++) {
						if (i % 2 == 1)
							sheetNum = sheetNum + 1;
						Sheet sheet = new Sheet(sheetNum, 0);
						sheet.setSheetName("sheet" + sheetNum);
						param.setPage(i);
						List<List<String>> userList = new ArrayList<>();
						List<Map<String, Object>> mapList = fetchFunc.getPageFetchNext();
						for (Map<String, Object> map : mapList) {
							List<String> l = nameArr.stream().map((str) -> {
								if (map.get(str) != null && map.get(str) instanceof java.sql.Timestamp) {
									return DateUtil.toSimpleTime(map.get(str));
								}
								return StringUtil.toSafeString(map.get(str));
							}).collect(Collectors.toList());
							userList.add(l);
						}
						writer.write0(userList, sheet, table);
						long schedule = ((100 / writeCount) * i);
						fileResultBean.setData(schedule == 100 ? 95 : schedule);
						CacheTemplate.REDIS.execute((RedisConnection conn) -> {
							conn.set( exportGuid.getBytes(),fileResultBean.toString().getBytes(), Expiration.milliseconds(3_600), null);
							return null;
						});
					}
					writer.finish();
					out.close();
					fileResultBean.setData(100);
					CacheTemplate.REDIS.execute((RedisConnection conn) -> {
						conn.set( exportGuid.getBytes(),fileResultBean.toString().getBytes(), Expiration.milliseconds(3_600), null);
						return null;
					});
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
					fileResultBean.setStatus(ResultConstants.ERROR_CODE);
					fileResultBean.setMsg(ResultConstants.ERROR_NAME);
					CacheTemplate.REDIS.execute((RedisConnection conn) -> {
						conn.set( exportGuid.getBytes(),fileResultBean.toString().getBytes(), Expiration.milliseconds(3_600), null);
						return null;
					});
				} finally {
					LimitApplicationRequestUtil.freeOneLimit(guidFile);
				}
			}).start();
			resultBean.setData(guidFile);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			resultBean.setStatus(ResultConstants.ERROR_CODE);
			resultBean.setMsg(ResultConstants.ERROR_NAME);
			LimitApplicationRequestUtil.freeOneLimit(guidFile);
		}
		return resultBean;
	}
	
	/**
	 * @param dgFields 表头对应字段名
	 * @param dgColumnTitles 表头
	 * @param param searchBean
	 * @return searchBean
	 */
	public static <T extends PageParameter> T writeExcelTitle(String[] dgFields, String[] dgColumnTitles, T param) {
		Gson gson = new Gson();
		param.setDgFields(gson.toJson(dgFields).toString());
		param.setDgColumnTitles(gson.toJson(dgColumnTitles).toString());
		return param;
	}

}
