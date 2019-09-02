package com.zxtech.ess.module.pub.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zxtech.ess.module.base.bean.BaseElevatorSearchBean;
import com.zxtech.ess.module.gen.bean.BaseMachineType;
import com.zxtech.ess.module.gen.bean.QueryDefinition;
import com.zxtech.ess.module.gen.bean.QueryItem;
import com.zxtech.ess.module.gen.bean.SysRole;
import com.zxtech.ess.module.pub.bean.PubSearchBean;
import com.zxtech.platform.vo.ResultBean;
import com.zxtech.platform.vo.UserBean;

public interface IPubService {

	List<SysRole> fetchPublicSysRole();
	
	/**
     * 用户机构select
     * @return
     * @author: syp637
     */
    public List<Map<String, Object>> getListByCompany(UserBean user, String combobox_type);
    
    /**
     * 获取流水单号
     * 原生成格式废弃,现改为32进制字符串,生成各模块单号无需参数, lanb 2018/11/08 
     * 
     * @return
     * @author: syp637
     */
    public String getSequenceOrder();
    
    /**
     * 公共地盘列表
     * @param queryInfo
     * @param user
     * @return
     * @author: syp637
     */
    public Map<String, Object> getCommonProjectList(PubSearchBean queryInfo, UserBean user);

    public List<Map<String, Object>> fetchQueryHelperComboboxList(String query_url, String empCode);

    public List<QueryItem> fetchQueryHelperItems(String query_url);

    public String saveQueryDefinition(QueryDefinition bean, UserBean user);

    public String deleteQueryDefinition(QueryDefinition bean, UserBean user);
	
    public void saveLogData(UserBean user, String opt_behavior);
	
    public void saveLogData(UserBean user, String opt_behavior, String method_url, Integer rec_id);
	
    public Map<String, Object> importCheck(MultipartFile mf, UserBean user) throws Exception;
	
    /**
     * 获取热线用户
     * @return
     * @author: syp637
     */
    public List<Map<String, Object>> getHotlineUserList();
    
    /**
     * 机器分类
     * @param bean
     * @return
     * @author: syp637
     */
    public List<BaseMachineType> fetchPublicMachineType(BaseMachineType bean);
    
    public Map<String, Object> getCommonElevatorPagingList(BaseElevatorSearchBean queryInfo);

    ResultBean fetchExportExcelSchedule(String guid);
}
