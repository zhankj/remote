package cn.cttic.icar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cttic.icar.dao.UsersMapper;
import cn.cttic.icar.domain.Users;

@Controller
public class PartController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	UsersMapper usersMapper;
	@RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
    public @ResponseBody
    Users getUser(@PathVariable("id") Long id) {
        logger.info("获取人员信息id=" + id);      
        return usersMapper.selectByPrimaryKey(id);
    }
}
