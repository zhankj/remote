package cn.cttic.sysframe.frame.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.domain.BatFile;
import cn.cttic.sysframe.frame.service.BatFileService;
import cn.cttic.sysframe.frame.service.FtpService;
import cn.cttic.sysframe.frame.service.SmJobLogService;

@Service
public class TestServiceImpl implements BatFileService {

	private transient static Log TestServiceImpl = LogFactory.getLog(TestServiceImpl.class);

	@Autowired
	private SmJobLogService SmJobLogService;

	@Autowired
	private FtpService FtpService;

	@Override
	public int[] addDealBat(BatFile batile) throws Exception {

		int[] back = new int[2];
		back[0] = 50;
		back[1] = 1;
		try {
			// ftp登陆
			FtpService.FtpUtils(batile.getFtpPathCode());
			// 将ftp的文件下载到本地
			FtpService.download(batile.getFileName(), batile.getFileName());
			// 获取本地路径
			String localPath = FtpService.getSmFtpPath().getLocalPath();

			File file = new File(localPath + "/" + batile.getFileName());
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String tempString = null;
			while ((tempString = br.readLine()) != null) {
				// 处理文件
				System.out.println(tempString);

				// 记录日志
				// SmJobLogService.insertSelective(batile.getFileId(),
				// "错误描述/成功描述");

			}
			br.close();
		}
		catch (Exception e) {
			TestServiceImpl.error(e);
			throw e;
		}
		finally {
			FtpService.close();

		}

		return back;
	}

	// 执行调用方法。
	public static void main(String[] args) {
		// 这个xml文件是Spring配置beans的文件，顺带一提，路径 在整个应用的根目录
		SpringContextUtil a = new SpringContextUtil();
		a.setApplicationContext(new ClassPathXmlApplicationContext(new String[] { "boss-data.xml", "boss-service.xml", "boss-servlet.xml" }));
		BpFileDealTask myBean = (BpFileDealTask) SpringContextUtil.getBean(BpFileDealTask.class);
	//	myBean.dealFile();
	}

}
