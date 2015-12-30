package cn.cttic.sysframe.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
@Service
@Scope("prototype")
public class FileTheadDeal implements Runnable {
		private Long fileId;
		@Autowired
		private BpFileDealTask bpFileDealTask;
		
	public	void FileTheadDeal(Long fileId){
			this.fileId=fileId;
		}
	
	@Override
	public void run() {
		System.out.println("***********正在执行文件ID*********"+fileId);
		bpFileDealTask.dealFile(fileId);
		System.out.println("***********执行完成文件ID*********"+fileId);
	}
	
}
