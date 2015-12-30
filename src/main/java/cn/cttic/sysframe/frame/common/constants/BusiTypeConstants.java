package cn.cttic.sysframe.frame.common.constants;


public enum BusiTypeConstants {

		BUSI_TYPE_1("000001", "业务一"),
		BUSI_TYPE_2("000002", "业务二");
		
		private String code;
		
		private String desc;
		
		private BusiTypeConstants(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		
		public String getCode() {
			return code;
		}
		
		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	
}
