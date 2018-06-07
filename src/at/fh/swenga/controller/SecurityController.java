package at.fh.swenga.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.fh.swenga.dao.UserDao;
import at.fh.swenga.dao.UserRepository;
import at.fh.swenga.dao.UserRoleDao;
import at.fh.swenga.dao.UserRoleRepository;
import at.fh.swenga.model.User;
import at.fh.swenga.model.UserRole;
 
@Controller
public class SecurityController {
 
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserRepository userRepository;
 
	@Autowired
	UserRoleDao userRoleDao;
	
	@Autowired
	UserRoleRepository userRoleRepository;
 
	@RequestMapping("/fillUsers")
	@Transactional
	public String fillData(Model model) {
 
		//UserRole adminRole = userRoleDao.getRole("ROLE_ADMIN");
		UserRole adminRole = userRoleRepository.findUserRole("ROLE_ADMIN");
		if (adminRole == null)
			adminRole = new UserRole("ROLE_ADMIN");
 
		//UserRole userRole = userRoleDao.getRole("ROLE_USER");
		UserRole userRole = userRoleRepository.findUserRole("ROLE_USER");
		if (userRole == null)
			userRole = new UserRole("ROLE_USER");
 
		User admin = userRepository.findUser("admin");
		if(admin==null)
		{
			admin = new User("admin", "password", true);	
			admin.encryptPassword();
			admin.addUserRole(userRole);
			admin.addUserRole(adminRole);
			//userDao.persist(admin);
			admin = userRepository.save(admin);
		}
 
		User user = userRepository.findUser("user");
		if(user==null)
		{
			user = new User("user", "password", true);
			user.encryptPassword();
			user.addUserRole(userRole);
			//userDao.persist(user);
			user = userRepository.save(user);
		}
		
		return "forward:login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}
 
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
 
		return "error";
 
	}
	/*
	 * @ExceptionHandler()
	 * 
	 * @ResponseStatus(code=HttpStatus.FORBIDDEN) public String handle403(Exception
	 * ex) {
	 * 
	 * return "login";
	 * 
	 * }
	 */
}