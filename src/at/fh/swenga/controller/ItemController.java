package at.fh.swenga.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.dao.ItemBaseModelRepository;
import at.fh.swenga.dao.UserRepository;
 
@Controller
public class ItemController {
	
	@Autowired
	ItemBaseModelRepository itemBaseModelRepository;
	
	@Autowired
	UserRepository userRepository;
		
	@RequestMapping(value = "/itemlist")
	public String itemArchive(Model model, Authentication authentication) {
		model.addAttribute("user",userRepository.findUser(authentication.getName()));
		model.addAttribute("items",itemBaseModelRepository.findAll());
		return "itemarchive";
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