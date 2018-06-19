package at.fh.swenga.controller;
 
import javax.validation.Valid;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.fh.swenga.dao.UserDao;
import at.fh.swenga.dao.UserRepository;
import at.fh.swenga.dao.UserRoleDao;
import at.fh.swenga.dao.UserRoleRepository;
import at.fh.swenga.model.User;
import at.fh.swenga.model.UserRole;
import at.fh.swenga.model.Character;
import at.fh.swenga.dao.CharacterRepository;
 
@Controller
public class SecurityController {
 
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CharacterRepository characterRepository;
 
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
		
		
		DataFactory df = new DataFactory();
		
		String[] names = {"Andreal", "Turial", "Fliroa", "Wacko Kane", "Razor Chilton", "Mad Eyed Neddy", "Kellie Bullettooth" ,"Krista Scarface", "Cindy the Fang", "Corinne Ghost"};
		String[] gender = {"Male", "Female"};

		Character character = new Character(df.getItem(names), "", df.getNumberUpTo(20), df.getNumberUpTo(20), df.getNumberUpTo(20), df.getNumberUpTo(20), df.getNumberUpTo(20), df.getNumberUpTo(20), df.getNumberUpTo(20), df.getItem(gender), null, admin);
		Character character2 = new Character(df.getItem(names), "", df.getNumberUpTo(20), df.getNumberUpTo(20), df.getNumberUpTo(20), df.getNumberUpTo(20), df.getNumberUpTo(20), df.getNumberUpTo(20), df.getNumberUpTo(20), df.getItem(gender), null, user);
		
		admin = userRepository.save(admin);
		character = characterRepository.save(character);
		user = userRepository.save(user);
		character2 = characterRepository.save(character2);
		
		return "forward:login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}
	
	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	public String registerPage() {
		
		return "register"; //register.html
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(@Valid User newUser, BindingResult bindingResult,
			Model model) {
		
		
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);

			return "forward:/login";
		}
		UserRole userRole = userRoleRepository.findUserRole("ROLE_USER");
		
		// Look for User in the List. One available -> Error 
		User user = userRepository.findUser(newUser.getUserName());
 
		if (user != null) {
			model.addAttribute("errorMessage", "user already exists!<br>");
			return "register";
		} else {			
			newUser.encryptPassword();
			newUser.addUserRole(userRole);
			//userDao.persist(user);
			newUser = userRepository.save(newUser);
			model.addAttribute("Registrierung", "New user " + newUser.getUserName() + "  erfolgreich.");
		}
 
		return "forward:/login";
		

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