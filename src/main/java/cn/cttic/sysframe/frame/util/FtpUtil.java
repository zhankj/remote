package cn.cttic.sysframe.frame.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.ftp.ConcurrentCapacity;
import cn.cttic.sysframe.frame.domain.SmFtp;
import cn.cttic.sysframe.frame.domain.SmFtpPath;
import cn.cttic.sysframe.frame.service.SmFtpPathService;
import cn.cttic.sysframe.frame.service.SmFtpService;

@Service
public class FtpUtil {

	@Autowired
	private SmFtpPathService smFtpPathService;

	@Autowired
	private SmFtpService smFtpService;

	public static final int BIN = 0;

	public static final int ASC = 1;

	private FTPClient client = null;

	private String localPath = null;

	private String remotePathHis = null;

	private String ftpPathCode = null;

	private static int TIMEOUT_SECONDS = 120;

	private static int CONCURRENT_CAPACITY = 10;

	private static int CONCURRENT_ACQUIRE_TIMEOUT_SECONDS = 3;

	private static ConcurrentCapacity SEM = null;

	public FtpUtil() throws Exception {
	}

	public void setFtpPathCode(String ftpPathCode) throws Exception {
		List<SmFtpPath> smFtpPathList = smFtpPathService.getSmFtpPath(ftpPathCode);
		SmFtpPath[] smFtpPath = smFtpPathList.toArray(new SmFtpPath[0]);

		List<SmFtp> smFtpList = smFtpService.getSmFtp(smFtpPath[0].getFtpCode());
		SmFtp[] smFtp = smFtpList.toArray(new SmFtp[0]);

		this.ftpPathCode = ftpPathCode;

		this.client = new FTPClient();
		this.client.setDefaultTimeout(TIMEOUT_SECONDS * 1000);

		boolean remoteVerificationEnabled = true;

		String ip = smFtp[0].getHostIp();
		if (ip.startsWith("{PASV}")) {
			remoteVerificationEnabled = false;
			ip = StringUtils.substringAfter(ip, "{PASV}");
		} else if (ip.startsWith("{PORT}")) {
			remoteVerificationEnabled = true;
			ip = StringUtils.substringAfter(ip, "{PORT}");
		}

		this.client.connect(ip, smFtp[0].getPort());
		this.client.setRemoteVerificationEnabled(remoteVerificationEnabled);
		this.client.login(smFtp[0].getUsername(), smFtp[0].getPassword());
		this.client.changeWorkingDirectory(wrapper(smFtpPath[0].getRemotePath()));

		this.localPath = smFtpPath[0].getLocalPath();
		this.remotePathHis = smFtpPath[0].getRemotePathHis();
	}

	public void getFtpUtil(String ftpCode, String remotePath) throws Exception {
		List<SmFtp> smFtpList = smFtpService.getSmFtp(ftpCode);
		SmFtp[] smFtp = smFtpList.toArray(new SmFtp[0]);

		this.client = new FTPClient();
		this.client.setDefaultTimeout(TIMEOUT_SECONDS * 1000);

		boolean remoteVerificationEnabled = true;

		String ip = smFtp[0].getHostIp();
		if (ip.startsWith("{PASV}")) {
			remoteVerificationEnabled = false;
			ip = StringUtils.substringAfter(ip, "{PASV}");
		} else if (ip.startsWith("{PORT}")) {
			remoteVerificationEnabled = true;
			ip = StringUtils.substringAfter(ip, "{PORT}");
		}

		this.client.connect(ip, smFtp[0].getPort());
		this.client.setRemoteVerificationEnabled(remoteVerificationEnabled);
		this.client.login(smFtp[0].getUsername(), smFtp[0].getPassword());
		this.client.changeWorkingDirectory(wrapper(remotePath));

	}

	public SmFtpPath getSmFtpPath() throws Exception {
		List<SmFtpPath> smFtpPathList = smFtpPathService.getSmFtpPath(ftpPathCode);
		return smFtpPathList.toArray(new SmFtpPath[0])[0];
	}

	private String wrapper(String str) throws Exception {
		return new String(str.getBytes(), "ISO-8859-1");
	}

