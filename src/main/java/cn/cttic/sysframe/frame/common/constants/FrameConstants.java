package cn.cttic.sysframe.frame.common.constants;

import java.text.SimpleDateFormat;

/**
 * 框架常量定义类 <br>
 * 定义框架功能中使用的常量
 * 
 * @author dong
 * @date 2014年4月25日 上午11:13:41 <br>
 *       Copyright: Copyright (c) 2014 CTTIC
 */
public class FrameConstants {

	/*
	 * 菜单
	 */
	public static class SmMenu {

		/*
		 * 菜单状态
		 */
		public static class State {

			// 0-可用
			public final static String USABLE = "0";

			// 1-不可用
			public final static String UNUSABLE = "1";
		}
		
		/*
		 * 菜单类型
		 */
		public static class MenuType {

			// 0-菜单目录
			public final static String CATALOG = "0";

			// 1-功能菜单
			public final static String FUNCTION = "1";
			
			// 1-功能子菜单
			public final static String SUBFUNCTION = "2";
		}
	}

	/*
	 * 组织机构
	 */
	public static class SmOrgan {

		/*
		 * 组织机构状态
		 */
		public static class State {

			// 有效
			public final static String VALID = "Y";

			// 无效
			public final static String INVALID = "N";
		}
	}

	/*
	 * 人员
	 */
	public static class SmStaff {

		/*
		 * 人员状态
		 */
		public static class StaffStatus {

			// 正常
			public final static String NORMAL = "Y";

			// 失效
			public final static String CANCEL = "N";
		}
	}
	
	/*
	 * 登录日志表
	 */
	public static class SmLogonLog {

		/*
		 * 登录类型
		 */
		public static class LogonType {

			// 正常登出
			public final static String NORMAL_OUT = "Q";

			// 正常登录
			public final static String NORMAL_IN = "Y";
			
			// session失效登出
			public final static String SESSION_OUT = "S";
			
			// 不规范登出
			public final static String UNNORMAL_OUT = "E";
		}
		
		/*
		 * 登录结果
		 */
		public static class ResultFlag {
			
			//成功
			public final static Short SUCCESS = 0;

			//失败
			public final static Short FAIL = 1;
		}
	}
	
	/*
	 * 登录表
	 */
	public static class SmLogon {

		
		/*
		 * 登录结果
		 */
		public static class LogonStatus {
			
			//在线
			public final static String ONLINE = "Y";

			//离线
			public final static String OUTLINE = "N";
		}
	}
	
	

	/*
	 * 工号
	 */
	public static class SmOper {

		// 超级工号
		public static final String ADMIN = "1";
		
		/*
		 * 工号状态
		 */
		public static class State {

			// 正常
			public final static String NORMAL = "Y";

			// 禁用
			public final static String CANCEL = "N";
		}
		
		/*
		 * 工号级别
		 */
		public static class OperLevel {

			//总部
			public final static String ZB = "00";

			//省份
			public final static String SF = "01";
			
			//地市
			public final static String DS = "02";

			//区县
			public final static String QX = "03";
		}
	}
	/*
	 * 功能集
	 */
	public static class SmFsSet {

		/*
		 * 功能集状态
		 */
		public static class State {

			// 正常
			public final static String NORMAL = "Y";

			// 禁用
			public final static String CANCEL = "N";
		}
	}
	
	public static class SmRegion {
		
		// 总部
		public final static String ZB = "09";
		
		// 集团/省中心
		public final static String PROVINCE_CENTER = "000";
		
		// 地市中心
		public final static String AREA_CENTER = "00000";
	}
	
	public static class TimerTask {
		
		
		// 定时任务状态，Y：有效；N：无效
		public final static String TASK_STATUS_Y = "Y";
		public final static String TASK_STATUS_N = "N";
	}
	
	public static class SmRegisteredService{
		
		// 实例类型   1，四层交换使用；2普通使用
		public final static String S_TYPE_SWITCH = "1";
		public final static String S_TYPE_INSTANCE = "2";
		
		//网络类型 B、P,.分别代表B域  公网(外网)
		public final static String NET_TYPE_B = "B";
		public final static String NET_TYPE_P = "P";
		
