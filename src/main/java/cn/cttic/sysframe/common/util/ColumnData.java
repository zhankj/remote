package cn.cttic.sysframe.common.util;

import java.util.HashMap;
import java.util.Map;



public class ColumnData {
	
	private String[] fieldArray ;
	
	private String[] titleArray ;
	
	private Map<String,String> typeCodeMap ;
	
	public Map<String, String> getTypeCodeMap() {
		return typeCodeMap;
	}

	public void setTypeCodeMap(Map<String, String> typeCodeMap) {
		this.typeCodeMap = typeCodeMap;
	}

	
	
	public ColumnData() {
		
    }
	
		
	
	public ColumnData(String str) {
		typeCodeMap = new HashMap<String, String>();
		
		String[] arr = str.split("\\|");
		titleArray = arr[1].split(",");
		
		String[] original = arr[0].split(",");
		fieldArray = new String[titleArray.length];
		for(int i=0 ; i<original.length ; i++){
			String[] temp = original[i].split(":");
			if(temp.length == 2){
				typeCodeMap.put(temp[0], temp[1]);
			}
			fieldArray[i] = temp[0];
		}
    }

	
    public String[] getFieldArray() {
    	return fieldArray;
    }

	
    public void setFieldArray(String[] fieldArray) {
    	this.fieldArray = fieldArray;
    }

	
    public String[] getTitleArray() {
    	return titleArray;
    }

	
    public void setTitleArray(String[] titleArray) {
    	this.titleArray = titleArray;
    }

	public ColumnData(String[] fieldList, String[] titleList) {
	    super();
	    this.fieldArray = fieldList;
	    this.titleArray = titleList;
    }
	
	
	

}
