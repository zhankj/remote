package cn.cttic.sysframe.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 日期工具类
 * <p>
 * 日期类型的工具类，该类均为静态方法，直接调用
 * </p>
 * 
 * @author
 * 
 */
public class DateUtil {

	//
	// private static final SimpleDateFormat sdf = new SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:ss");
	// private static final SimpleDateFormat sdfSimple = new SimpleDateFormat(
	// "yyyy-MM-dd");

	/**
	 * 日期格式化模式（日期类型数据）
	 * <p>
	 * 日期格式化模式，使用此模式将日期格式化为“2012-10-08”，一般用于日期类型数据格式化
	 * </p>
	 */
	public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

	public static final String PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	// 时间格式化
	public static final String PATTERNYYYYMMDD = "yyyyMMdd";

	public static final String DEFAULT_EXP_DATE = "2099-12-31";

	/**
	 * 日期格式化模式（日期类型数据）
	 * <p>
	 * 日期格式化模式，使用此模式将日期格式化为“10-08”，一般用于日期类型数据格式化
	 * </p>
	 */
	public static final String PATTERN_MM_DD = "MM-dd";

	public static final String PATTERN_UNIT_YYYY_MM_DD = "yyyy年-MM月-dd日";

	public static final String PATTERN_UNIT_MM_DD = "MM月-dd日";

	public static final String PATTERN_UNIT_MMDD = "MM月dd日";

	public static final String PATTERN_UNIT_YYYYMMDD = "yyyy年MM月dd日";

	/**
	 * 日期格式化模式（时间类型数据）
	 * <p>
	 * 日期格式化模式，使用此模式将日期格式化为“2012-10-08 10:10:08”，一般用于时间类型数据格式化
	 * </p>
	 */
	public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式化模式（时间类型数据），精确到分
	 * <p>
	 * 日期格式化模式，使用此模式将日期格式化为“2012-10-08 10:10”，一般用于时间类型数据格式化
	 * </p>
	 */
	public static final String PATTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	/**
	 * 日期格式化模式（时间类型数据），精确到分
	 * <p>
	 * 日期格式化模式，使用此模式将日期格式化为“2012/10/08 10:10”，一般用于时间类型数据格式化
	 * </p>
	 */
	public static final String PATTERN_YYYYMMDD_HH_MM = "yyyy/MM/dd HH:mm";

	/**
	 * 日期格式化模式（时间类型数据），精确到时
	 * <p>
	 * 日期格式化模式，使用此模式将日期格式化为“2012-10-08 10”，一般用于时间类型数据格式化
	 * </p>
	 */
	public static final String PATTERN_YYYY_MM_DD_HH = "yyyy-MM-dd HH";

	public static final String PATTERN_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

	public static final String PATTERN_DD = "dd";

	public static final String PATTERN_YYYY = "yyyy";

	public static final String PATTERN_YYYYMM = "yyyyMM";

	/**
	 * 其他时间值转换为毫秒单位：秒
	 */
	public static final long UNIT_SECOND_TIME = 1000;

	/**
	 * 其他时间值转换为毫秒单位：分钟
	 */
	public static final long UNIT_MINUS_TIME = 60 * UNIT_SECOND_TIME;

	/**
	 * 其他时间值转换为毫秒单位：小时
	 */
	public static final long UNIT_HOUR_TIME = 60 * UNIT_MINUS_TIME;

	/**
	 * 其他时间值转换为毫秒单位：天
	 */
	public static final long UNIT_DAY_TIME = 24 * UNIT_HOUR_TIME;

	/**
	 * 时间单位名称：秒
	 */
	public static final String UNIT_SECOND_NAME = "秒";

	/**
	 * 时间单位名称：分钟
	 */
	public static final String UNIT_MINUS_NAME = "分钟";

	/**
	 * 时间单位名称：小时
	 */
	public static final String UNIT_HOUR_NAME = "小时";

	/**
	 * 时间单位名称：天
	 */
	public static final String UNIT_DAY_NAME = "天";

	public static final String PATTERN_MM_DD_HH = "MM月dd日HH点";