		//实例标识状态  1.未被标识2.已被标识 3.标识错误
		public final static String INST_IDENTIFY_STS_N = "1";
		public final static String INST_IDENTIFY_STS_Y = "2";
		public final static String INST_IDENTIFY_STS_E = "3";
	}
	
	public static class SmSendmailLog {
		
		// 失败重试次数
		public final static Short FAILURE_RETRY_NUM = 3;
		
		/*
		 * 发送类型
		 */
		public class SendType {
			
			// 0-立即
			public final static String IMMEDIATELY = "0";
			// 1-延时
			public final static String DELAY = "1";
		}
		
		/*
		 * 发送状态
		 */
		public class State {
			
			// 0-未发送 
			public final static String UNDO = "0";
			// 1-发送成功 
			public final static String SUCCESS = "1";
			// 2-发送失败
			public final static String FAILURE = "2";
		}
	}
	
	/*
	 * 数据权限
	 */
	public static class DataRight {
		
	  /**数据权限定义****************************************************/
		//数据权限分配目标  1.工号 2.功能集 3.所有
		public final static int CONFIG_AIM_OPER = 1;
		public final static int CONFIG_AIM_FS = 2;
		public final static int CONFIG_AIM_ALL = 3;
		
	  /**数据权限分配****************************************************/
		public final static String objectId = "dataId";
	
		public final static String objectValue = "dataValue";
	
		public final static String isDisplay = "isDisplay";  //是否展示 Y 展示 N不展示
		public final static String isDisplay_Y = "Y"; 
		public final static String isDisplay_N = "N"; 
		
		public final static String displayId = "ID";
		public final static String displayName = "displayName";
		
		public final static String isDistinct = "isDistinct";  //是否Distinct Y  N 
		public final static int isDistinct_Y = 2;
		public final static int isDistinct_N = 1;
		
		public final static String fuzzyQry = "isFuzzy"; // 是否模糊查询 1 精确查询 2 模糊查询
		public final static String fuzzyQry_N = "1"; 
		public final static String fuzzyQry_Y = "2"; 
	
		public final static String qryType = "qryType"; // 查询类型 1 输入框 2 选择框
		public final static String qryType_I = "1"; 
		public final static String qryType_S = "2"; 
		
		public final static String parentId ="parentId";//选择框联动父组件
		public final static String childId ="childId";    //选择框联动下级组件
		public final static String clearId ="clearId";    //选择框联动下级组件
		
		public final static String sysId="sysId";  
	    public final static String DATA_RIGHT_SYSPROVINCEID = "sysProvinceId"; 
	    public final static String DATA_RIGHT_SYSAREAID = "sysAreaId"; 
	    public final static String DATA_RIGHT_SYSCOUNTYID = "sysCountyId"; 
	    public final static String DATA_RIGHT_SYSOBJECTID = "sysObjectId"; 
//	    public final static String DATA_RIGHT_SYSORGID = "sysOrgId"; 
//	    public final static String DATA_RIGHT_SYSORGTYPE = "sysOrgType"; 
//	    public final static String DATA_RIGHT_SYSREGIONID = "sysRegionId"; 

		public final static String qrySql = "qrySql";  //下拉框 sql语句
		public final static String tabPrefix="b.";
		public final static String qryId="qryId";
		public final static String selDatas="selDatas";
		public final static String sysExecSql="sysExecSql";  //与SmDataRightMapper.xml commQuery 中参数对应
		
		//结果区参数配置 
		public final static String selFlag_select = "select"; //可选区
		public final static String selFlag_selected = "selected"; //已选区
		
		public final static String headJOSNArray = "headJOSNArray"; //结果区表头集合
		
		public final static String mustId = "Id";
		public final static String resultSqlQry = "sqlQry";    //结果区结果集过滤条件
		public final static String resultSqlOrder ="sqlOrder"; //结果区结果集合并
		public final static String resultSqlData="sqlData";    //结果区查询sql
		public final static String resultDataCode="dataCode"; 
		public final static String resultObjId="objId";       
		
	}
	//批量处理配置
	public static class BatchDeal {
		public final static int DEAL_NOTE=1006;//  批量处理短信
	}
	public final static SimpleDateFormat SDF_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public final static SimpleDateFormat SDF_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	
	public final static String IN_VALID_TIME = "2099-12-31 23:59:59";
	
	
}