	public void setEncoding(String encoding) {
		this.client.setControlEncoding(encoding);
	}

	public void bin() throws Exception {
		this.client.setFileType(2);
	}

	public void asc() throws Exception {
		this.client.setFileType(0);
	}

	public String[] list(String pathName) throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			List list = new ArrayList();

			FTPFile[] ftpFiles = this.client.listFiles(pathName);

			for (int i = 0; i < ftpFiles.length; ++i) {
				if (ftpFiles[i].isFile()) {
					list.add(ftpFiles[i].getName());
				}
			}

			return (String[]) list.toArray(new String[0]);
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public String[] listNames() throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			String[] fileNames = this.client.listNames();

			String[] arrayOfString1 = fileNames;

			return arrayOfString1;
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public String[] listNames(String pathName) throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			String[] fileNames = this.client.listNames(pathName);

			String[] arrayOfString1 = fileNames;

			return arrayOfString1;
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public String[] list() throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			List list = new ArrayList();

			FTPFile[] ftpFiles = this.client.listFiles();

			for (int i = 0; i < ftpFiles.length; ++i) {
				if (ftpFiles[i].isFile()) {
					list.add(ftpFiles[i].getName());
				}
			}

			return (String[]) list.toArray(new String[0]);
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public FTPFile getFtpFile(String fileName) throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			FTPFile rtn = null;

			FTPFile[] ftpFiles = this.client.listFiles(fileName);

			for (int i = 0; i < ftpFiles.length; ++i) {
				if ((ftpFiles[i].isFile()) && (ftpFiles[i].getName().equals(fileName))) {
					rtn = ftpFiles[i];
					break;
				}
			}

			if (rtn == null) {
				throw new Exception("无法在目录:" + this.client.printWorkingDirectory() + "下找到文件:" + fileName);
			}

			return rtn;
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public String[] listDir() throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			List list = new ArrayList();

			FTPFile[] ftpFiles = this.client.listFiles();

			for (int i = 0; i < ftpFiles.length; ++i) {
				if (ftpFiles[i].isDirectory()) {
					list.add(ftpFiles[i].getName());
				}
			}
			return (String[]) list.toArray(new String[0]);
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public String getCurrentWorkingDirectory() throws Exception {
		return this.client.printWorkingDirectory();
	}

	public boolean mkdir(String dir) throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			boolean bool1 = this.client.makeDirectory(dir);

			return bool1;
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public void changeWorkingDirectory(String dir) throws Exception {
		this.client.changeWorkingDirectory(wrapper(dir));
	}

	public void upload(String remoteFileName, InputStream input, int mode) throws Exception {
		if (mode == 0) {
			this.client.setFileType(2);
		} else if (mode == 1) {
			this.client.setFileType(0);
		} else {
			throw new SystemException(StatusCodeForFrame.INSIDE_ERROR);
		}
		upload(remoteFileName, input);
	}

	public void upload(String remoteFileName, InputStream input) throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			this.client.storeFile(wrapper(remoteFileName), input);
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public void upload(String remoteFileName, String localFileName) throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			InputStream is = null;
			try {
				is = new BufferedInputStream(new FileInputStream(this.localPath + "/" + localFileName));

				this.client.storeFile(wrapper(remoteFileName), is);
			}
			finally {
				if (is != null)
					is.close();
			}
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public void upload(String remoteFileName, String localFileName, int mode) throws Exception {
		if (mode == 0) {
			this.client.setFileType(2);
		} else if (mode == 1) {
			this.client.setFileType(0);
		} else {
			throw new SystemException(StatusCodeForFrame.INSIDE_ERROR);
		}
		upload(remoteFileName, localFileName);
	}

	public void download(String remoteFileName, OutputStream output, int mode) throws Exception {
		if (mode == 0) {
			this.client.setFileType(2);
		} else if (mode == 1) {
			this.client.setFileType(0);
		} else {
			throw new SystemException(StatusCodeForFrame.INSIDE_ERROR);
		}
		download(remoteFileName, output);
	}