	/**
	 * 根据特定模式，将字符串型日期对象解析成Date对象
	 * 
	 * @param source 要解析的字符串
	 * @param pattern 解析模式，默认为{@value #PATTERN_YYYY_MM_DD_HH_MM_SS}
	 * @return 解析结果
	 * @throws ParseException 如果要解析的字符串格式不匹配，则抛出此异常
	 */
	public static Date parse(final String source, String pattern) throws ParseException {
		// 检查value是否为空
		if (source == null || StringUtil.isTrimEmpty(source)) {
			return null;
		}
		// 如果pattern为空
		if (pattern == null) {
			// 设置pattern为PATTERN_YYY_MM_DD_HH_MM_SS
			pattern = PATTERN_YYYY_MM_DD_HH_MM_SS;
		}
		// 初始化一个format类
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		// 开始解析
		return format.parse(source);
	}

	/**
	 * 将日期对象根据特定格式格式化成字符串
	 * 
	 * @param source 要格式化的日期对象
	 * @param pattern 格式化模式，默认为{@value #PATTERN_YYYY_MM_DD_HH_MM_SS}
	 * @return 格式化后的字符串
	 */
	public static String format(final Date source, String pattern) {
		// 检查value是否为空
		if (source == null) {
			return null;
		}
		// 如果pattern为空
		if (pattern == null) {
			// 设置pattern为PATTERN_YYYY_MM_DD_HH_MM_SS
			pattern = PATTERN_YYYY_MM_DD_HH_MM_SS;
		}
		// 初始化一个format类
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(source);
	}

	/**
	 * 格式化到天
	 * 
	 * @param source
	 * @return
	 */
	public static String formatToDay(final Date source) {

		return format(source, PATTERN_YYYY_MM_DD);
	}

