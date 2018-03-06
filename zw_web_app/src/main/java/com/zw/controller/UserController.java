package com.zw.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zw.common.LogUtil;
import com.zw.model.User;
import com.zw.service.IUserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Resource
	private IUserService userService;
	
	@RequestMapping(value="userList")
	public String userList(HttpServletRequest request,ModelMap modelMap) {
		List<User> users = userService.getAllUser();
		modelMap.addAttribute("users", users);
		return "userList";
	}
	
	@RequestMapping(value="showUser")
	public String showUser(HttpServletRequest request,ModelMap modelMap) {
		String id = request.getParameter("id");
		User user = userService.getUserById(Integer.valueOf(id));
		modelMap.addAttribute("user", user);
		return "showUser";
	}
	@RequestMapping(value="addUser")
	public String addUser(HttpServletRequest request,ModelMap modelMap) {
		User user=new User();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		user.setUsername(username);
		LogUtil.apiInfo(username);
		user.setPassword(password);
		LogUtil.apiInfo(password);
		userService.insertUser(user);
		return "addUser";
	}
	
	@RequestMapping(value="userInfo")
	public String userInfo() {
		return "test/user";
	}
}
