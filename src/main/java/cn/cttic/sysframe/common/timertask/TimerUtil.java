package cn.cttic.sysframe.common.timertask;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.domain.SmTimerTaskDef;

public class TimerUtil {

	/** 存放定时任务缓存信息 */
	public static Map<String, List<Map>> sysFrameTimerMap = new HashMap<String, List<Map>>();

	public static final String TIMER_OBJECT = "TIMER_OBJECT";
	public static final String UPDATE_TIME = "UPDATE_TIME";
	public static final String PRE_RUN_TIME = "PRE_RUN_TIME";
	public static final String CHILD_OBJECT = "CHILD_OBJECT";
	public static final String START_TIME = "START_TIME";
	public static final String SYNC_TIME_FORMAT = "SYNC_TIME_FORMAT";

	public static final int TIMER_TASK_PERIOD_SPECIAL = 7;

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 * @author
	 */
	public static boolean isBlank(String str) {
		if (null == str) {
			return true;
		}
		if ("".equals(str.trim())) {
			return true;
		}

		return false;
	}

	public static Object getImplObjectForReflection(String implMeth)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		if (implMeth == null || implMeth.length() == 0) {
			return null;
		}
		Object obj = null;
		Class clazz = Class.forName(implMeth);
		obj = clazz.newInstance();
		return obj;
	}
	public static Object getImplObjectForSpring(String implMeth)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		if (implMeth == null || implMeth.length() == 0) {
			return null;
		}
		Object obj = null;
		Class clazz = Class.forName(implMeth);
		obj=SpringContextUtil.getBean(clazz);
		return obj;
	}

	/**
	 * 获取定时器首次执行时间 syncTimeFormat 定时时间 月,周,日,时,分,秒
	 */
	public static Date getFirstTime(String syncTimeFormat, int task_type,
			int period_type) {

		Date date = new Date();
		if (!isBlank(syncTimeFormat)) {
			if (period_type == TIMER_TASK_PERIOD_SPECIAL) {
				// 时分秒 例如:20:30:40
				String[] dateArray = syncTimeFormat.split(":");
				String hours = dateArray[0];
				String minutes = dateArray[1];
				String seconds = dateArray[2];

				if (!isBlank(hours)) {
					date.setHours(new Long(hours).intValue());
				}
				if (!isBlank(minutes)) {
					date.setMinutes(new Long(minutes).intValue());
				}
				if (!isBlank(seconds)) {
					date.setSeconds(new Long(seconds).intValue());
				}

			} else {
				// 年,月,周,日,时,分,秒
				String[] dateArray = syncTimeFormat.split(",");

				String year = dateArray[0];
				String month = dateArray[1];
				String week = dateArray[2];
				String day = dateArray[3];
				String hours = dateArray[4];
				String minutes = dateArray[5];
				String seconds = dateArray[6];

				if (!isBlank(year)) {
					date.setYear(new Long(year).intValue() - 1900);
				}
				if (!isBlank(month)) {
					date.setMonth(new Long(month).intValue() - 1);
				}
				if (!isBlank(week)) {

					int currentWeek = getWeek();
					int dayInterval = currentWeek - new Long(week).intValue();
					if (dayInterval < 0)
						dayInterval = dayInterval + 7;

					int currentDay = getDay();
					date.setDate(currentDay + dayInterval);
				} else if (!isBlank(day)) {
					date.setDate(new Long(day).intValue());
				}
				if (!isBlank(hours)) {
					date.setHours(new Long(hours).intValue());
				}
				if (!isBlank(minutes)) {
					date.setMinutes(new Long(minutes).intValue());
				}
				if (!isBlank(seconds)) {
					date.setSeconds(new Long(seconds).intValue());
				}
			}
		}
		return date;
	}

	/**
	 * 获取定时周期 syncTimeFormat 定时时间 月,周,日,时,分,秒
	 */
	public static long getPeriod(String syncTimeFormat) {

		long period = 0;
		// 年,月,周,日,时,分,秒
		if (!isBlank(syncTimeFormat)) {
			String[] dateArray = syncTimeFormat.split(",");

			// String year = dateArray[0];
			// String month = dateArray[1];
			// String week = dateArray[2];
			// String day = dateArray[3];
			String hours = dateArray[4];
			String minutes = dateArray[5];
			String seconds = dateArray[6];

			if (!isBlank(hours)) {
				period = 24 * 60 * 60 * 1000;
			} else if (!isBlank(minutes)) {
				period = 60 * 60 * 1000;
			} else if (!isBlank(seconds)) {
				period = 60 * 1000;
			}
		}
		return period;
	}

	/**
	 * 判断定时器是否可被该服务执行 不适合于复杂第二周期
	 * 
	 */
	public static boolean isTimerRun(SmTimerTaskDef taskInfo, Map taskMap,
			Date run_date, boolean isComplex, String syncTimeFormat) {

		boolean run = false;

		if (!isComplex) {

			List<Map> mapList = TimerUtil.sysFrameTimerMap.get(taskInfo
					.getTaskId().toString());
			if(mapList!=null && mapList.size()>0){
				for (Map taskInfoMap : mapList) {
					String syncTimeFormatTemp = (String) taskInfoMap
							.get(SYNC_TIME_FORMAT);
					if (!isBlank(syncTimeFormat)
							&& syncTimeFormat.equals(syncTimeFormatTemp)) {
						Date start_time = (Date) taskInfoMap
								.get(TimerUtil.START_TIME);
						if (start_time.getTime() > run_date.getTime()) {
							return run;
						}
					}
				}
			}
		}
		// 如果是复杂周期子任务,判断子任务是否启动 如果没有启动则直接先启动.
		if (isComplex) {
			List<Map> mapList = TimerUtil.sysFrameTimerMap.get(taskInfo
					.getTaskId().toString());
			if(mapList==null||mapList.size()<1){
				return true;
			}
			for (Map taskInfoMap : mapList) {
				String syncTimeFormatTemp = (String) taskInfoMap
						.get(SYNC_TIME_FORMAT);
				if (!isBlank(syncTimeFormat)
						&& syncTimeFormat.equals(syncTimeFormatTemp)) {
					Timer timer = (Timer) taskInfoMap
							.get(TimerUtil.CHILD_OBJECT);
					if (timer == null) {
						return true;
					}
				}
			}
		}
		if (!isTimerRunDate(taskInfo.getSyncTimeFormat(),
				taskInfo.getTaskType(), taskInfo.getPeriodType())) {
			return run;
		}
		run = isTimerRunAct(taskInfo, syncTimeFormat);
		return run;
	}

	/**
	 * 判断定时器是否为设定执行时间 syncTimeFormat 定时时间 月,周,日,时,分,秒
	 * 
	 */
	public static boolean isTimerRunDate(String syncTimeFormat) {

		boolean run = false;
		if (!isBlank(syncTimeFormat)) {
			String[] dateArray = syncTimeFormat.split(",");

			String year = dateArray[0];
			String month = dateArray[1];
			String week = dateArray[2];
			String day = dateArray[3];
			// String hours=dateArray[3];
			// String minutes=dateArray[4];
			// String seconds=dateArray[5];

			// 按星期执行 星期优先
			if (!isBlank(week)) {
				if (new Integer(week).intValue() == getWeek()) {
					run = true;
				} else {
					return false;
				}
			} else {
				// 一次性执行时间
				if (!isBlank(year)) {
					if (new Integer(year).intValue() == getYear()
							&& !isBlank(month)
							&& new Integer(month).intValue() == getMonth()
							&& !isBlank(day)
							&& new Integer(day).intValue() == getDay()) {

						run = true;
					}
				}
				// 每月
				else if (!isBlank(month)) {
					if (new Integer(month).intValue() == getMonth()
							&& !isBlank(day)
							&& new Integer(day).intValue() == getDay()) {
						run = true;
					}
				}
				// 每日
				else {
					// 每日
					if (!isBlank(day)) {
						if (new Integer(day).intValue() == getDay()) {
							run = true;
						}
					} else {
						run = true;
					}
				}
			}
		}
		return run;
	}

	/**
	 * 判断定时器是否为设定执行时间 syncTimeFormat 定时时间 月,周,日,时,分,秒 用于特殊类型
	 * 
	 */
	public static boolean isTimerRunDate(String syncTimeFormat, int task_type,
			int period_type) {

		boolean run = false;
		if (!isBlank(syncTimeFormat)) {
			String[] dateArray = syncTimeFormat.split(",");

			String year = dateArray[0];
			String month = dateArray[1];
			String weekStr = dateArray[2];
			String day = dateArray[3];
			// String hours=dateArray[3];
			// String minutes=dateArray[4];
			// String seconds=dateArray[5];

			// 当前日期
			int currentDay = getDay();
			int currentMonth = getMonth();
			int currentWeek = getWeek();

			// 判断月份是否是可执行月份
			if (!isBlank(month)) {
				boolean currentMonthFlag = false;
				String[] monthInfo = month.split(";");
				for (int m = 0; m < monthInfo.length; m++) {
					if (new Integer(monthInfo[m]).intValue() == currentMonth) {
						currentMonthFlag = true;
						break;
					}
				}
				if (!currentMonthFlag) {
					return currentMonthFlag;
				}
			}

			// 星期与日都配置 按星期执行 星期优先
			if (!isBlank(weekStr)) {
				String[] weeks = weekStr.split("#");
				if (weeks.length > 0 && !isBlank(weeks[0])) {
					String[] weekInfo = weeks[0].split(";");
					for (int j = 0; j < weekInfo.length; j++) {

						if (new Integer(weekInfo[j]).intValue() == currentWeek) {

							if (weeks.length > 1 && !isBlank(weeks[1])) {
								String[] weekNum = weeks[1].split(";");
								for (int i = 0; i < weekNum.length; i++) {

									if (isBlank(weekNum[i])) {
										run = true;
										break;
									}
									// 最后一个星期几
									else if (new Integer(weekNum[i]).intValue() == 7) {
										if (getEndWeekOfMonth(new Integer(
												weekInfo[j]).intValue()) == currentDay) {
											run = true;
											break;
										}
									} else if (getWeekOfMonth(new Integer(
											weekNum[i]).intValue(),
											new Integer(weekInfo[j]).intValue()) == currentDay) {
										run = true;
										break;
									}
								}
								if (run) {
									break;
								}
							} else {
								run = true;
								break;
							}
						}
					}

				} else {
					// 指定具体哪周执行,被指定的周每天都执行
					if (weeks.length > 1 && !isBlank(weeks[1])) {
						String[] weekNum = weeks[1].split(";");
						for (int i = 0; i < weekNum.length; i++) {

							if (isBlank(weekNum[i])) {
								run = true;
								break;
							}
							// 最后一个星期几
							else if (new Integer(weekNum[i]).intValue() == 7) {

								// 该类型需要配置星期,否则不做处理
								// if (getEndWeekOfMonth(new
								// Integer(weekInfo[j]).intValue()) ==
								// currentDay) {
								// run=true;
								// break;
								// }
							} else {
								for (int m = 0; m < 7; m++) {
									if (getWeekOfMonth(
											new Integer(weekNum[i]).intValue(),
											m) == currentDay) {
										run = true;
										break;
									}
								}
								if (run) {
									break;
								}
							}
						}
					}
				}
			} else {
				// 每日
				if (!isBlank(day)) {
					String[] dayInfo = day.split(";");
					for (int i = 0; i < dayInfo.length; i++) {
						if (new Integer(dayInfo[i]).intValue() == currentDay
								|| (new Integer(dayInfo[i]).intValue() == 32 && getEndDayOfMonth(1) == currentDay)) {
							run = true;
							break;
						}
					}
				} else {
					run = true;
				}
			}
		}
		return run;
	}

	/**
	 * 判断是否可执行 不适合于复杂第二周期
	 * 
	 */
	public static boolean isTimerRunAct(SmTimerTaskDef taskInfo,
			String syncTimeFormat) {

		boolean run = false;

		if (taskInfo.getPreRunTime() == null
				|| isBlank(taskInfo.getPreRunTime().toString())) {
			run = true;
		} else {
			Date preDate = taskInfo.getPreRunTime();
			Date currentDate = runActTime(taskInfo, syncTimeFormat);
			if (currentDate.getTime() - preDate.getTime() > 0) {
				run = true;
			}
		}
		return run;
	}

	/**
	 * 
	 * 不适合于复杂第二周期 获取执行时间
	 * 
	 */
	public static Date runActTime(SmTimerTaskDef taskInfo, String syncTimeFormat) {

		Date date = null;
		if (taskInfo.getPreRunTime() == null
				|| isBlank(taskInfo.getPreRunTime().toString())) {
			// 运行时间及第一次开始时间
			date = getFirstTime(syncTimeFormat, taskInfo.getTaskType(),
					taskInfo.getPeriodType());
		} else {

			date = new Date();

			if (taskInfo.getPeriodType() == TIMER_TASK_PERIOD_SPECIAL) {
				String[] dateArray = syncTimeFormat.split(":");
				String hours = dateArray[0];
				String minutes = dateArray[1];
				String seconds = dateArray[2];

				if (!isBlank(hours)) {
					date.setHours(new Long(hours).intValue());
				}
				if (!isBlank(minutes)) {
					date.setMinutes(new Long(minutes).intValue());
				}
				if (!isBlank(seconds)) {
					date.setSeconds(new Long(seconds).intValue());
				}
			} else {
				// 周期类型:1.非周期类、2.小时、3.天、4.周、5.月、6.年
				// 获取当前时间,用设定时间的十分秒替换
				if (!isBlank(syncTimeFormat)) {
					// 年,月,周,日,时,分,秒
					String[] dateArray = syncTimeFormat.split(",");

					String hours = dateArray[4];
					String minutes = dateArray[5];
					String seconds = dateArray[6];

					if (!isBlank(hours)) {
						date.setHours(new Long(hours).intValue());
					}
					if (!isBlank(minutes)) {
						date.setMinutes(new Long(minutes).intValue());
					}
					if (!isBlank(seconds)) {
						date.setSeconds(new Long(seconds).intValue());
					}
				}
				// }
			}
		}
		return date;
	}

	/**
	 * 
	 * 适用于 复杂第二周期 作废方法 获取执行时间
	 * 
	 */
	// public static Date runActComplexTime(TimerTaskDefine taskInfo) {
	//
	// Date date = new java.sql.Date(taskInfo.getPre_run_time().getTime());
	// Date newDate=new Date();
	// long time=date.getTime()+taskInfo.getRun_time_interval() * 60 * 1000;
	// newDate.setTime(time);
	// return newDate;
	// }
	/**
	 * 判断是否可执行 适合于复杂第二周期
	 * 
	 */
	public static boolean isTimerRunActComplex(SmTimerTaskDef taskInfo,
			Map taskMap, Date run_date, String syncTimeFormat) {

		boolean run = false;

		List<Map> mapList = TimerUtil.sysFrameTimerMap.get(taskInfo.getTaskId()
				.toString());
		if(mapList!=null&&mapList.size()>0){
			for (Map taskInfoMap : mapList) {
				String syncTimeFormatTemp = (String) taskInfoMap
						.get(SYNC_TIME_FORMAT);
				if (!isBlank(syncTimeFormat)
						&& syncTimeFormat.equals(syncTimeFormatTemp)) {
	
					Date start_time = (Date) taskInfoMap.get(TimerUtil.START_TIME);
					if (start_time.getTime() > run_date.getTime()) {
						return run;
					}
	
					if (taskInfo.getPreRunTime() == null
							|| isBlank(taskInfo.getPreRunTime().toString())) {
						run = true;
					} else {
						Date preDate = taskInfo.getPreRunTime();
						// Date currentDate = runActComplexTime(taskInfo);
						long interval = run_date.getTime() - preDate.getTime()
								- taskInfo.getRunTimeInterval() * 60 * 1000;
						if (interval >= 0) {
							run = true;
						}
					}
				}
			}
		}
		return run;
	}

	/**
	 * 返回当前时间是星期几 返回几就是星期几
	 * 
	 * @return
	 */
	public static int getWeek() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK);
		// week中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		if (week > 1) {
			week--;
		} else {
			week = 7;
		}
		return week;
	}

	/**
	 * 返回当前时间 年份
	 * 
	 * 
	 * @return
	 */
	public static int getYear() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 返回当前时间月份
	 * 
	 * 
	 * @return
	 */
	public static int getMonth() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		return month + 1;
	}

	/**
	 * 返回当前时间日期
	 * 
	 * 
	 * @return
	 */
	public static int getDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 返回当前小时
	 * 
	 * 
	 * @return
	 */
	public static int getHours() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int hours = c.get(Calendar.HOUR_OF_DAY);
		return hours;
	}

	/**
	 * 返回当前分
	 * 
	 * 
	 * @return
	 */
	public static int getMinutes() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int minutes = c.get(Calendar.MINUTE);
		return minutes;
	}

	/**
	 * 返回当前秒
	 * 
	 * @return
	 */
	public static int getSeconds() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int seconds = c.get(Calendar.SECOND);
		return seconds;
	}

	/**
	 * 停止定时器
	 * 
	 * @return
	 * @return
	 */
	public static String stopTimer(String taskId, boolean isChild,
			String syncTimeFormat) {

		String msg = "";

		List<Map> mapList = TimerUtil.sysFrameTimerMap.get(taskId);
		if (mapList != null && mapList.size() > 0) {

			for (int i = 0; i < mapList.size(); i++) {
				Map map = (Map) mapList.get(i);

				if (map != null) {
					String syncTimeFormatTemp = (String) map
							.get(SYNC_TIME_FORMAT);
					if (isBlank(syncTimeFormat)
							|| syncTimeFormat.equals(syncTimeFormatTemp)) {
						if (map != null) {
							if (isChild) {
								try {
									Timer timer = (Timer) map
											.get(TimerUtil.CHILD_OBJECT);
									if (timer != null) {
										timer.cancel();
									}
									map.put(TimerUtil.CHILD_OBJECT, null);
								} catch (Exception e) {
									msg = msg + " " + e.getMessage();
								}
							} else {
								try {
									Timer timer = (Timer) map
											.get(TimerUtil.TIMER_OBJECT);
									if (timer != null) {
										Timer timerChild = (Timer) map
												.get(TimerUtil.CHILD_OBJECT);
										if (timerChild != null) {
											timerChild.cancel();
										}
										timer.cancel();
									}
									mapList.remove(map);
								} catch (Exception e) {
									msg = msg + " " + e.getMessage();
								}
							}
						}
					}
					if (mapList == null || mapList.size() < 1) {
						TimerUtil.sysFrameTimerMap.remove(taskId);
					}
				}
			}
		}
		return msg;
	}

	/**
	 * 判断是否润年
	 * 
	 * @param 年
	 *            例:2000
	 * @return
	 */
	public static boolean isLeapYear(int year) {

		/**
		 * 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 获取一个月的倒数第几天的日期
	 * 
	 * @param delay
	 *            天数,倒数第二天则 delay=2
	 * @return
	 */
	public static int getEndDayOfMonth(int delay) {

		int mon = getMonth();
		int day = 0;
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
				|| mon == 10 || mon == 12) {
			day = 31;
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			day = 30;
		} else {
			if (isLeapYear(getYear())) {
				day = 29;
			} else {
				day = 28;
			}
		}
		return day - delay + 1;
	}

	/**
	 * 每月最后一个星期几 1 星期一,2星期二 ..... 7星期天 返回日期 1-31
	 * 
	 * @return
	 */
	public static int getEndWeekOfMonth(int week) {

		if (week <= 0 || week > 7) {
			return 0;
		}

		int lastDay = getEndDayOfMonth(1);
		Date date = new Date();
		date.setDate(lastDay);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int lastDayWeek = c.get(Calendar.DAY_OF_WEEK);

		// week中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		if (lastDayWeek > 1) {
			lastDayWeek--;
		} else {
			lastDayWeek = 7;
		}

		int dayInterval = lastDayWeek - week;
		if (dayInterval < 0)
			dayInterval = dayInterval + 7;
		return lastDay - dayInterval;
	}

	/**
	 * num 周次 1-6 week 星期几 1 星期一,2星期二 ..... 7星期天 返回日期 1-31
	 * 
	 * @return
	 */
	public static int getWeekOfMonth(int num, int week) {

		if (week <= 0 || week > 7) {
			return 0;
		} else if (num <= 0 || num > 6) {
			return 0;
		}

		Date date = new Date();
		date.setDate(1);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int firstWeek = c.get(Calendar.DAY_OF_WEEK);

		// week中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		if (firstWeek > 1) {
			firstWeek--;
		} else {
			firstWeek = 7;
		}

		int dayInterval = week - firstWeek;
		if (dayInterval < 0) {
			dayInterval = dayInterval + 7;
			num = num - 1;
		}

		int dayOfMonth = 1 + dayInterval + (num - 1) * 7;
		if (dayOfMonth > getEndDayOfMonth(1)) {
			return 0;
		} else if (dayOfMonth < 0) {
			return 0;
		}
		return dayOfMonth;
	}
}