	/**
	 * 格式化日期到秒
	 * 
	 * @param source
	 * @return
	 */
	public static String formatToSecond(final Date source) {
		return format(source, PATTERN_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 格式化日期到分钟
	 * 
	 * @param source
	 * @return
	 */
	public static String formatToMinute(final Date source) {
		return format(source, PATTERN_YYYY_MM_DD_HH_MM);
	}

	/**
	 * 格式化日期到小时
	 * 
	 * @param source
	 * @return
	 */
	public static String formatToHour(final Date source) {
		return format(source, PATTERN_YYYY_MM_DD_HH);
	}

	/**
	 * 03月01日15点
	 * 
	 * @param source
	 * @return
	 */
	public static String formatToMonthHour(final Date source) {
		return format(source, PATTERN_MM_DD_HH);
	}

	/**
	 * 返回服务器时间
	 * 
	 * @return
	 */
	public static Date getServerDate() {
		return new Date();
	}

	/**
	 * 判断特定时间距离当前服务器时间的毫秒数
	 * 
	 * @param date 要比较的时间
	 * @return 比较结果，如果当前日期迟于要比较的时间则返回值小于零，反之大于零
	 */
	private static long millisecondsBeforeNow(final Date source) {
		// 检查日期对象是否存在
		if (source == null) {
			return 0;
		}
		return new Date().getTime() - source.getTime();
	}

	/**
	 * 此方法用于前台项目显示当前相差的时间
	 * <p>
	 * 该方法用于前台项目中使用，一般用于提问时间等，规则如下（xx为时间，数字）：<br/>
	 * （当前时间与发布时间）未超过一分钟：则显示秒，即xx秒前；<br/>
	 * （当前时间与发布时间）未超过一小时：则显示到分钟，即xx分钟前；<br/>
	 * （当前时间与发布时间）未超过一天（24小时）：则显示到小时，即xx小时前；<br/>
	 * （当前时间与发布时间）超过一天（24小时）：则显示实际发布时间，年-月-日 时:分:秒；
	 * </p>
	 * 
	 * @param source 要格式化的日期
	 * @return 格式化结果
	 */
	public static String formatToFront(final Date source) {
		// 获取相差的毫秒数
		long d = millisecondsBeforeNow(source);
		// 之前描述名称
		final String BERFORE = "前";
		// 一分钟以内
		if (d < UNIT_MINUS_TIME) {
			long unit = d / UNIT_SECOND_TIME;
			return (unit == 0 ? 1 : unit) + UNIT_SECOND_NAME + BERFORE;
		}
		// 一个小时以内
		else if (d < UNIT_HOUR_TIME) {
			return (d / UNIT_MINUS_TIME) + UNIT_MINUS_NAME + BERFORE;
		}
		// 未超过一天
		else if (d < UNIT_DAY_TIME) {
			return (d / UNIT_HOUR_TIME) + UNIT_HOUR_NAME + BERFORE;
		}
		// 超过一天
		else {
			return format(source, PATTERN_YYYY_MM_DD_HH_MM_SS);
		}
	}

	/**
	 * 获取日期相对于1970/01/01的时间戳
	 * 
	 * @param source 要比较的时间
	 * @return 时间戳
	 */
	public static long getTime(final Date source) {
		if (source == null) {
			return -1;
		}
		return source.getTime();
	}

	/**
	 * 将秒转成分钟和秒字符串
	 * 
	 * @param second 秒钟
	 * @return xxm xxs
	 */
	public static String formatToFront(Integer second) {
		long s = second % 60;
		int t = (int) (second / 60);
		return t + "分 " + s + "秒";
	}

	/**
	 * 特定日期和服务器日期相差的天数
	 * 
	 * @param source 要比较的日期
	 * @return 天数，之后大于零，之前小于零
	 */
	public static int daysBeforeNow(final Date source) {
		// 如果要比较的source不存在
		if (source == null) {
			return 0;
		}
		Calendar now = Calendar.getInstance();
		Calendar old = Calendar.getInstance();
		// 设置时分秒为0:0:0
		old.setTime(source);
		old.set(Calendar.HOUR_OF_DAY, 0);
		old.set(Calendar.MINUTE, 0);
		old.set(Calendar.SECOND, 0);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		// 获取相差描述
		long l = old.getTimeInMillis() - now.getTimeInMillis();
		// 转换程天数
		return BigDecimal.valueOf(l).divide(BigDecimal.valueOf(UNIT_DAY_TIME), RoundingMode.HALF_UP).intValue();
	}

	/**
	 * 特定日期1 和 特定日期2 相差的天数
	 * 
	 * @param source 要比较的日期
	 * @return 天数，之后大于零，之前小于零
	 */
	public static int daysBefore(final Date source1, final Date source2) {
		// 如果要比较的source不存在
		if (source1 == null || source2 == null) {
			return 0;
		}
		Calendar now = Calendar.getInstance();
		Calendar old = Calendar.getInstance();
		// 设置时分秒为0:0:0
		old.setTime(source1);
		old.set(Calendar.HOUR_OF_DAY, 0);
		old.set(Calendar.MINUTE, 0);
		old.set(Calendar.SECOND, 0);

		now.setTime(source2);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		// 获取相差描述
		long l = old.getTimeInMillis() - now.getTimeInMillis();
		// 转换程天数
		return BigDecimal.valueOf(l).divide(BigDecimal.valueOf(UNIT_DAY_TIME), RoundingMode.HALF_UP).intValue();
	}

	/**
	 * 特定日期1 和 特定日期2 相差的天数
	 * 
	 * @param source 要比较的日期
	 * @return 天数，之后大于零，之前小于零
	 */
	public static int daysBeforeSECOND(final Date source1, final Date source2) {
		// 如果要比较的source不存在
		if (source1 == null || source2 == null) {
			return 0;
		}
		Calendar now = Calendar.getInstance();
		Calendar old = Calendar.getInstance();
		old.setTime(source1);
		now.setTime(source2);

		// 获取相差描述
		long l = old.getTimeInMillis() - now.getTimeInMillis();
		// 转换程天数
		return BigDecimal.valueOf(l).divide(BigDecimal.valueOf(UNIT_DAY_TIME), RoundingMode.DOWN).intValue();
	}

	/**
	 * 获取在某天的基础上增加或减少N天
	 * 
	 * @param date 某天
	 * @param num 加上或减少的天数，正数为加，负数为减。
	 * @return
	 * @author
	 */
	public static Date getDateAfterDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, days);
		return c.getTime();
	}

