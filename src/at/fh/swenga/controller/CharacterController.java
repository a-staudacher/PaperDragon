package at.fh.swenga.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.fh.swenga.dao.BlockModelRepository;
import at.fh.swenga.dao.CharacterRepository;
import at.fh.swenga.dao.FriendModelRepository;
import at.fh.swenga.dao.UserRepository;
import at.fh.swenga.model.BlockModel;
import at.fh.swenga.model.Character;
import at.fh.swenga.model.FriendModel;
import at.fh.swenga.model.User;
 
@Controller
public class CharacterController {
		
	@Autowired
	CharacterRepository characterRepository;
	
	@Autowired
	FriendModelRepository friendModelRepository;
	
	@Autowired
	BlockModelRepository blockModelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@RequestMapping(value = "/characterpage.html",method = RequestMethod.GET)
	public String characterPage(Model model, Authentication authentication) {
		String userName = authentication.getName();
		Character character = characterRepository.findByUserUserName(userName);
		
		model.addAttribute("user",userRepository.findUser(userName));
		model.addAttribute("character",character);
		//characterRepository;
		
		return "characterpage";
	}
	
	@RequestMapping(value = "/characterpage",method = RequestMethod.POST)
	public String editCharacter(Model model, Authentication authentication) {
		//todo: finish this or add Parameters to get.
		return "characterpage";
	}
	
	@RequestMapping(value = "/contacts.html")
	public String contacts(Model model, Authentication authentication) {
		String userName = authentication.getName();
		List<FriendModel> friends = friendModelRepository.findByFriend1UserName(userName);
		List<BlockModel> blocks = blockModelRepository.findByUserUserName(userName);
		
		List<User> userList = userRepository.findAll();
		
		//anmerkung: user und in html user ebenso deffiniert in th:each
		model.addAttribute("user",userName);
		model.addAttribute("friends",friends);
		model.addAttribute("blocks",blocks);
		model.addAttribute("userList", userList);
		
		return "contacts";
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