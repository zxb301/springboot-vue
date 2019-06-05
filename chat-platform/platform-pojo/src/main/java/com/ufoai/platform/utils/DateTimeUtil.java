package com.ufoai.platform.utils;


import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtil {
	
	
	//格式：2007年06月07日 12时12分12秒234毫秒
	private final static String[] FORMAT_CHINA = {"年","月","日","时","分","秒","毫秒"};
	//格式：2007-06-07  12:12:12 234
	private final static String[] FORMAT_NORMAL = {"-","-","",":",":","",""};
	//格式：2007/06/07 12:12:12 234
	private final static String[] FORMAT_DATATIME = {"/","/","",":",":","",""};
	//格式：中文星期
	private final static String[] FORMAT_WEEK = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
	/**
	 * 系统日期格式
	 * yyyy-MM-dd HH:mm:ss
	 */
	private static final String DATE_FORMAT_STRING_YMd ="yyyy-MM-dd";
	private static final String DATE_FORMAT_STRING ="yyyy-MM-dd HH:mm:ss";
	private static final String DATE_FORMAT_STRING_BEGIN ="yyyy-MM-dd 00:00:00";
	
	/**
	 * 返回 yyyy-MM-dd HH:mm:ss格式日期
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getToday(){
		return DateFormatUtils.format(new Date(), DATE_FORMAT_STRING);
	}
	public static String getTodayYMD(){
		return DateFormatUtils.format(new Date(), DATE_FORMAT_STRING_YMd);
	}
	/**
	 * 返回 yyyy-MM-dd格式日期
	 * @return yyyy-MM-dd
	 */
	public static String getTodayYMd(){
		return DateFormatUtils.format(new Date(), DATE_FORMAT_STRING);
	}
	/**
	 * 返回 yyyy-MM-dd HH:mm:ss格式日期
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getTodayBegin(){
		return DateFormatUtils.format(new Date(), DATE_FORMAT_STRING_BEGIN);
	}
	
	/**
	 * 获取今日年份  
	 * @return yyyy
	 */
	public static String getTodayYear(){
		return DateFormatUtils.format(new Date(), "yyyy");
	}
	
	/**
	 * 获取今日月份
	 * @return MM
	 */
	public static String getTodayMonth(){
		return DateFormatUtils.format(new Date(), "MM");
	}
	
	/**
	 * 获取今日日期
	 * @return dd
	 */
	public static String getTodayDay(){
		return DateFormatUtils.format(new Date(), "dd");
	}
	
	/**
	 * 获取短日月
	 * @return MMdd
	 */
	public static String getTodayChar4(){
		return DateFormatUtils.format(new Date(), "MMdd");
	}
	
	/**
	 * 返回年月
	 * @return yyyyMM
	 */
	public static String getTodayChar6(){
		return DateFormatUtils.format(new Date(), "yyyyMM");
	}
	
	/**
	 * 返回年月日
	 * @return yyyyMMdd
	 */
	public static String getTodayChar8(){
		return DateFormatUtils.format(new Date(), "yyyyMMdd");
	}
	
	/**
	 * 返回 年月日小时分
	 * @return yyyyMMddHHmm
	 */
	public static String getTodayChar12() {
		return DateFormatUtils.format(new Date(),"yyyyMMddHHmm");
	}	
	
	/**
	 * 返回 年月日小时分秒
	 * @return yyyyMMddHHmmss
	 */
	public static String getTodayChar14(){
		return DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
	}
	
	
	
	
	/**
	 * 返回 年月日小时分秒 毫秒
	 * @return yyyyMMddHHmmssS
	 */
	public static String getTodayChar17(){
		return DateFormatUtils.format(new Date(), "yyyyMMddHHmmssS");
	}
	
	
	/**
	 * 返回中文日期格式 支持4、6、8、12、14、17位转换
	 * @param charDateTime 长整型 CHAR
	 * @return 2007年12月12日 12时12分12秒 234毫秒
	 */
	public static String getFormatChina(String charDateTime){
		return getFormatDateTime(charDateTime,"FORMAT_CHINA");
	}
	
	/**
	 * 返回标准日期格式 支持4、6、8、12、14、17位转换
	 * @param charDateTime 长整型 CHAR
	 * @return  2007-12-12 12:12:12 234
	 */
	public static String getFormatNormal(String charDateTime){
		return getFormatDateTime(charDateTime,"FORMAT_NORMAL");
	}
	
	
	/**
	 * 返回标准日期格式 支持4、6、8、12、14、17位转换
	 * @param charDateTime 长整型 CHAR
	 * @return  2007/12/12 12:12:12 234
	 */
	public static String getFormatDateTime(String charDateTime){
		return getFormatDateTime(charDateTime,"FORMAT_DATATIME");
	}
	
	/**
	 * 把日期格式转换为长整型格式
	 * @param inputDateTime
	 * @return 
	 */
	public static String getDateTimeToChar(String inputDateTime){
		String strResult = "";
		if (null == inputDateTime) {
			return strResult = "";
		}

		if (("".equals(inputDateTime.trim()))) {
			return strResult = "";
		}

		// 替换
		strResult = inputDateTime.replaceAll("年", "");
		strResult = strResult.replaceAll("月", "");
		strResult = strResult.replaceAll("日", "");
		strResult = strResult.replaceAll("时", "");
		strResult = strResult.replaceAll("分", "");
		strResult = strResult.replaceAll("秒", "");
		strResult = strResult.replaceAll("毫", "");
		strResult = strResult.replaceAll(" ", "");
		strResult = strResult.replaceAll("-", "");
		strResult = strResult.replaceAll("/", "");
		strResult = strResult.replaceAll(":", "");
		return strResult;

	}
	
	
	/**
	 * 对日期进行转换
	 * @param charDateTime
	 * @param formatType
	 * @return
	 */
	private static String getFormatDateTime(String charDateTime,String formatType){
		String strResult = "";
		if (null == charDateTime) {
			return strResult = "";
		}

		if (("".equals(charDateTime.trim()))) {
			return strResult = "";
		}

		String[] FORMAT_CHAR = null;
		if (formatType.equals("FORMAT_CHINA")) {
			FORMAT_CHAR = FORMAT_CHINA;
		} else if (formatType.equals("FORMAT_NORMAL")) {
			FORMAT_CHAR = FORMAT_NORMAL;
		} else if (formatType.equals("FORMAT_DATATIME")) {
			FORMAT_CHAR = FORMAT_DATATIME;
		} else {
			return strResult = charDateTime;
		}

		// 去掉空格
		charDateTime = charDateTime.trim();

		if (charDateTime.length() == 4) {
			// MMdd 转换 MM月dd日
			strResult = charDateTime.substring(0, 2) + FORMAT_CHAR[1]
					+ charDateTime.substring(2, 4) + FORMAT_CHAR[2];
		} else if (charDateTime.length() == 6) {
			// yyyyMM 转换 yyyy年MM月
			strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0]
					+ charDateTime.substring(4, 6) + FORMAT_CHAR[1];
		} else if (charDateTime.length() == 8) {
			// yyyyMMdd
			strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0]
					+ charDateTime.substring(4, 6) + FORMAT_CHAR[1]
					+ charDateTime.substring(6, 8) + FORMAT_CHAR[2];
		} else if (charDateTime.length() == 12) {
			// yyyyMMddHHmm
			strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0]
					+ charDateTime.substring(4, 6) + FORMAT_CHAR[1]
					+ charDateTime.substring(6, 8) + FORMAT_CHAR[2] + " "
					+ charDateTime.substring(8, 10) + FORMAT_CHAR[3]
					+ charDateTime.substring(10, 12) + FORMAT_CHAR[4];
		} else if (charDateTime.length() == 14) {
			// yyyyMMddHHmmss
			strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0]
					+ charDateTime.substring(4, 6) + FORMAT_CHAR[1]
					+ charDateTime.substring(6, 8) + FORMAT_CHAR[2] + " "
					+ charDateTime.substring(8, 10) + FORMAT_CHAR[3]
					+ charDateTime.substring(10, 12) + FORMAT_CHAR[4]
					+ charDateTime.substring(12, 14) + FORMAT_CHAR[5];
		} else if (charDateTime.length() == 17) {
			// yyyyMMddHHmmssS
			strResult = charDateTime.substring(0, 4) + FORMAT_CHAR[0]
					+ charDateTime.substring(4, 6) + FORMAT_CHAR[1]
					+ charDateTime.substring(6, 8) + FORMAT_CHAR[2] + " "
					+ charDateTime.substring(8, 10) + FORMAT_CHAR[3]
					+ charDateTime.substring(10, 12) + FORMAT_CHAR[4]
					+ charDateTime.substring(12, 14) + FORMAT_CHAR[5] + " "
					+ charDateTime.substring(14, 17) + FORMAT_CHAR[6];
		} else {
			strResult = charDateTime;
		}

		return strResult;
	}
	
	/**
	 * 将指定Date类型参数转换为指定的Oracle日期时间格式字符串
	 * @param
	 * @return String
	 */
	public static String getOracleDate(Date inputDateTime)
			throws NullPointerException {
		if (null == inputDateTime) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(inputDateTime);
	}
	
	/**
	 * 比对两个时间间隔
	 * @param startDateTime 开始时间
	 * @param endDateTime 结束时间
	 * @param distanceType 计算间隔类型 天d 小时h 分钟m 秒s
	 * @return
	 */
	public static String getDistanceDT(String startDateTime, String endDateTime, String distanceType) {
		String strResult = "";
		long lngDistancVal = 0;
		try {
			SimpleDateFormat tempDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date startDate = tempDateFormat.parse(startDateTime);
			Date endDate = tempDateFormat.parse(endDateTime);
			if (distanceType.equals("d")) {
				lngDistancVal = (endDate.getTime() - startDate.getTime())
						/ (1000 * 60 * 60 * 24);
			} else if (distanceType.equals("h")) {
				lngDistancVal = (endDate.getTime() - startDate.getTime())
						/ (1000 * 60 * 60);
			} else if (distanceType.equals("m")) {
				lngDistancVal = (endDate.getTime() - startDate.getTime())
						/ (1000 * 60);
			} else if (distanceType.equals("s")) {
				lngDistancVal = (endDate.getTime() - startDate.getTime()) / (1000);
			}
			strResult = String.valueOf(lngDistancVal);
		} catch (Exception e) {
			strResult = "0";
		}
		return strResult;
	}

	/**
	 * @param strDate
	 * @return
	 * 如2015-07-06转化成201507060000
	 */
	public static long currentTimeMillis(String strDate) {
		long timeMillis = 0;
		if (strDate == null || strDate.trim().length() != 10) {
			return timeMillis;
		}
		String year = strDate.trim().substring(0, 4);
		String month = strDate.trim().substring(5, 7);
		String day = strDate.trim().substring(8, 10);
		timeMillis = Long.parseLong(year + month + day + "000000");
		return timeMillis;
	}
	
	/**
	 * @param strDate
	 * @return
	 * 2015-07-06转化成201507060000
	 */
	public static long currentDateMillis(String strDate) {
		long timeMillis = 0;
		try {
			String tem = dateFormat.format(dateFormat_3.parse(strDate));
			timeMillis = Long.parseLong(tem);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timeMillis;
	}
	
	public static long currentWeekMillis(int year,int month) {
		try {
			Calendar c = Calendar.getInstance();
			c.set(year, month - 1, 1);
			int tem = c.get(Calendar.DAY_OF_WEEK);
			return currentDateMillis(year + "-" + month + "-" + (10 - tem));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static long currentLastDayOfMonthMillis(int year, int month) {
		try {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month - 1);
			int tem = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			return currentDateMillis(year + "-" + month + "-" + tem);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String getHour(String date) {
		try {
			return dateFormat_4.format(dateFormat.parse(date));
		} catch (ParseException e) {
		    return "";
		}
	}
	
	/**
	 * @param date 
	 * @return 日/月
	 * yyyyMMddHHmmss格式转化成 dd/MM
	 */
	public static String getDay(String date) {
		try {
			return dateFormat_5.format(dateFormat.parse(date));
		} catch (ParseException e) {
		    return "";
		}
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 * yyyyMMddHHmmss 格式转化成 yyyy-MM-dd
	 */
	public static String formatDateToOther(String date) {
		try {
			return dateFormat_3.format(dateFormat.parse(date));
		} catch (ParseException e) {
		    return "";
		}
	}
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat dateFormat_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat dateFormat_3 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat dateFormat_4 = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat dateFormat_5 = new SimpleDateFormat("dd/MM");
	private static SimpleDateFormat dateFormat_6 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


	/**
	 * 
	 * @param time
	 * @return
	 * yyyyMMddHHmmss 格式转化成 yyyy-MM-dd HH:mm:ss 
	 */
	public static String formatDateStrToOtherStr(String time) {
		String reTime = "";
		try {
			reTime = dateFormat_2.format(dateFormat.parse(time));
		} catch (ParseException e) {
		}
		return reTime;
	}
	
	public static long formatDate(String strDate) {
		 long reDate = 0;
		 try {
			Date date = dateFormat.parse(strDate);
			String tem = dateFormat.format(date);
			reDate =  Long.parseLong(tem);
		} catch (ParseException e) {
		}
		return reDate;
	}
	
    /**
     * 日期差计算 例如在某个日期增加几天后的日期 返回几天后日期
     * @param startDate  yyyyMMdd
     * @param addDate
     * @return
     * 
     */
    public static String getIncreaseDT (String startDate ,int addDate){
		String strResult = "0000-00-00";

		try {
			// 把字符串型日期转换为日期
			Calendar localDate = new GregorianCalendar();
			SimpleDateFormat tempDateFormat = new SimpleDateFormat("yyyyMMdd");
			Date tempDate = tempDateFormat.parse(startDate);
			localDate.setTime(tempDate);
			localDate.add(Calendar.DATE, addDate);

			String curyear = "" + localDate.get(Calendar.YEAR);
			int intmonth = localDate.get(Calendar.MONTH) + 1;
			String curmonth = "" + intmonth;
			String curday = "" + localDate.get(Calendar.DAY_OF_MONTH);

			if (curmonth.length() == 1) {
				curmonth = "0" + curmonth;
			}
			if (curday.length() == 1) {
				curday = "0" + curday;
			}
			strResult = curyear + "" + curmonth + "" + curday;
		} catch (Exception e) {
		}
		return strResult;
      }
	
    /**
     * 获取本周的开始日期 （星期天的日期）20070201
     * @return
     */
    public static String getWeekStartDate(){
		String strResult = "19000101";
		try {
			Calendar calendar = Calendar.getInstance();
			int intWeekNum = calendar.get(Calendar.DAY_OF_WEEK);
			intWeekNum = intWeekNum - 1;
			strResult = getIncreaseDT(getTodayChar8(), -intWeekNum);
		} catch (Exception ex) {
			strResult = "19000101";
		}
		return strResult;
     }
    
    /**
     * 日期差计算 例如在某个日期增加几天后的日期 返回几天后日期
     * @param startDate
     * @param addDate
     * @return
     */
    public static String getIncreaseDT1 (String startDate, int addDate){
        String strResult="0000-00-00";

        try{
           //把字符串型日期转换为日期
            Calendar localDate = new GregorianCalendar();
            SimpleDateFormat tempDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date tempDate = tempDateFormat.parse(startDate);
            localDate.setTime(tempDate);
            localDate.add(Calendar.DATE, addDate);

            String curyear = "" + localDate.get(Calendar.YEAR);
            int intmonth = localDate.get(Calendar.MONTH) + 1;
            String curmonth = "" + intmonth;
            String curday = "" + localDate.get(Calendar.DAY_OF_MONTH);

            if (curmonth.length() == 1){
                curmonth = "0" + curmonth;
            }
            if (curday.length() == 1){
                curday = "0" + curday;
            }
            strResult = curyear + "-" + curmonth + "-" + curday;
         }catch(Exception e){
         
         }
          return strResult;
      }
	
    

	
   /**
    * 获取今天星期几 中文
    * @return
    */
    public static String getWeekChina(){
        String strResult = " ";
        try {
            Calendar calendar = Calendar.getInstance();
            int intWeekNum = calendar.get(Calendar.DAY_OF_WEEK);
            intWeekNum = intWeekNum - 1;
            strResult = FORMAT_WEEK[intWeekNum] ;
        } catch (Exception ex) {
        	strResult = " ";
        }
        return strResult;
    }
    
    /**
	 * 返回年月日
	 * @return yyyyMMdd
	 */
	public static String getTodayChar8ByFormat(String format){
		return DateFormatUtils.format(new Date(), format);
	}
	
	/**
	 * 返回日期类型
	 * @param strDate 2014-11-26
	 * @return
	 */
	public static Date getDateFromStr(String strDate){
		Date date = null;
		try {
			date = dateFormat_3.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	

	/**
	 * 返回日期类型
	 * @param  2014-11-26
	 * @return
	 */
	public static String getStrFromDate(Date date){
		String strDate = null;
		strDate = dateFormat_3.format(date);
		return strDate;
	}
	
	
	/**
	 * format yyyy-MM-ddHH:mm:ss or yyyy/MM/dd HH:mm:ss To yyyyMMddHHmmss
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTimeChar19To14(String time) {
		String resultTime = "";
		Date formatTime = null;
		try {
			formatTime= dateFormat_2.parse(time);
			resultTime = dateFormat.format(formatTime);
		} catch (ParseException e) {
			try {
			    formatTime = dateFormat_6.parse(time);
				resultTime = dateFormat.format(formatTime);
			} catch (ParseException e1) {
				resultTime = time;
			}
		}
		return resultTime;
	}
	
	
	
	/**
	 * format yyyyMMddHHmmss To yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTimeChar14To19(String time) {
		String resultTime = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date dd = sdf.parse(time);
			SimpleDateFormat sdfAfter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			resultTime = sdfAfter.format(dd);
		} catch (Exception e) {
			resultTime = null;
		}
		return resultTime;
	}
	
	
	
	/**
	 * format HHmm To HH:mm
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTimeCharHHmm(String time) {
		String resultTime = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
			Date dd = sdf.parse(time);
			SimpleDateFormat sdfAfter = new SimpleDateFormat(
					"HH:mm");
			resultTime = sdfAfter.format(dd);
		} catch (Exception e) {
			resultTime = null;
		}
		return resultTime;
	}
	private static SimpleDateFormat dateFormat_year = new SimpleDateFormat("yyyy");
	private static SimpleDateFormat dateFormat_mouth = new SimpleDateFormat("MM");
	private static SimpleDateFormat dateFormat_day = new SimpleDateFormat("dd");
	private static SimpleDateFormat dateFormat_hour = new SimpleDateFormat("HH");
	private static SimpleDateFormat dateFormat_min = new SimpleDateFormat("mm");
	public static String getYear(String date) {
		try {
			return dateFormat_year.format(dateFormat_2.parse(date));
		} catch (ParseException e) {
			return "";
		}
	}
	public static String getMouth(String date) {
		try {
			return dateFormat_mouth.format(dateFormat_2.parse(date));
		} catch (ParseException e) {
			return "";
		}
	}
	public static String getDay2(String date) {
		try {
			return dateFormat_day.format(dateFormat_2.parse(date));
		} catch (ParseException e) {
			return "";
		}
	}
	public static String getHour2(String date) {
		try {
			return dateFormat_hour.format(dateFormat_2.parse(date));
		} catch (ParseException e) {
			return "";
		}
	}
	public static String getMin(String date) {
		try {
			return dateFormat_min.format(dateFormat_2.parse(date));
		} catch (ParseException e) {
			return "";
		}
	}
	public static void main(String[] args) {
		String date= DateTimeUtil.getIncreaseDT1(DateTimeUtil.getTodayYMd(),1);
         System.out.print(date);
     }
	public static String getStrtoDate(Date date){
		String strDate = null;
		strDate = dateFormat_2.format(date);
		return strDate;
	}

}
