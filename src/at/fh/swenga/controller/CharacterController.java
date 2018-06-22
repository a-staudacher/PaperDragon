package at.fh.swenga.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.dao.BlockModelRepository;
import at.fh.swenga.dao.CharacterRepository;
import at.fh.swenga.dao.FriendModelRepository;
import at.fh.swenga.dao.ItemModelRepository;
import at.fh.swenga.dao.UserRepository;
import at.fh.swenga.model.BlockModel;
import at.fh.swenga.model.Character;
import at.fh.swenga.model.FriendModel;
import at.fh.swenga.model.ItemModel;
import at.fh.swenga.model.User;
 
@Controller
public class CharacterController {
		
	@Autowired
	CharacterRepository characterRepository;
	
	@Autowired
	ItemModelRepository itemModelRepository;
	
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
		
		ItemModel helmet = null;
		ItemModel chestArmor = null;
		ItemModel armArmor = null;
		ItemModel legArmor = null;
		ItemModel weapon = null;
		
		for(ItemModel item : itemModelRepository.findByEquippedAndCharacterUserUserName(true,userName))
		{
			switch(item.getItemBase().getItemType().getType())
			{
				case "helmet": 
					helmet = item;
					break;
				case "chestArmor": 
					chestArmor = item;
					break;
				case "armArmor": 
					armArmor = item;
					break;
				case "legArmor": 
					legArmor = item;
					break;
				case "weapon": 
					weapon = item;
					break;	
			}
		}
		
		model.addAttribute("user",userRepository.findUser(userName));
		model.addAttribute("character",character);
		
		model.addAttribute("helmet",helmet);
		model.addAttribute("chestArmor",chestArmor);
		model.addAttribute("armArmor",armArmor);
		model.addAttribute("legArmor",legArmor);
		model.addAttribute("weapon",weapon);
		//characterRepository;
		
