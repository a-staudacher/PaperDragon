package at.fh.swenga.controller;
 
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import at.fh.swenga.dao.DocumentModelRepository;
import at.fh.swenga.dao.GameSessionRepository;
import at.fh.swenga.dao.UserRepository;
import at.fh.swenga.model.DocumentModel;
import at.fh.swenga.model.GameSession;
import at.fh.swenga.model.User;
 
@Controller
public class GameController {
	@Autowired
	GameSessionRepository gameSessionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DocumentModelRepository documentModelRepository;
	
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
		
		GameSession existingGame = gameSessionRepository.findByName(newGameSession.getName());
		if(existingGame==null)
			newGameSession= gameSessionRepository.save(newGameSession);
		else
			newGameSession = existingGame;
		
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
	
	@RequestMapping(value="/leaveGroup")
	public String leaveGroup(Model model, Authentication authentication) {
		
		String userName = authentication.getName();
		User user = userRepository.findUser(userName);
		GameSession gameSession = user.getGameSession();
		user.setGameSession(null);
		userRepository.save(user);
		
		if(gameSession.getUsers()==null || gameSession.getUsers().isEmpty())
			gameSessionRepository.delete(gameSession);
	
		return CreateGameSession(model,authentication);
	}
	
	@RequestMapping(value="/uploadGamePic",method = RequestMethod.POST )
	public String uploadGamePic(Model model, @RequestParam("my_file") MultipartFile file, Authentication authentication) {
		String username = authentication.getName();
		User user = userRepository.findUser(username);
		DocumentModel document = new DocumentModel();
		
			try {
				document.setContent(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.setContentType(file.getContentType());
			document.setCreated(new Date());
			document.setFilename(file.getOriginalFilename());
			document.setName(file.getName());
			
			DocumentModel old = documentModelRepository.findByGameSessionName(user.getGameSession().getName());
			//todo: if(document.getContentType()) is not picture dont upload
			if(old!=null)
				documentModelRepository.delete(old);
			user.getGameSession().setPicture(document);
			
			
			
		documentModelRepository.save(document);
		gameSessionRepository.save(user.getGameSession());
		return ViewGameSession(model,authentication);
	}
	
	@RequestMapping(value="/editMode",method = RequestMethod.GET )
	public String editMode(Model model, Authentication authentication) {
		
		model.addAttribute("editMode","");

		
		return ViewGameSession(model,authentication);
	}

	@RequestMapping(value="/saveLore",method = RequestMethod.GET )
	public String saveLore(Model model, Authentication authentication, @RequestParam String newlore) {
		
		User user = userRepository.findUser(authentication.getName());
		
		GameSession gamesession = user.getGameSession();
		
		gamesession.setLore(newlore);
		
		gameSessionRepository.save(gamesession);
		
		
		
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