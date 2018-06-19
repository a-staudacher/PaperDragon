package at.fh.swenga.controller;
 
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.dao.GameSessionRepository;
import at.fh.swenga.dao.UserRepository;
import at.fh.swenga.model.GameSession;
import at.fh.swenga.model.User;
 
@Controller
public class GameController {
	@Autowired
	GameSessionRepository gameSessionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/gamesessionview.html")
	public String ViewGameSession(Model model, Authentication authentication) {
		String userName = authentication.getName();
		User user = userRepository.findUser(userName);
		GameSession gameSession = user.getGameSession();
		
		if(gameSession == null)
			return "gamesessioncreate";
		
		List<User> userList = userRepository.findAll();
		model.addAttribute("userList", userList);
		
		
		model.addAttribute("user",user);
		model.addAttribute("gameSession",gameSession);
		return "gamesessionview";
	}
	
	@RequestMapping(value="/gamesessioncreate.html",method = RequestMethod.GET)
	public String CreateGameSession(Model model, Authentication authentication) {
		String userName = authentication.getName();
		User user = userRepository.findUser(userName);
		List<User> userList = userRepository.findAll();
		model.addAttribute("userList", userList);
		if(user.getGameSession() != null)
			return ViewGameSession(model,authentication);
			
		model.addAttribute("user",user);
		return "gamesessioncreate";
	}
	
	@RequestMapping(value="/createGame",method = RequestMethod.POST)
	public String SaveGameSession(@Valid @ModelAttribute GameSession newGameSession, Model model, Authentication authentication) {
		
		String userName = authentication.getName();
		User user = userRepository.findUser(userName);
		
		newGameSession= gameSessionRepository.save(newGameSession);
		user.setGameSession(newGameSession);
		userRepository.save(user);
		model.addAttribute("gameSession",newGameSession);
		model.addAttribute("user",user);
		return ViewGameSession(model,authentication);
	}
	
	@RequestMapping(value="/addMember")
	public String AddMember(Model model, @RequestParam String name, Authentication authentication) {
		
		String userName = authentication.getName();
		User user = userRepository.findUser(userName);
		User member = userRepository.findUser(name);
		GameSession gameSession = user.getGameSession();
		member.setGameSession(gameSession);
		userRepository.save(member);
	
		return ViewGameSession(model,authentication);
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