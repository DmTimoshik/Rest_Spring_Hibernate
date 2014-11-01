package controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.Role;
import dao.User;
import service.RoleDaoService;
import service.UserDaoService;
import util.*;

@Controller
public class Controler {

	@Autowired
	private UserDaoService userDaoService;

	@Autowired
	private RoleDaoService roleDaoService;


	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public List<User> findClient() {
		return userDaoService.findAll();
	}
	
	@RequestMapping(value = "/user/{login}", method = RequestMethod.GET)
	@ResponseBody
	public User findClientUser(@PathVariable("login") String loginUser) {
		return userDaoService.findByLogin(loginUser);
	}
	
	
	@RequestMapping(value = "/user/{login}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteClientUser(@PathVariable("login") String loginUser) {
		userDaoService.remove(userDaoService.findByLogin(loginUser));
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public void createClientUser(@RequestBody User user) {
		userDaoService.create(user);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	@ResponseBody
	public void updateClientUser(@RequestBody User user) {
		userDaoService.update(user);
	}
	
	@RequestMapping(value = "/role/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Role findNameRole(@PathVariable("name") String nameRole) {
		return roleDaoService.findByName(nameRole);
	}
	
	@RequestMapping(value = "/role/{name}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteNameRole(@PathVariable("name") String nameRole) {
		roleDaoService.remove(roleDaoService.findByName(nameRole));
	}	
	
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	@ResponseBody
	public void createClientRole(@RequestBody Role role) {
		roleDaoService.create(role);
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.PUT)
	@ResponseBody
	public void updateClientRole(@RequestBody Role role) {
		roleDaoService.update(role);
	}
}
