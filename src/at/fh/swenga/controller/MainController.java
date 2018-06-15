package at.fh.swenga.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.dao.CharacterRepository;
import at.fh.swenga.dao.GameSessionRepository;
import at.fh.swenga.dao.UserRepository;
import at.fh.swenga.model.Character;
import at.fh.swenga.model.GameSession;
import at.fh.swenga.model.User;
 
@Controller
public class MainController {
	
	@Autowired
	GameSessionRepository gameSessionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CharacterRepository characterRepository;
	
	@RequestMapping("/")
	public String index(Model model, Authentication authentication) {
		String userName = authentication.getName();
		User user = userRepository.findUser(userName);
		
		
		
		model.addAttribute("user",user);
		model.addAttribute("character",user.getCharacter());
		model.addAttribute("gameSession",user.getGameSession());
		return "index";
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