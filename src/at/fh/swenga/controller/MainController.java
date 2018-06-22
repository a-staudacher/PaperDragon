package at.fh.swenga.controller;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import at.fh.swenga.dao.BlockModelRepository;
import at.fh.swenga.dao.CharacterRepository;
import at.fh.swenga.dao.ChatRepository;
import at.fh.swenga.dao.DocumentModelRepository;
import at.fh.swenga.dao.GameSessionRepository;
import at.fh.swenga.dao.UserRepository;
import at.fh.swenga.model.BlockModel;
import at.fh.swenga.model.Chat;
import at.fh.swenga.model.DocumentModel;
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
	
	@Autowired
	BlockModelRepository blockModelRepository;
	
	@Autowired
	ChatRepository chatRepository;
	
	@Autowired
	DocumentModelRepository documentModelRepository;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String index(Model model, Authentication authentication) {
		String userName = authentication.getName();
		User user = userRepository.findUser(userName);
		ArrayList<Chat> lines =  new ArrayList<Chat>();
		if(user.getGameSession()!=null)
		{
			List<Chat> chatLines = chatRepository.findByGameSessionName(user.getGameSession().getName());
			List<BlockModel> blocks = blockModelRepository.findByUserUserName(userName);

			for(Chat line : chatLines)
			{
				boolean notFromBlocked = true;
				for(BlockModel blocked : blocks)
				{
					
					if(line.getUser().getUserName().equals(blocked.getBlockedUser().getUserName()))
					{
						notFromBlocked = false;
					}
				}
				if(notFromBlocked)
					lines.add(line);
			}
			model.addAttribute("chatLines",lines);
		}
		
		model.addAttribute("user",user);
		model.addAttribute("character",user.getCharacter());
		model.addAttribute("gameSession",user.getGameSession());
		
		return "index";
	}
	
	@RequestMapping(value="/index.html",method = RequestMethod.GET)
	public String index2(Model model, Authentication authentication) {
		return index(model,authentication);
	}
 
	/*
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
 
		return "error";
 
	}*/
	
	@RequestMapping("/fillChat")
	public String FillChat(Model model, Authentication authentication) {
		

		User admin = userRepository.findUser("admin");
		User user = userRepository.findUser("user");
		
		
		GameSession chat1 = new GameSession("Epic", "Great", null);
		//GameSession savedchat1 = gameSessionRepository.save(chat1);
		//GameSession savedchat1 = gameSessionRepository.findByName("Epic");
				
		gameSessionRepository.save(chat1);
		admin.setGameSession(chat1);
		user.setGameSession(chat1);
		userRepository.save(admin);
		userRepository.save(user);
		Date now = new Date();
		
		Chat line1 = new Chat("Wünderschöner Tag", now);		
		Chat line2 = new Chat("auch wünderschön", now);
		Chat line3 = new Chat("tach", now);
		
		line1.setUser(admin);
		line1.setGamesession(chat1);
		
		line2.setUser(user);
		line2.setGamesession(chat1);
		
		line3.setUser(user);
		line3.setGamesession(chat1);
		
		chatRepository.save(line1);
		chatRepository.save(line2);
		chatRepository.save(line3);
		
		
		return index(model,authentication);
	}

	@RequestMapping(value="/uploadProfilePic",method = RequestMethod.POST )
	public String uploadProfilePic(Model model, @RequestParam("my_file") MultipartFile file, Authentication authentication) {
		String username = authentication.getName();
		User user = userRepository.findUser(username);
		
		
		DocumentModel document = new DocumentModel();

		if(file.getContentType()!=null && file.getContentType().split("/")[0].equals("image"))
		{
			try {
				document.setContent(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			document.setContentType(file.getContentType());
			document.setCreated(new Date());
			document.setFilename(file.getOriginalFilename());
			document.setName(file.getName());
			
			DocumentModel old = documentModelRepository.findByUserUserName(username);
			if(old!=null)
				documentModelRepository.delete(old);
			user.setPicture(document);
			
			
			
			documentModelRepository.save(document);
			userRepository.save(user);
		}
		return index(model,authentication);
	}
	
	
	private void createChatLine(Chat newChat, String userName) 
	{
		User user = userRepository.findUser(userName);
		if(user.getGameSession()!=null)
		{
		newChat.setUser(user);
		newChat.setDate(new Date());
		newChat.setGamesession(user.getGameSession());
		chatRepository.save(newChat);
		}
	}
	
	
	@RequestMapping(value="/chat",method = RequestMethod.POST )
	public String postChat(@Valid @ModelAttribute Chat newChat, Model model, Authentication authentication) {
		createChatLine(newChat, authentication.getName());

		return index(model,authentication);
	}
	
	@RequestMapping(value="/chatingame",method = RequestMethod.POST )
	public String postChatInGame(@Valid @ModelAttribute Chat newChat, Model model, Authentication authentication) {
		String userName = authentication.getName();
		createChatLine(newChat, userName);

		
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
	
	
	

	@RequestMapping(value="/impressum",method = RequestMethod.GET )
	public String getImpressum(Model model) {
		return "impressum";
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