	/**
	 * 获取今天是今年中的第几天
	 * 
	 * @return
	 * @author
	 */
	public static int getTodayOfYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取指定日期是今年中的第几天
	 * 
	 * @param dateStr 日期字符串 格式：yyyy-MM-dd
	 * @return 指定日期是今年中的第几天
	 * @author
	 * @note 请不要输入2012-02-31、2012-13-32之类的非法日期字符串
	 */
	public static int getDayOfYear(String dateStr) {
		Calendar calendar = initCalendarByDateString(dateStr);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 使用yyyy-MM-dd格式的日期字符串实例化一个Calendar对象
	 * 
	 * @param dateStr 日期字符串 格式：yyyy-MM-dd
	 * @return
	 * @author
	 */
	public static Calendar initCalendarByDateString(String dateStr) {
		if (null != dateStr && !"".equals(dateStr.trim()) && dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
			String[] date = dateStr.split("-");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.parseInt(date[0]));
			calendar.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);// 月份需要减1，Calendar从0开始算1月
			calendar.set(Calendar.DATE, Integer.parseInt(date[2]));
			return calendar;
		} else {
			throw new IllegalArgumentException("传入的日期格式字符串非法！接受的日期格式字符串为：yyyy-MM-dd");
		}
	}

	/**
	 * 获取今年总共有多少天
	 * 
	 * @return
	 * @author
	 */
	public static int getDaysOfThisYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getMaximum(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 在当前时间的基础上添加特定时间
	 * <p>
	 * 根据type类型来区分添加的时间单位，type等同于Calendar.XXXX 如：小时Calendar.HOUR_OF_DAY。<br/>
	 * 暂只支持，DAY_OF_MONTH,HOUR_OF_DAY，MINUTE
	 * </p>
	 * 
	 * @param type 类型
	 * @param value 要新增的值（如果是负数则是减少的值）
	 * @return 处理后的时间
	 */
	public static Date addByCurrent(int type, int value) {
		return addByDate(new Date(), type, value);
	}

	/**
	 * 在特定时间的基础上添加特定时间
	 * <p>
	 * 根据type类型来区分添加的时间单位，type等同于Calendar.XXXX 如：小时Calendar.HOUR_OF_DAY。<br/>
	 * 暂只支持，DAY_OF_MONTH,HOUR_OF_DAY，MINUTE
	 * </p>
	 * 
	 * @param date 特定时间
	 * @param type 类型
	 * @param value 要新增的值（如果是负数则是减少的值）
	 * @return 处理后的时间
	 */
	public static Date addByDate(Date date, int type, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(type, now.get(type) + value);
		return now.getTime();
	}

	/**
	 * 同oracle：add_months函数
	 * 
	 * @param date
	 * @param months
	 * @return
	 * @author KONGJF
	 * @date 2014-6-16 下午1:55:28
	 */
	public static Date addMonths(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar caltem = Calendar.getInstance();
		caltem.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + months, 1);
		int maxday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int today = cal.get(Calendar.DATE);
		int lastday = caltem.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (today == maxday || today > lastday) {
			caltem.set(Calendar.DAY_OF_MONTH, lastday);
		} else {
			caltem.set(Calendar.DAY_OF_MONTH, today);
		}
		return caltem.getTime();
	}

	/**
	 * 格式化观看时间
	 * 
	 * @param second 秒
	 * @return 0时 00分 00秒
	 */
	public static String formatMediaTime(Integer second) {
		if (second == null) {
			second = 0;
		}
		Integer hour = second / 60 / 60;
		Integer mediaTime = second / 60 - hour * 60;
		Integer mediaSecond = second - hour * 60 * 60 - mediaTime * 60;
		StringBuilder sb = new StringBuilder();
		sb.append(hour < 10 ? hour : hour).append("时 ");
		sb.append(mediaTime < 10 ? "0" + mediaTime : mediaTime).append("分 ");
		sb.append(mediaSecond < 10 ? "0" + mediaSecond : mediaSecond).append("秒");
		return sb.toString();
	}

	/**
	 * 格式化观看时间
	 * 
	 * @param second 秒
	 * @return 0时 00分 00秒
	 */
	public static String formatMediaTime(BigDecimal second) {
		if (second == null) {
			second = new BigDecimal(0);
		}
		return formatMediaTime(second.intValue());
	}

	/**
	 * 检查两个日期的某个单位的差值，当前支持小时、天数
	 * 
	 * @param before 被减数
	 * @param after 减数
	 * @param type 类型，参见Calendar
	 * @return 值
	 */
	public static int distance(Date before, Date after, int type) {
		if (before == null || after == null) {
			throw new IllegalArgumentException("参数值错误");
		}
		Calendar start = Calendar.getInstance();
		start.setTime(before);
		Calendar end = Calendar.getInstance();
		end.setTime(after);

		long unit = -1;
		switch (type) {
			case Calendar.DAY_OF_MONTH:
				end.set(Calendar.HOUR_OF_DAY, 0);
				start.set(Calendar.HOUR_OF_DAY, 0);
				unit = UNIT_DAY_TIME;
				break;
			case Calendar.HOUR_OF_DAY:
				unit = UNIT_HOUR_TIME;
				// 设置分秒
				end.set(Calendar.SECOND, 0);
				end.set(Calendar.MINUTE, 0);
				start.set(Calendar.SECOND, 0);
				start.set(Calendar.MINUTE, 0);
				break;
			case Calendar.MINUTE:
				unit = UNIT_MINUS_TIME;
				// 设置分秒
				end.set(Calendar.SECOND, 0);
				start.set(Calendar.SECOND, 0);
				break;
			case Calendar.SECOND:
				unit = UNIT_SECOND_TIME;
				break;
			default:
				throw new IllegalArgumentException("type = " + type + " no support !!!!");
		}
		return BigDecimal.valueOf(start.getTimeInMillis() - end.getTimeInMillis()).divide(BigDecimal.valueOf(unit), RoundingMode.HALF_UP).intValue();
	}

	/**
	 * @Title formatMinuteSecond
	 * @Description 根据秒数返回M分钟S秒
	 * @param second 秒
	 * @return String
	 */
	public static String formatMinuteSecond(Object second) {
		if (StringUtil.isEmpty(second))
			return "—";
		try {
			int m = Integer.parseInt(second.toString()) / 60;
			int s = Integer.parseInt(second.toString()) % 60;
			return m + "分" + s + "秒";
		}
		catch (NumberFormatException e) {
			return "—";
		}
	}

	/**
	 * 获取当前日期
	 * 
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getCurrentDate(String pattern) throws ParseException {
		String sToday = format(new Date(), pattern);
		return parse(sToday, pattern);
	}

	/**
	 * 获取当天的最后一秒
	 * 
	 * @return
	 * @author
	 */
	public static String getTodayLastSecond() {
		String sToday = format(new Date(), PATTERN_YYYY_MM_DD);
		return sToday + " 23:59:59";
	}

	/**
	 * 获取指定天的最后一秒
	 * 
	 * @return
	 * @author KONGJF
	 * @throws ParseException
	 */
	public static Date getDateLastSecond(Date date) throws ParseException {
		String sday = format(date, PATTERN_YYYY_MM_DD);
		return parse(sday + " 23:59:59", PATTERN_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取当天的第一秒
	 * 
	 * @return
	 * @author
	 */
	public static String getTodayFirstSecond() {
		String sToday = format(new Date(), PATTERN_YYYY_MM_DD);
		return sToday + " 00:00:00";
	}

	/**
	 * 获取指定天的第一秒
	 * 
	 * @return
	 * @author Huang Peng
	 * @throws ParseException
	 */
	public static Date getDateFirstSecond(Date date) throws ParseException {
		String sday = format(date, PATTERN_YYYY_MM_DD);
		return parse(sday + " 00:00:00", PATTERN_YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
	 * 比较两个时间是否是同一周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeek(Date date1, Date date2) {

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		// subYear==0,说明是同一年
		if (subYear == 0) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		// 例子:cal1是"2005-1-1"，cal2是"2004-12-25"
		// java对"2004-12-25"处理成第52周
		// "2004-12-26"它处理成了第1周，和"2005-1-1"相同了
		// 大家可以查一下自己的日历
		// 处理的比较好
		// 说明:java的一月用"0"标识，那么12月用"11"
		else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		// 例子:cal1是"2004-12-31"，cal2是"2005-1-1"
		else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;

	}

	/**
	 * 获得指定日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 * @author
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 获取传入时间的延后几分钟
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date getAfterDateMinute(Date date, int minute) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minute);
		return c.getTime();
	}

	/**
	 * 取得指定日期上一周的最后一天
	 * 
	 * @param date
	 * @return
	 * @author
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.add(Calendar.WEEK_OF_YEAR, -1);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 获取特定日历字段的下一个时间点
	 * <p>
	 * 该方法会将pattern之外的参数置为0，如yyyy-MM-dd 会将时、分、秒和毫秒置为零
	 * </p>
	 * 
	 * @param source
	 * @param field
	 * @param pattern
	 * @return
	 * 
	 */
	public static Date getNextDate(Date source, int field, String pattern) {
		/**
		 * 检查参数
		 */
		if (source == null || StringUtil.isTrimEmpty(pattern)) {
			throw new IllegalArgumentException("参数异常");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(source);
		c.set(field, c.get(field) + 1);
		source = c.getTime();
		try {
			source = parse(format(source, pattern), pattern);
		}
		catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
		return source;
	}

	/**
	 * 取得指定日期所在周的最后一天的最后一秒
	 * 
	 * @param date
	 * @return
	 * @author
	 * @throws ParseException
	 */
	public static Date getLastDayOfCurrentWeek(Date date) throws ParseException {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		String sToday = format(c.getTime(), PATTERN_YYYY_MM_DD) + " 23:59:59";
		date = parse(sToday, "yyyy-MM-dd HH:mm:ss");
		return date;
	}

	/**
	 * 取得指定日期所在周的最开始一天的最开始一秒
	 * 
	 * @param date
	 * @return
	 * @author
	 * @throws ParseException
	 */
	public static Date getFirstDayOfCurrentWeek(Date date) throws ParseException {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		String sToday = format(c.getTime(), PATTERN_YYYY_MM_DD) + "  00:00:00";
		date = parse(sToday, "yyyy-MM-dd HH:mm:ss");
		return date;
	}

	/**
	 * 根据日期取得星期几
	 * 
	 * @param date
	 * @return
	 * @author
	 */
	public static String getWeek(Date date) {
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return weeks[week_index];
	}

	/**
	 * @Title getServiceLimit
	 * @Description 根据serviceDayLimit获取serviceLimit
	 * @param dayNum
	 * @return String
	 * @author
	 * @throws
	 */
	private static Calendar fromCal = Calendar.getInstance();

	public static String getServiceLimitDate(Object dayNum) {
		if (StringUtil.isEmpty(dayNum))
			return null;
		fromCal.setTime(new Date());
		fromCal.add(Calendar.DATE, Integer.parseInt(dayNum.toString()));
		String limitDate = format(fromCal.getTime(), PATTERN_YYYY_MM_DD_HH_MM_SS);
		return limitDate;
	}

	/**
	 * 获取两个指定时间相差的周数,大于为正值，小于为负值
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getWeekDistance(Date start, Date end) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(start);
		int weekStart = cal1.get(Calendar.WEEK_OF_YEAR);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(end);
		int weekEnd = cal2.get(Calendar.WEEK_OF_YEAR);
		return (weekStart - weekEnd);
	}

	/**
	 * @Title getDaysOfWeek
	 * @Description 获取某一年某一周的所有日期 例如：2013年第45周是：11月4好到11月10号,并且周一作为每周的第一天
	 * @return Date[]
	 * @author
	 */
	public static Date[] getDaysOfWeek(Map<String, Object> map) {
		int mondayPlus = getMondayPlus();
		int year = (Integer) map.get("year");
		int week = (Integer) map.get("week");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.YEAR, year); // 获取某年某周的第一天
		currentDate.set(Calendar.WEEK_OF_YEAR, week); // 第几周
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		Date[] ds = new Date[7];
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 7; i++) {
			ds[i] = new Date(monday.getTime() + i * 24 * 60 * 60 * 1000);
			Integer y = Integer.parseInt(sdf.format(ds[i]));
			if (!list.contains(y)) {
				list.add(y);
			}
		}
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		int calcYear = list.get(0); // 给year赋值年份
		// 跨年份的周
		if (calcYear > year) {
			year = calcYear;
			week = 2;
			map.put("year", year);
			map.put("week", week);
		}
		if (calcYear < year) {
			year = calcYear;
			map.put("year", year);
			map.put("date", 31);
			// 当年份小于当前年份,得计算当前周
			int w = getWeeksOfYear(map);
			if ((Integer) map.get("date") != 31) {
				w = w + 1;
			}
			map.put("week", w);
			ds = getDaysOfWeek(map);
		}
		return ds;
	}

	private static int getMondayPlus() {
		Calendar c = Calendar.getInstance();
		// 获得今天是一周的第几天，星期一是第一天，星期二是第二天......
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	private static int getWeeksOfYear(Map<String, Object> map) {
		Calendar c = Calendar.getInstance();
		int year = (Integer) map.get("year");
		int date = (Integer) map.get("date");
		c.set(year, 11, date);
		int weeks = c.get(Calendar.WEEK_OF_YEAR);
		if (weeks == 1) {
			map.put("date", --date);
			weeks = getWeeksOfYear(map);
		}
		return weeks;
	}

	/**
	 * 根据日期取得月份
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonth(Date date) {
		String[] months = { "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int i = cal.get(Calendar.MONTH);
		if (i < 0) {
			i = 0;
		}
		return months[i];
	}

	/**
	 * 比较指定两个时间之间相差的毫秒数
	 * 
	 * @param date1
	 * @param date2
	 * @return date1晚于date2时则返回值大于0,反之小于0,相等等于0
	 */
	public static long millisecondsDistance(Date date1, Date date2) {
		// 检查日期是否存在
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("两个日期参数值错误");
		}

		return date1.getTime() - date2.getTime();

	}

	// 时间转字符
	public static String DateFormatStr(Date Date, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(Date);
		return date;
	}

	// 字符串转时间
	public static Date strFormatDate(String Date, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(Date);
		return date;
	}

	/**
	 * 两个时间相差秒数
	 * 
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return
	 * @author fujz
	 * @date 2014-5-13 上午11:33:53
	 */
	public static long betweenSecond(Date start, Date end) {
		return ((end.getTime() - start.getTime()) / 1000);
	}

	/**
	 * 两个时间相差分钟数
	 * 
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return
	 * @author fujz
	 * @date 2014-5-13 上午11:34:19
	 */
	public static long betweenMinute(Date start, Date end) {
		return ((end.getTime() - start.getTime()) / 60 / 1000);
	}

	/**
	 * 两个时间相差小时数
	 * 
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return
	 * @author fujz
	 * @date 2014-5-13 上午11:34:54
	 */
	public static long betweenHour(Date start, Date end) {
		return ((end.getTime() - start.getTime()) / 3600 / 1000);
	}

	/**
	 * 两个时间相差天数
	 * 
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return
	 * @author fujz
	 * @date 2014-5-13 上午11:35:15
	 */
	public static long betweenDay(Date start, Date end) {
		return ((end.getTime() - start.getTime()) / 3600 / 24 / 1000);
	}

	/**
	 * 获取某年某月第一天第0秒
	 * 
	 * @param specifiedMonth
	 * @param dateFormatType
	 * @return
	 * @throws CSException
	 * @author lihuijuan
	 * @date 2014-5-21 下午2:12:51
	 */
	public static Date getTheFirstDayOfSpecifiedMonth(int specifiedYear, int specifiedMonth) {
		if (specifiedMonth > 12 || specifiedMonth < 1)
			throw new IllegalArgumentException("指定月份参数值错误");
		Calendar calendar = Calendar.getInstance();
		calendar.set(specifiedYear, specifiedMonth - 1, 1, 0, 0, 0);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 获取某年某月最后一天最后一秒
	 * 
	 * @param specifiedMonth
	 * @param dateFormatType
	 * @return
	 * @throws CSException
	 * @author lihuijuan
	 * @date 2014-5-21 下午2:12:51
	 */
	public static Date getTheLastDayOfSpecifiedMonth(int specifiedYear, int specifiedMonth) {
		if (specifiedMonth > 12 || specifiedMonth < 1)
			throw new IllegalArgumentException("指定月份参数值错误");
		Calendar calendar = Calendar.getInstance();
		calendar.set(specifiedYear, specifiedMonth - 1, 1, 23, 59, 59);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 获取帐期最后一天
	 * 
	 * @param billCycle
	 * @return
	 * @throws ParseException
	 * @author zhaohongxing_cttic
	 * @date 2014-5-28 下午2:01:34
	 */
	public static String getTheLastDayOfMonth(String billCycle) throws ParseException {
		SimpleDateFormat cycleFormat = new SimpleDateFormat("yyyyMM");
		Date cycleDate = cycleFormat.parse(billCycle);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar ca = Calendar.getInstance();
		ca.setTime(cycleDate);
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(ca.getTime());
	}

	/**
	 * 获取当前日期月份最后一天
	 * 
	 * @param date
	 * @return
	 * @author KONGJF
	 * @date 2014-6-15 下午9:49:08
	 */
	public static String lastDayOfMonth(Date date, String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	/**
	 * 计算
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 * @author KONGJF
	 * @date 2014-6-15 下午10:30:29
	 */
	public static int betweenMonth(Date start, Date end) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		if (start.before(end)) {
			c1.setTime(start);
			c2.setTime(end);
		} else {
			c1.setTime(end);
			c2.setTime(start);
		}
		return (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12 + (c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH));
	}

	/**
	 * 获取当期月份+2.5月
	 * 
	 * @param billCycle
	 * @return
	 * @throws ParseException
	 * @author zhaohongxing_cttic
	 * @date 2014-6-28 下午2:23:19
	 */
	public static String getLate2MonDate(String billCycle, String _PATTERN) throws ParseException {
		Date billDate = DateUtil.strFormatDate(billCycle + "15", DateUtil.PATTERNYYYYMMDD);
		Calendar c = Calendar.getInstance();
		c.setTime(billDate);
		c.add(Calendar.MONTH, 2);
		String date = DateUtil.DateFormatStr(c.getTime(), _PATTERN);
		return date;
	}

	/**
	 * 获取当期月份+3.5月
	 * 
	 * @param billCycle
	 * @return
	 * @throws ParseException
	 * @author zhaohongxing_cttic
	 * @date 2014-6-28 下午2:23:19
	 */
	public static String getLate3MonDate(String billCycle, String _PATTERN) throws ParseException {
		Date billDate = DateUtil.strFormatDate(billCycle + "15", DateUtil.PATTERNYYYYMMDD);
		Calendar c = Calendar.getInstance();
		c.setTime(billDate);
		c.add(Calendar.MONTH, 3);
		String date = DateUtil.DateFormatStr(c.getTime(), _PATTERN);
		return date;
	}

	/**
	 * 在一个时间上加上对应的月份数
	 * 
	 * @param ti long
	 * @param i int
	 * @return Date
	 * @throws Exception
	 */
	public static Date addOrMinusMonth(long ti, int i) throws Exception {
		Date rtn = null;
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(ti);
		cal.setTime(date);
		cal.add(GregorianCalendar.MONTH, i);
		rtn = cal.getTime();
		return rtn;
	}

	/**
	 * 在一个时间上加上或减去天数
	 * 
	 * @param ti long
	 * @param i int
	 * @return Date
	 */
	public static Date addOrMinusDays(long ti, int i) {
		Date rtn = null;
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(ti);
		cal.setTime(date);
		cal.add(GregorianCalendar.DAY_OF_MONTH, i);
		rtn = cal.getTime();
		return rtn;
	}

	/**
	 * 在一个时间上加上或减去小时
	 * 
	 * @param ti long
	 * @param i int
	 * @return Date
	 */
	public static Date addOrMinusHours(long ti, int i) {
		Date rtn = null;
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(ti);
		cal.setTime(date);
		cal.add(GregorianCalendar.HOUR, i);
		rtn = cal.getTime();
		return rtn;
	}

	/**
	 * 在一个时间上加上或减去分钟
	 * 
	 * @param ti long
	 * @param i int
	 * @return Date
	 */
	public static Date addOrMinusMinutes(long ti, int i) {
		Date rtn = null;
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(ti);
		cal.setTime(date);
		cal.add(GregorianCalendar.MINUTE, i);
		rtn = cal.getTime();
		return rtn;
	}

	/**
	 * 在一个时间上加上或减去秒数
	 * 
	 * @param ti long
	 * @param i int
	 * @return Date
	 */
	public static Date addOrMinusSecond(long ti, int i) {
		Date rtn = null;
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(ti);
		cal.setTime(date);
		cal.add(GregorianCalendar.SECOND, i);
		rtn = cal.getTime();
		return rtn;
	}

	/**
	 * 求两个日期差
	 * 
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @return 两个日期相差天数
	 */
	public static long GetDateMargin(Date beginDate, Date endDate) {
		long margin = 0;
		margin = endDate.getTime() - beginDate.getTime();
		margin = margin / (1000 * 60 * 60 * 24);
		return margin;
	}

	/**
	 * 返回两个时间之间相差的差值，返回List
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author KONGJF
	 * @date 2014-7-16 下午5:14:19
	 */
	public static List<String> getBetweenMonthList(Date startDate, Date endDate, int calendarType, String pattern) {
		List<String> list = new ArrayList<String>();
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.setTime(startDate);
		end.setTime(endDate);
		while (!start.after(end)) {
			list.add(DateUtil.format(start.getTime(), pattern));
			start.add(calendarType, 1);
		}
		return list;
	}

	public static String formatToEnglish(Date date) {
		/*
		 * DateFormat df1 = new SimpleDateFormat("MMM dd, yyyy",Locale.ENGLISH);
		 * return DateUtil.DateFormatStr(df1.format(date));
		 */
		SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		return format.format(date);
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 * @author yangpengyan-CTTIC
	 * @date 2014年11月26日 上午11:42:44
	 */
	public static long getCurrentTimeMillis() {
		Date time = getServerDate();
		return time.getTime();
	}

}
