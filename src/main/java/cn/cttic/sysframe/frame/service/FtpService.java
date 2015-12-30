package cn.cttic.sysframe.frame.service;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.net.ftp.FTPFile;

import cn.cttic.sysframe.frame.domain.SmFtpPath;

public interface FtpService {
	
	public FtpService getInstance() throws Exception;
	
	public void FtpUtils(String ftpPathCode) throws Exception;

	public SmFtpPath getSmFtpPath() throws Exception;

	public String[] list(String pathName) throws Exception;

	public String[] listNames() throws Exception;

	public String[] listNames(String pathName) throws Exception;

	public String[] list() throws Exception;

	public FTPFile getFtpFile(String fileName) throws Exception;

	public String[] listDir() throws Exception;

	public boolean mkdir(String dir) throws Exception;

	public void changeWorkingDirectory(String dir) throws Exception;

	public void upload(String remoteFileName, InputStream input, int mode)
			throws Exception;

	public void upload(String remoteFileName, InputStream input)
			throws Exception;

	public void upload(String remoteFileName, String localFileName)
			throws Exception;

	public void upload(String remoteFileName, String localFileName, int mode)
			throws Exception;

	public void download(String remoteFileName, OutputStream output, int mode)
			throws Exception;

	public void download(String remoteFileName, OutputStream output)
			throws Exception;

	public void download(String remoteFileName, String localFileName, int mode)
			throws Exception;

	public void download(String remoteFileName, String localFileName)
			throws Exception;

	public InputStream readRemote(String remoteFileName) throws Exception;

	public InputStream readRemote(String remoteFileName, int mode)
			throws Exception;

	public void rename(String oldRemoteFileName, String newRemoteFileName)
			throws Exception;

	public void delete(String remoteFileName) throws Exception;

	public void completePendingCommand() throws Exception;

	public void close() throws Exception;

	public OutputStream getOutputStream(String fileName) throws Exception;

	public void moveFileToRemoteHisDir(String fileName) throws Exception;

	public void bin() throws Exception;

	public Long fileUpload(HttpServletRequest request, String phone,
			String note, String jobCode) throws Exception;

	public void moveFileToRemoteHis(String fileName, String path)
			throws Exception;

}
