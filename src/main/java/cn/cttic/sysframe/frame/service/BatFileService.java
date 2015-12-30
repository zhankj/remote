package cn.cttic.sysframe.frame.service;

import cn.cttic.sysframe.frame.domain.BatFile;

public abstract interface  BatFileService {
	/**
	 * @throws Exception 
	 * 
	 
	* @Title: BatFileService
	 
	* @Description: TODO(批量处理统一接口)
	 
	* @param @param file
	* @param @return    设定文件
	 
	* @return    返回值  String[]数据是一个长度为2的数据，String[0] 成功数量，String[1]失败数量
	 
	* @throws
	 */
	public  int[] addDealBat(BatFile file) throws Exception; 

}
