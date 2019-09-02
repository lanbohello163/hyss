package com.zxtech.platform.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Title: 日期操作的工具类<br>
 * Description: 对日期对象或者字符串处理的工具类，开发人员优先使用该日期工具类完成日期相关操作。如该工具不能完成的需求，可以使用第三方commons-lang中的DateUtils<br>
 * Date: 2012-08-01<br>
 * Modify-Date:2012-08-01<br>
 * Copyright (c) 2012 zxtech <br>
 * 
 * @version 1.0
 * @author 
 */
public class DateUtil {

	public static final String DATE_STRING = "yyyy-MM-dd";
	
	public static final String MONTH_STRING = "yyyy-MM";
	
	public static final String DATETIME_STRING = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DAY_START_TIME = " 00:00:00";
	
	public static final String DAY_END_TIME = " 23:59:59";

	public static final String DAY_BEGIN_START = "1970-01-01";
	
	public static final String DAY_END_START = "2100-01-01";
	
	private static final int DATE_OFFSET = 10;

	private DateUtil() {
	}

	/**
	 * 将java.util.Date对象转换为java.sql.Date类型
	 * 
	 * @param java.util.Date date，需要转换的日期对象
	 * @return java.sql.Date  返回转换后的日期对象
	 * 
	 * @CreateTime: 2012-8-2
	 * 
	 * */
	public static java.sql.Date utilDate2SqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 将java.sql.Date对象转换为java.util.Date类型
	 * 
	 * @param java.sql.Date date，需要转换的日期对象
	 * @return java.util.Date 转换后的日期对象
	 * 
	 * @CreateTime: 2012-8-2
	 * */
	public static java.util.Date sqlDate2UtilDate(java.sql.Date date) {
		return new java.util.Date(date.getTime());
	}

	/**
	 * 将日期对象根据格式字符串参数的定义转换成字符串
	 * 
	 * @param Date currentDate，需要转换的日期对象 
	 * @param String format，需要转换的格式
	 * @return String 根据日期对象转换成字符串
	 * 
	 * @CreateTime: 2012-8-1
	 * */
	public static String date2String(Date currentDate) {
		return date2String(currentDate, DATE_STRING);
	}
	
	public static String date2String(Date currentDate, String formate) {
		String result = null;
		SimpleDateFormat formatdater = new SimpleDateFormat(formate);
		result = formatdater.format(currentDate);
		return result;
	}

	/**
	 * 将日期字符串转换成Date日期对象，需要给定字符串时间格式
	 * 
	 * @param String currentDate，需要转换的字符串 
	 * @param String format，需要转换的格式
	 * @return Date 转换后的日期对象
	 * 
	 * @throws ParseException
	 * @CreateTime: 2012-8-1
	 * */
	public static Date string2Date(String currentDate) throws ParseException {
		return string2Date(currentDate, DATE_STRING);
	}
	public static Date string2Date(String currentDate, String formate) throws ParseException {
		Date result = null;
		SimpleDateFormat formatdater = new SimpleDateFormat(formate);
		result = formatdater.parse(currentDate);
		return result;
	}

