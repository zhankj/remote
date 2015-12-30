package cn.cttic.sysframe.frame.service.impl;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.frame.dao.SmBulletinMapper;
import cn.cttic.sysframe.frame.domain.SmBulletin;
import cn.cttic.sysframe.frame.domain.SmBulletinExample;
import cn.cttic.sysframe.frame.service.FileUploadService;
import cn.cttic.sysframe.frame.service.SmBulletinService;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class SmBulletinServiceImpl implements SmBulletinService {

	@Autowired
	SmBulletinMapper smBulletinMapperDao;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Override
	public PageList<SmBulletin> queryPage(SmBulletinExample example,
			PageBounds pageBounds) {
		return smBulletinMapperDao.queryPage(example, pageBounds);
	}
	
	@Override
	public SmBulletin queryBean(Integer bulletinId) {
		return smBulletinMapperDao.selectByPrimaryKey(bulletinId);
	}

	@Override
	public int insert(HttpServletRequest request, SmBulletin record) throws Exception {
		fileUpload(request, record);
		return smBulletinMapperDao.insert(record);
	}
	
	@Override
	public int updateByPrimaryKeySelective(HttpServletRequest request, SmBulletin record) throws Exception {
		fileUpload(request, record);
		return smBulletinMapperDao.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public PageList<SmBulletin> queryOperPage(Map<String, Object> map, PageBounds pageBounds) {
		return smBulletinMapperDao.queryOperPage(map, pageBounds);
	}
	
	private void fileUpload(HttpServletRequest request, SmBulletin record) throws Exception {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 获取multipartRequest中所有的文件名
			Iterator iter = multipartRequest.getFileNames();
			 while (iter.hasNext()) {
				MultipartFile file = multipartRequest.getFile(iter.next().toString());
				if(file==null){
					throw new SystemException(StatusCodeForFrame.NONEXISTENT_ORGAN, "上传文件不存在!");
				}
				//上传文件
				fileUploadService.upload(request, "SMBULLETIN_FILE", String.valueOf(record.getBulletinId()), "");
			 }
		}
	}
}