	public void download(String remoteFileName, OutputStream output) throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			boolean rtn = this.client.retrieveFile(wrapper(remoteFileName), output);
			if (!(rtn))
				throw new SystemException(StatusCodeForFrame.INSIDE_ERROR);
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public void download(String remoteFileName, String localFileName, int mode) throws Exception {
		if (mode == 0) {
			this.client.setFileType(2);
		} else if (mode == 1) {
			this.client.setFileType(0);
		} else {
			throw new SystemException(StatusCodeForFrame.INSIDE_ERROR);
		}
		download(remoteFileName, localFileName);
	}

	public void download(String remoteFileName, String localFileName) throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			OutputStream os = null;
			try {
				os = new BufferedOutputStream(new FileOutputStream(this.localPath + "/" + localFileName));
				boolean rtn = this.client.retrieveFile(wrapper(remoteFileName), os);
				if (!(rtn))
					throw new SystemException(StatusCodeForFrame.INSIDE_ERROR);
			}
			finally {
				if (os != null)
					os.close();
			}
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public File download(String remoteFileName, String localFileName, String localPath) throws Exception {
		OutputStream os = null;
		try {
			File localFile = new File(localPath + "/" + localFileName);

			FileOutputStream fos = new FileOutputStream(localFile);
			os = new BufferedOutputStream(fos);
			this.download(remoteFileName, os);
			return localFile;
		}
		finally {
			if (os != null) {
				os.close();
			}
		}
	}

	public InputStream readRemote(String remoteFileName) throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			InputStream localInputStream = this.client.retrieveFileStream(wrapper(remoteFileName));

			return localInputStream;
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public int getReplay() throws Exception {
		return this.client.getReply();
	}

	public InputStream readRemote(String remoteFileName, int mode) throws Exception {
		if (mode == 0) {
			this.client.setFileType(2);
		} else if (mode == 1) {
			this.client.setFileType(0);
		} else {
			throw new SystemException(StatusCodeForFrame.INSIDE_ERROR);
		}
		return readRemote(remoteFileName);
	}

	public void rename(String oldRemoteFileName, String newRemoteFileName) throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			this.client.rename(wrapper(oldRemoteFileName), wrapper(newRemoteFileName));
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public void delete(String remoteFileName) throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			boolean rtn = this.client.deleteFile(wrapper(remoteFileName));
			if (!(rtn))
				throw new SystemException(StatusCodeForFrame.INSIDE_ERROR);
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public void completePendingCommand() throws Exception {
		this.client.completePendingCommand();
	}

	public void close() throws Exception {
		if (this.client.isConnected())
			this.client.disconnect();
	}

	public OutputStream getOutputStream(String fileName) throws Exception {
		boolean isAcquire = false;
		try {
			isAcquire = SEM.acquire();

			OutputStream localOutputStream = this.client.storeFileStream(wrapper(fileName));

			return localOutputStream;
		}
		finally {
			if (isAcquire)
				SEM.release();
		}
	}

	public void moveFileToRemoteHisDir(String fileName) throws Exception {
		if (this.client.listFiles(fileName).length == 0) {
			throw new SystemException(StatusCodeForFrame.INSIDE_ERROR);

		}

		if (StringUtils.isBlank(this.remotePathHis)) {
			throw new SystemException(StatusCodeForFrame.INSIDE_ERROR);
		}

		StringBuffer newFileName = new StringBuffer();
		newFileName.append(this.remotePathHis);
		newFileName.append("/");
		newFileName.append(fileName);
		rename(fileName, newFileName.toString());
	}

	static {
		TIMEOUT_SECONDS = Integer.getInteger("cn.cttic.sysframe.ftp.timeoutSeconds", 120).intValue();
		CONCURRENT_CAPACITY = Integer.getInteger("cn.cttic.sysframe.ftp.concurrentCapacity", 10).intValue();
		CONCURRENT_ACQUIRE_TIMEOUT_SECONDS = Integer.getInteger("cn.cttic.sysframe.ftp.concurrentCapacityAcquireTimeoutSeconds", 3).intValue();
		SEM = new ConcurrentCapacity(CONCURRENT_CAPACITY, CONCURRENT_ACQUIRE_TIMEOUT_SECONDS);
	}
}