		return "characterpage";
	}

	@RequestMapping(value = "/characterview.html",method = RequestMethod.GET)
	public String characterPage(Model model, Authentication authentication, @RequestParam String name) {
		Character character = characterRepository.findByUserUserName(name);
		
		ItemModel helmet = null;
		ItemModel chestArmor = null;
		ItemModel armArmor = null;
		ItemModel legArmor = null;
		ItemModel weapon = null;
		
		for(ItemModel item : itemModelRepository.findByEquippedAndCharacterUserUserName(true,name))
		{
			switch(item.getItemBase().getItemType().getType())
			{
				case "helmet": 
					helmet = item;
					break;
				case "chestArmor": 
					chestArmor = item;
					break;
				case "armArmor": 
					armArmor = item;
					break;
				case "legArmor": 
					legArmor = item;
					break;
				case "weapon": 
					weapon = item;
					break;	
			}
		}
		
		model.addAttribute("user",userRepository.findUser(name));
		model.addAttribute("character",character);
		
		model.addAttribute("helmet",helmet);
		model.addAttribute("chestArmor",chestArmor);
		model.addAttribute("armArmor",armArmor);
		model.addAttribute("legArmor",legArmor);
		model.addAttribute("weapon",weapon);
		//characterRepository;
		
		return "characterview";
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
	
	@RequestMapping(value = "/addFriend")
	public String addFriend(Model model, Authentication authentication, @RequestParam String name) {
		String userName = authentication.getName();
		User user = userRepository.findUser(userName);
		User friend = userRepository.findUser(name);
		if(friendModelRepository.findByFriend1UserNameAndFriend2UserName(userName, name)==null)
		{
			friendModelRepository.save(user.addFriend(friend));
		}
		
		return contacts(model,authentication);
	}	
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/banUser")
	public String banUser(Model model, Authentication authentication, @RequestParam String name) {
		User user = userRepository.findUser(name);
		user.setEnabled(!user.isEnabled());
		userRepository.save(user);
		
		return contacts(model,authentication);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/resetPassUser")
	public String resetPassUser(Model model, Authentication authentication, @RequestParam String name) {
		User user = userRepository.findUser(name);
		user.setPassword(user.getUserName());
		user.encryptPassword();
		userRepository.save(user);
		
		return contacts(model,authentication);
	}
	
	@RequestMapping(value = "/removeFriend")
	public String removeFriend(Model model, Authentication authentication, @RequestParam String name) {
		String userName = authentication.getName();
		FriendModel friendModel = friendModelRepository.findByFriend1UserNameAndFriend2UserName(userName, name);
		if(friendModel!=null)
		{
			friendModelRepository.delete(friendModel);
		}
		
		return contacts(model,authentication);
	}
	
	@RequestMapping(value = "/removeBlock")
	public String removeBlock(Model model, Authentication authentication, @RequestParam String name) {
		String userName = authentication.getName();
		BlockModel blockModel = blockModelRepository.findByUserUserNameAndBlockedUserUserName(userName, name);
		if(blockModel!=null)
		{
			blockModelRepository.delete(blockModel);
		}
		
		return contacts(model,authentication);
	}
	
	@RequestMapping(value = "/addBlock")
	public String addBlock(Model model, Authentication authentication, @RequestParam String name) {
		String userName = authentication.getName();
		User user = userRepository.findUser(userName);
		User block = userRepository.findUser(name);
		if(blockModelRepository.findByUserUserNameAndBlockedUserUserName(userName, name)==null)
		{
			blockModelRepository.save(user.addBlockedUser(block));
		}
		
		return contacts(model,authentication);
	}
	
	@RequestMapping(value = "/addStrength")
	public String addStrength(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setStrength(character.getStrength()+1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
	
	@RequestMapping(value = "/subStrength")
	public String subStrength(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setStrength(character.getStrength()-1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
	
	@RequestMapping(value = "/addCharisma")
	public String addCharisma(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setCharisma(character.getCharisma()+1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
	
	@RequestMapping(value = "/subCharisma")
	public String subCharisma(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setCharisma(character.getCharisma()-1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
	
	@RequestMapping(value = "/addConstitution")
	public String addConstitution(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setConstitution(character.getConstitution()+1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
	
	@RequestMapping(value = "/subConstitution")
	public String subConstitution(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setConstitution(character.getConstitution()-1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}

	@RequestMapping(value = "/addDexterity")
	public String addDexterity(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setDexterity(character.getDexterity()+1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
	
	@RequestMapping(value = "/subDexterity")
	public String subDexterity(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setDexterity(character.getDexterity()-1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
	
	@RequestMapping(value = "/addIntelligence")
	public String addIntelligence(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setIntelligence(character.getIntelligence()+1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
	
	@RequestMapping(value = "/subIntelligence")
	public String subIntelligence(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setIntelligence(character.getIntelligence()-1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
	
	@RequestMapping(value = "/addVitality")
	public String addVitality(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setVitality(character.getVitality()+1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
	
	@RequestMapping(value = "/subVitality")
	public String subVitality(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setVitality(character.getVitality()-1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
	
	@RequestMapping(value = "/addWisdom")
	public String addWisdom(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setWisdom(character.getWisdom()+1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
	
	@RequestMapping(value = "/subWisdom")
	public String subWisdom(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setWisdom(character.getWisdom()-1);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
 
	@RequestMapping(value = "/updateLore")
	public String updateLore(Model model, Authentication authentication, @RequestParam String lore) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		character.setHistory(lore);
		characterRepository.save(character);
				
		return characterPage(model,authentication);
	}
	
	@RequestMapping(value = "/getExcel")
	public String getExcel(Model model, Authentication authentication) {
		Character character = userRepository.findUser(authentication.getName()).getCharacter();
		model.addAttribute("character", character);
		
		return "excelReport";		
	}
	
	@RequestMapping(value = "/getExcelFromOther")
	public String getExcelFromOther(Model model, Authentication authentication, @RequestParam String user) {
		Character character = userRepository.findUser(user).getCharacter();
		model.addAttribute("character", character);
		
		return "excelReport";		
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