	/**
	 * 对日期进行按天加减操作
	 * 
	 * @param Date date，需要进行计算的日期 
	 * @param int offset，加减的偏移量
	 * @return Date 返回计算后的日期
	 * 
	 * @CreateTime: 2011-7-11
	 * @ModifyTime: 2012-8-1
	 * */
	public static Date changeDay(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, (calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return calendar.getTime();
	}

	/**
	 * 对日期进行按天加减操作，并按给定格式格式化成字符串
	 * 
	 * @param Date date，需要进行计算的日期 
	 * @param int offset，加减的偏移量 
	 * @param String format，需要格式化的格式
	 * @return String 计算后并格式化成对应的字符串
	 * 
	 * @CreateTime: 2011-7-11
	 * @ModifyTime: 2012-8-1
	 * */
	public static String changeDay(Date date, int offset, String formate) {
		SimpleDateFormat formatdater = new SimpleDateFormat(formate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, (calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return formatdater.format(calendar.getTime());
	}

	/**
	 * 对日期进行按天加减操作，并按给定格式格式化成字符串
	 * 
	 * @param String date，需要进行计算的日期字符串 
	 * @param int offset，加减的偏移量
	 * @param String format，需要格式化的格式
	 * @return String 计算后并格式化成对应的字符串
	 * 
	 * @CreateTime: 2011-7-11
	 * @ModifyTime: 2012-8-1
	 * */
	public static String changeDay(String date, int offset, String formate) throws ParseException {
		SimpleDateFormat formatdater = new SimpleDateFormat(formate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatdater.parse(date));
		calendar.set(Calendar.DAY_OF_YEAR, (calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return formatdater.format(calendar.getTime());
	}

	/**
	 * 对日期进行按小时加减操作
	 * 
	 * @param Date datetime，需要进行计算的日期 
	 * @param int offset，加减的偏移量
	 * @return Date 计算后的日期
	 * 
	 * @CreateTime: 2011-7-11
	 * @ModifyTime: 2012-8-1
	 * */
	public static Date changeHour(Date datetime, int offset)
			throws ParseException
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datetime);
		calendar.add(Calendar.HOUR, offset);
		return calendar.getTime();
	}

	/**
	 * 对日期进行按小时加减操作，并按yyyy-MM-dd HH:mm:ss格式格式化成字符串
	 * 
	 * @param String datetime，需要进行计算的日期字符串 
	 * @param int offset，加减的偏移量
	 * @return String 计算后并格式化成对应的字符串
	 * 
	 * @CreateTime: 2011-7-11
	 * @ModifyTime: 2012-8-1
	 * */
	public static String changeHour(String datetime, int offset)
			throws ParseException
	{
		SimpleDateFormat formatdater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatdater.parse(datetime));
		calendar.add(Calendar.HOUR, offset);
		return formatdater.format(calendar.getTime());
	}

	/**
	 * 对日期进行按分钟加减操作，并按yyyy-MM-dd HH:mm:ss格式格式化成字符串
	 * 
	 * @param String datetime，需要进行计算的日期字符串
	 * @param int offset，加减的偏移量
	 * @return String 计算后并格式化成对应的字符串
	 * 
	 * @CreateTime: 2011-7-11
	 * @ModifyTime: 2012-8-1
	 * */
	public static String changeMinute(String datetime, int offset)
			throws ParseException
	{
		SimpleDateFormat formatdater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatdater.parse(datetime));
		calendar.add(Calendar.MINUTE, offset);
		return formatdater.format(calendar.getTime());
	}

	/**
	 * 对日期进行按分钟加减操作
	 * 
	 * @param Date datetime，需要进行计算的日期 
	 * @param int offset，加减的偏移量
	 * @return Date 计算后的日期
	 * 
	 * @CreateTime: 2011-7-11
	 * @ModifyTime: 2012-8-1
	 * */
	public static Date changeMinute(Date datetime, int offset)
			throws ParseException
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datetime);
		calendar.add(Calendar.MINUTE, offset);
		return calendar.getTime();
	}

	/**
	 * 对日期进行按秒加减操作，并按yyyy-MM-dd HH:mm:ss格式格式化成字符串
	 * 
	 * @param String datetime，需要进行计算的日期字符串 
	 * @param int offset，加减的偏移量
	 * @return String 计算后并格式化成对应的字符串
	 * 
	 * @CreateTime: 2011-7-11
	 * @ModifyTime: 2012-8-1
	 * */
	public static String changeSecond(String datetime, int offset)
			throws ParseException
	{
		SimpleDateFormat formatdater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatdater.parse(datetime));
		calendar.add(Calendar.SECOND, offset);
		return formatdater.format(calendar.getTime());
	}

	/**
	 * 对日期进行按秒加减操作
	 * 
	 * @param Date datetime，需要进行计算的日期
	 * @param int offset，加减的偏移量
	 * @return Date 计算后的日期
	 * 
	 * @CreateTime: 2011-7-11
	 * @ModifyTime: 2012-8-1
	 * */
	public static Date changeSecond(Date datetime, int offset)
			throws ParseException
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datetime);
		calendar.add(Calendar.SECOND, offset);
		return calendar.getTime();
	}

	/**
	 * 对YYYY-MM-dd格式的日期字符串加入00:00:00的时间信息
	 * 
	 * @param String beginDate，需要加入时间信息的字符串
	 * @return String 加入时间字符串信息后返回
	 * 
	 * @CreateTime: 2012-3-30
	 * @ModifyTime: 2012-8-1
	 * */
	public static String getBeginDate(String beginDate) throws ParseException
	{
		String result = null;
		if (!StringUtil.isBlank(beginDate))
		{
			result = beginDate + " 00:00:00";
		}
		return result;
	}

	/**
	 * 对YYYY-MM-dd格式的日期字符串加入23:59:59的时间信息
	 * 
	 * @param String endDate，需要加入时间信息的字符串
	 * @return String 加入时间字符串信息后返回
	 * 
	 * @CreateTime: 2012-3-30
	 * @ModifyTime: 2012-8-1
	 * */
	public static String getEndDate(String endDate) throws ParseException
	{
		String result = null;
		if (!StringUtil.isBlank(endDate))
		{
			result = endDate + " 23:59:59";
		}
		return result;
	}

	/**
	 * 对时间长字符串yyyy-MM-dd HH:mm:ss进行截断，只返回年月日的字符串
	 * 
	 * @param String datetime，需要获取年月日信息的时间长串
	 * @return String 返回截取年月日信息后的字符串
	 * 
	 * @CreateTime: 2012-3-30
	 * @ModifyTime: 2012-8-1
	 * */
	public static String dateString2NormalDateString(String datetime)
			throws ParseException
	{
		String result = null;
		if (!StringUtil.isBlank(datetime))
		{
			result = datetime.substring(0, DATE_OFFSET);
		}
		return result;

	}

	/**
	 * 对日期进行格式化处理，返回只含有年月日信息的字符串yyyy-MM-dd
	 * 
	 * @param Date date，需要格式化成yyyy-MM-dd格式的日期对象
	 * @return String 格式化后的字符串
	 * 
	 * @CreateTime: 2012-3-30
	 * @ModifyTime: 2012-8-1
	 * */
	public static String dateTime2NormalDateString(Date date)
			throws ParseException
	{
		String result = null;
		String datetime = date2String(date, "yyyy-MM-dd");
		if (!StringUtil.isBlank(datetime))
		{
			result = datetime.substring(0, DATE_OFFSET);
		}
		return result;

	}

	/**
	 * 对日期进行格式化处理，返回只含有年月日信息的字符串yyyy-MM-dd HH:mm:ss
	 * 
	 * @param Date date，需要格式化成yyyy-MM-dd HH:mm:ss格式的日期对象
	 * @return String 格式化后的字符串
	 * 
	 * @CreateTime: 2012-3-30
	 * @ModifyTime: 2012-8-1
	 * */
	public static String dateTime2DefaultStr(Date date) {
		String str = "";
		if (null != date) {
			str = date2String(date, "yyyy-MM-dd HH:mm:ss");
		}
		return str;
	}

	/**
	 * 返回日期的星期几的中文字符串，例如返回“星期三”
	 * 
	 * @param Date date，需要处理的日期
	 * @return String 返回日期的星期几的中文字符串
	 * 
	 * @CreateTime: 2012-8-2
	 * @ModifyTime: 2012-8-2
	 * */
	public static String getWeekdayChineseStrByDate(Date date)
	{
		if (date == null)
		{
			return null;
		}
		return new SimpleDateFormat("E").format(date);
	}

	/**
	 * 返回日期的星期几的数字
	 * 
	 * @param int year，年
	 * @param int month，月份 
	 * @param int day，日
	 * @return int 返回日期的星期几的数字
	 * 
	 * @CreateTime: 2012-8-2
	 * @ModifyTime: 2017-11-17
	 * */
	public static int getWeekdayNumberByDate(int year, int month, int day)
	{
		LocalDate ld = LocalDate.of(year, month, day);
		return ld.getDayOfWeek().getValue();
	}

	/**
	 * 返回日期的星期几的数字
	 * 
	 * @param Date date，需要计算的日期对象
	 * @return int 返回日期的星期几的数字
	 * 
	 * @CreateTime: 2012-8-2
	 * @ModifyTime: 2017-11-17
	 * */
	public static int getWeekdayNumberByDate(Date date)
	{
		int year = Integer.valueOf(date2String(date, "yyyy"));
		int month = Integer.valueOf(date2String(date, "MM"));
		int day = Integer.valueOf(date2String(date, "dd"));
		LocalDate ld = LocalDate.of(year, month, day);
		return ld.getDayOfWeek().getValue();
	}

	/**
	 * 返回日期是所在月的第几周
	 * 
	 * @param int year，年
	 * @param int month，月份
	 * @param int day，日
	 * @return int 返回日期是所在月的第几周
	 * 
	 * @CreateTime: 2012-8-2
	 * @ModifyTime: 2017-11-17
	 * */
	public static int getWeekNumByDate(int year, int month, int day)
	{
		int result = 1;

		if (day == 1)
			return 1;

		LocalDate ld = LocalDate.of(year, month, 1);
		int startDay = 7 - ld.getDayOfWeek().getValue() + 1;

		if (day < startDay)
			return 1;
		int count = (day - startDay) / 7;

		if (startDay == 1)
		{
			result = 1 + count;
		} else
		{
			result = 2 + count;
		}
		return result;
	}

	/**
	 * 返回日期是所在月的第几周
	 * 
	 * @param Date date，需要计算的日期对象
	 * @return int 返回日期是所在月的第几周
	 * 
	 * @CreateTime: 2012-8-2
	 * @ModifyTime: 2017-11-17
	 * */
	public static int getWeekNumByDate(Date date)
	{
		int year = Integer.valueOf(date2String(date, "yyyy"));
		int month = Integer.valueOf(date2String(date, "MM"));
		int day = Integer.valueOf(date2String(date, "dd"));

		int result = 1;
		if (day == 1)
			return 1;

		LocalDate ld = LocalDate.of(year, month, 1);
		int startDay = 7 - ld.getDayOfWeek().getValue() + 1;

		if (day < startDay)
			return 1;
		int count = (day - startDay) / 7;

		if (startDay == 1)
		{
			result = 1 + count;
		} else
		{
			result = 2 + count;
		}
		return result;
	}

	/**
	 * 判断当前年是否是闰年
	 * 
	 * @param int year，年
	 * @return boolean 返回是否是闰年
	 * 
	 * @CreateTime: 2012-8-2
	 * @ModifyTime: 2012-8-2
	 * */
	private static boolean isLeapyear(int year)
	{
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}

	/**
	 * 返回当前月的最后一天是几号
	 * 
	 * @param int year，年 
	 * @param int month，月份
	 * @return int 返回当前月的最后一天是几号
	 * 
	 * @CreateTime: 2012-8-2
	 * @ModifyTime: 2012-8-2
	 * */
	public static int getLastdayByMonth(int year, int month)
	{
		int day = 31;
		if (month == 4 || month == 6 || month == 9 || month == 11)
		{
			day = 30;
		}
		if (month == 2)
		{
			day = 28;
			if (isLeapyear(year))
			{
				day = 29;
			}
		}
		return day;
	}

	/**
	 * 返回当前月的最后一天是几号
	 * 
	 * @param Date date，需要处理的日期对象
	 * @return int 返回当前月的最后一天是几号
	 * 
	 * @CreateTime: 2012-8-2
	 * @ModifyTime: 2012-8-2
	 * */
	public static int getLastdayByMonth(Date date)
	{
		int year = Integer.valueOf(date2String(date, "yyyy"));
		int month = Integer.valueOf(date2String(date, "MM"));
		int day = 31;
		if (month == 4 || month == 6 || month == 9 || month == 11)
		{
			day = 30;
		}
		if (month == 2)
		{
			day = 28;
			if (isLeapyear(year))
			{
				day = 29;
			}
		}
		return day;
	}
	
	/**
	 * 返回当前月的最后一天
	 * 
	 * @param String 2018-04，年-月
	 * @return int 返回当前月的最后一天是几号
	 * 
	 * @CreateTime: 2018-04-20
	 * */
	public static String getLastdayByMonth(String date)
	{
		String[] arr = date.split("-");
		Integer year = Integer.valueOf(arr[0]);
		Integer month = Integer.valueOf(arr[1]);
		
		int day = 31;
		if (month == 4 || month == 6 || month == 9 || month == 11)
		{
			day = 30;
		}
		if (month == 2)
		{
			day = 28;
			if (isLeapyear(year))
			{
				day = 29;
			}
		}
		return date + "-" + day;
	}

	/**
	 * 返回给定月份的最后一天的在周中是第几天。 星期一返回1，。。。。。。星期日返回7
	 * 
	 * @param int year，年
	 * @param int month，月份
	 * @return int 返回月份最后一天的在周中是第几天
	 * 
	 * @CreateTime: 2012-8-2
	 * @ModifyTime: 2012-8-2
	 * */
	public static int getWeekdayOfLastDay(int year, int month)
	{
		return getWeekdayNumberByDate(year, month,
				getLastdayByMonth(year, month));
	}

	/**
	 * 返回给定月份的最后一天的在周中是第几天。 星期一返回1，。。。。。。星期日返回7
	 * 
	 * @param Date date，需要处理的日期
	 * @return int 返回月份最后一天的在周中是第几天
	 * 
	 * @CreateTime: 2012-8-2
	 * @ModifyTime: 2012-8-2
	 * */
	public static int getWeekdayOfLastDay(Date date)
	{
		int year = Integer.valueOf(date2String(date, "yyyy"));
		int month = Integer.valueOf(date2String(date, "MM"));
		return getWeekdayNumberByDate(year, month,
				getLastdayByMonth(year, month));
	}

	/**
	 * 返回当前月一共包括几周。有可能返回5，例如2012年5月就包含5周
	 * 
	 * @param int year，年 
	 * @param int month，月份
	 * @return int 返回当前月份中一共包含几周
	 * 
	 * @CreateTime: 2012-8-1
	 * @ModifyTime: 2012-8-1
	 * */
	public static int getWeekCountByMonth(int year, int month)
	{
		int day = getLastdayByMonth(year, month);
		return getWeekNumByDate(year, month, day);
	}

	/**
	 * 返回当前月一共包括几周。有可能返回5，例如2012年5月就包含5周
	 * 
	 * @param Date date，需要处理的日期
	 * @return String 格式化后的字符串
	 * 
	 * @CreateTime: 2012-8-1
	 * @ModifyTime: 2012-8-1
	 * */
	public static int getWeekCountByMonth(Date date)
	{
		int year = Integer.valueOf(date2String(date, "yyyy"));
		int month = Integer.valueOf(date2String(date, "MM"));
		int day = getLastdayByMonth(year, month);
		return getWeekNumByDate(year, month, day);
	}
	
	/**
	 * 获取当前时间
	 * 
	 * @CreateTime: 2017-6-15
	 * @ModifyTime: 2017-6-15
	 */
	public static Timestamp getNowTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 比较两个时间戳是否相等，仅比较年月日时分秒
	 * 
	 * @param Timestamp
	 *            o1，比较时间1
	 * @param Timestamp
	 *            o2，比较时间2
	 * @CreateTime: 2017-6-15
	 * @ModifyTime: 2017-6-15
	 */
	public static boolean equalsTimestamp(Timestamp o1, Timestamp o2) {
		if (o1 == null && o2 == null)
			return true;
		if (o1 != null && o2 != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (df.format(o1).equals(df.format(o2)))
				return true;
		}
		return false;
	}
	
	/**
	 * 获取当前时间,年月日格式
	 * 
	 * @return
	 */
	public static String getNowDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_STRING);// 设置日期格式
		String date = null;
		try {
			date = sdf.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Timestamp getDayStartTime(String day) {
		return StringUtil.isNotBlank(day) ? Timestamp.valueOf(day + DAY_START_TIME) : null;
	}
	
	public static Timestamp getDayEndTime(String day) {
		return StringUtil.isNotBlank(day) ? Timestamp.valueOf(day + DAY_END_TIME) : null;
	}
	
	public static java.sql.Date getWeekStartDay() {
        return java.sql.Date.valueOf(getWeekStartDayString());
	}
	
	public static String getWeekStartDayString() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
        int d = 0;
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - calendar.get(Calendar.DAY_OF_WEEK);
        }
        calendar.add(Calendar.DAY_OF_WEEK, d);
        // 所在周开始日期
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_STRING);
        return dateFormat.format(calendar.getTime());
	}
	
	public static java.sql.Date getWeekEndDay() {
        return java.sql.Date.valueOf(getWeekEndDayString());
	}
	
	public static String getWeekEndDayString() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
        int d = 0;
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - calendar.get(Calendar.DAY_OF_WEEK);
        }
        calendar.add(Calendar.DAY_OF_WEEK, d + 6);
        // 所在周结束日期
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_STRING);
        return dateFormat.format(calendar.getTime());
	}
	
	public static Timestamp getWeekStartTimestamp() {
        return Timestamp.valueOf(getWeekStartDayString() + DAY_START_TIME);
	}
	
	public static Timestamp getWeekEndTimestamp() {
        return Timestamp.valueOf(getWeekEndDayString() + DAY_END_TIME);
	}
	
	public static java.sql.Date getCurrentSqlDate() {
        return java.sql.Date.valueOf(getNowDate());
	}
	/**
	 * 某一年某个月的每一天
	 */
	public static List<String> getMonthFullDay(int year, int month, int day){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_STRING);
	    List<String> fullDayList = new ArrayList<String>();
	    if(day <= 0 ) day = 1;
	    Calendar cal = Calendar.getInstance();// 获得当前日期对象
	    cal.clear();// 清除信息
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month - 1);// 1月从0开始
	    cal.set(Calendar.DAY_OF_MONTH, day);// 设置为1号,当前日期既为本月第一天
	    int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    for (int j = 0; j <= (count-1);) {
	        if(sdf.format(cal.getTime()).equals(getLastDay(year, month)))
	            break;
	        cal.add(Calendar.DAY_OF_MONTH, j == 0 ? +0 : +1);
	        j++;
	        fullDayList.add(sdf.format(cal.getTime()));
	    }
	    return fullDayList;
	}

	public static String getLastDay(int year,int month){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_STRING);
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month);
	    cal.set(Calendar.DAY_OF_MONTH, 0);
	    return sdf.format(cal.getTime());
	}
	
	/**
	 * 获取当前月份
	 * @return
	 */
	public static String getNowMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat(MONTH_STRING);
		String date = null;
		try {
			date = sdf.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 转换时间格式，去掉毫秒
	 * @return
	 */
	public static String toSimpleTime(Object time) {
		if(time == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_STRING);
		String simpleTime = null;
		try {
			simpleTime = sdf.format(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return simpleTime;
	}
}
