package at.fh.swenga.controller;
 
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
		
		//if(gameSession == null)
			//return "gamesessioncreate";
		
		
		model.addAttribute("user",user);
		model.addAttribute("gameSession",gameSession);
		return "gamesessionview";
	}
	
	@RequestMapping(value="/gamesessioncreate.html",method = RequestMethod.GET)
	public String CreateGameSession(Model model, Authentication authentication) {
		String userName = authentication.getName();
		User user = userRepository.findUser(userName);
		//if(user.getGameSession() != null)
			//return "gamesessionview";
			
		model.addAttribute("user",user);
		return "gamesessioncreate";
	}
	
	@RequestMapping(value="/gamesessioncreate",method = RequestMethod.POST)
	public String SaveGameSession(@Valid @ModelAttribute GameSession newGameSession, Model model, Authentication authentication) {
		
		String userName = authentication.getName();
		User user = userRepository.findUser(userName);
		
		newGameSession= gameSessionRepository.save(newGameSession);
		
		model.addAttribute("gameSession",newGameSession);
		model.addAttribute("user",user);
		return "forward:gamesessionview";
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