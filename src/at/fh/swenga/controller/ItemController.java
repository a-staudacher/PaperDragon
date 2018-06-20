package at.fh.swenga.controller;
 
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.dao.CharacterRepository;
import at.fh.swenga.dao.ItemBaseModelRepository;
import at.fh.swenga.dao.ItemModelRepository;
import at.fh.swenga.dao.ItemTypeRepository;
import at.fh.swenga.dao.UserRepository;
import at.fh.swenga.model.Character;
import at.fh.swenga.model.ItemBaseModel;
import at.fh.swenga.model.ItemModel;
import at.fh.swenga.model.ItemType;
 
@Controller
public class ItemController {
	
	@Autowired
	ItemBaseModelRepository itemBaseModelRepository;
	
	@Autowired
	ItemModelRepository itemModelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ItemTypeRepository itemTypeRepository;
	
	@Autowired
	CharacterRepository characterRepository;
		
	@RequestMapping(value = "/itemarchive.html")
	public String itemArchive(Model model, Authentication authentication) {
		model.addAttribute("user",userRepository.findUser(authentication.getName()));
		model.addAttribute("items",itemBaseModelRepository.findAll());
		return "itemarchive";
	}
	
	@RequestMapping("fillItems")
	public String fillItems(Model model, Authentication authentication) {
		
		if(itemBaseModelRepository.findAll().isEmpty())
		{
			ItemType helmet = new ItemType("helmet", null);
			
			ItemType chestArmor = new ItemType("chestArmor", null);
			
			ItemType armArmor = new ItemType("armArmor", null);
			
			ItemType legArmor = new ItemType("legArmor", null);
			
			ItemType weapon = new ItemType("weapon", null);
			
			helmet = itemTypeRepository.save(helmet);
			chestArmor = itemTypeRepository.save(chestArmor);
			armArmor = itemTypeRepository.save(armArmor);
			legArmor = itemTypeRepository.save(legArmor);
			weapon = itemTypeRepository.save(weapon);
			
			ItemBaseModel item1 = genItem("KingsSwordofHaste", "A realy fast sword!",weapon);
			
			ItemBaseModel item2 = genItem("QuinquennialSword", "A mysterious sword of unknown origin.", weapon);
			
			ItemBaseModel item4 = genItem("Amberwing", "Its hot like burning flames.", weapon);
	
			ItemBaseModel item5 = genItem("MonsterHunter", "It hungers for monster blood.", weapon);
	
			ItemBaseModel item6 = genItem("Broadsword", "A simple broadsword.", weapon);
			
			ItemBaseModel item7 = genItem("Wildwood", "A wild sword.", weapon);
	
			ItemBaseModel item8 = genItem("Falchion", "A long curved sword.", weapon);
	
			ItemBaseModel item9 = genItem("ExecutionerSword", "A sword soaked the blood of many men.", weapon);
	
			ItemBaseModel item10 = genItem("TheZweihander", "A sword best to be wielded with two hands.", weapon);
		
			ItemBaseModel item12 = genItem("Claymore", "A sword best to be wielded with two hands.", weapon);
	
			ItemBaseModel item13 = genItem("Scourge", "The weapon makes you feel uneasy while holding it.", weapon);
	
			ItemBaseModel item14 = genItem("Blackguard", "This weapon got a lot of history.", weapon);
	
			ItemBaseModel item15 = genItem("Reaver", "This weapon has mind of its owne, telling you to kill everyone around you.", weapon);
	
			ItemBaseModel item16 = genItem("Nagamaki", "A weapon from a land far away.", weapon);
	
			ItemBaseModel item17 = genItem("HideGloves", "Made ouf of simple leather", armArmor);
	
			ItemBaseModel item18 = genItem("ChainGloves", "Heavy but provide good protection", armArmor);
	
			ItemBaseModel item19 = genItem("Gauntlets", "Very heavy and bad for mobility, but give very good protection", armArmor);
	
			ItemBaseModel item20 = genItem("StoneGauntlets", "Are you sure you can even hold these?", armArmor);
	
			ItemBaseModel item21 = genItem("Magefist", "These have magic enhancing properties", armArmor);
	
			ItemBaseModel item22 = genItem("Vambraces", "Brace yourself for vam.", armArmor);
	
			ItemBaseModel item23 = genItem("GladiatorGauntlets", "Feel like a Gladiator. Just today for 9.99", armArmor);
	
			ItemBaseModel item24 = genItem("HideTunic", "Most basic protection.", chestArmor);
	
			ItemBaseModel item25 = genItem("HeartofIron", "Like a metal fortress.", chestArmor);
	
			ItemBaseModel item26 = genItem("ChainMail", "Good protection. Makes a lot of sound", chestArmor);
	
			ItemBaseModel item27 = genItem("SplintCuirass", "Some armor, from some place", chestArmor);
	
			ItemBaseModel item28 = genItem("AquilaCuirass", "Makes you feel pleasantly cool.", chestArmor);
	
			ItemBaseModel item29 = genItem("PlateMail", "Heavy armor that greatly decreases mobility. Gives you very good protection", chestArmor);
	
			ItemBaseModel item30 = genItem("Cindercoat", "Cinder&Coat", chestArmor);
	
			ItemBaseModel item31 = genItem("BattleArmor", "Made for battle!", chestArmor);
	
	
			ItemBaseModel item33 = genItem("PridesFall", "Seems you lost your pride somewhere.", helmet);
	
			ItemBaseModel item34 = genItem("BlindFaith", "You cant see anything while wearing this. But dont wory, have some faith!", helmet);
	
			ItemBaseModel item35 = genItem("WarhelmofKassar", "Its a warhelm. ... And I guess you know where if comes from.", helmet);
	
			ItemBaseModel item36 = genItem("ChainHood", "Want to wear a chain on your head like a turban?", helmet);
	
			ItemBaseModel item37 = genItem("MysteryHelm", "You dont know where this helm came from nor what it is made of.", helmet);
	
			ItemBaseModel item38 = genItem("FatesVow", "You can never go against you fait, so you might as well vow on it.", helmet);
	
			ItemBaseModel item39 = genItem("Hounskull", "Uuuh disgusting! What you wearing?", helmet);
	
			ItemBaseModel item40 = genItem("MysteryBoots", "Some boots that appeared ouf ot nowhere. You feel funny while wearing them.", legArmor);
	
			ItemBaseModel item41 = genItem("RiveraDancers", "First choice of everyone that wants to spend all of night dancing.", legArmor);
	
			ItemBaseModel item42 = genItem("HeavyBoots", "Wear them in a swamp and ", legArmor);
	
			ItemBaseModel item43 = genItem("BootsofDisregard", "You know when you ", legArmor);
	
			ItemBaseModel item45 = genItem("ChainBoots", "They are so heavy, they chain you down... Okay im sorry I stop", legArmor);
	
			ItemBaseModel item46 = genItem("Greaves", "I dont know, think for yourself!", legArmor);
	
			ItemBaseModel item47 = genItem("SilkShoes", "Made of the finest silk. Trust me.", legArmor);
	
			itemBaseModelRepository.save(item1);
			itemBaseModelRepository.save(item2);
			itemBaseModelRepository.save(item4);
			itemBaseModelRepository.save(item5);
			itemBaseModelRepository.save(item6);
			itemBaseModelRepository.save(item7);
			itemBaseModelRepository.save(item8);
			itemBaseModelRepository.save(item9);
			itemBaseModelRepository.save(item10);
			itemBaseModelRepository.save(item12);
			itemBaseModelRepository.save(item13);
			itemBaseModelRepository.save(item14);
			itemBaseModelRepository.save(item15);
			itemBaseModelRepository.save(item16);
			itemBaseModelRepository.save(item17);
			itemBaseModelRepository.save(item18);
			itemBaseModelRepository.save(item19);
			itemBaseModelRepository.save(item20);
			itemBaseModelRepository.save(item21);
			itemBaseModelRepository.save(item22);
			itemBaseModelRepository.save(item23);
			itemBaseModelRepository.save(item24);
			itemBaseModelRepository.save(item25);
			itemBaseModelRepository.save(item26);
			itemBaseModelRepository.save(item27);
			itemBaseModelRepository.save(item28);
			itemBaseModelRepository.save(item29);
			itemBaseModelRepository.save(item30);
			itemBaseModelRepository.save(item31);
			itemBaseModelRepository.save(item33);
			itemBaseModelRepository.save(item34);
			itemBaseModelRepository.save(item35);
			itemBaseModelRepository.save(item36);
			itemBaseModelRepository.save(item37);
			itemBaseModelRepository.save(item38);
			itemBaseModelRepository.save(item39);
			itemBaseModelRepository.save(item40);
			itemBaseModelRepository.save(item41);
			itemBaseModelRepository.save(item42);
			itemBaseModelRepository.save(item43);
			itemBaseModelRepository.save(item45);
			itemBaseModelRepository.save(item46);
			itemBaseModelRepository.save(item47);
			
		}
		return itemArchive(model,authentication);
	}

	private ItemBaseModel genItem(String name, String lore, ItemType type)
	{
		double chanceToStat = 3;
		
		DataFactory db = new DataFactory();
		
		ItemBaseModel item = new ItemBaseModel(name, lore, null, 
				(db.getNumberUpTo(9)<chanceToStat) ? db.getNumberUpTo(20) : 0, (db.getNumberUpTo(9)<chanceToStat) ? db.getNumberUpTo(20) : 0, 
				(db.getNumberUpTo(9)<chanceToStat) ? db.getNumberUpTo(20) : 0, 
				(db.getNumberUpTo(9)<chanceToStat) ? db.getNumberUpTo(20) : 0,
				(db.getNumberUpTo(9)<chanceToStat) ? db.getNumberUpTo(20) : 0, 
				(db.getNumberUpTo(9)<chanceToStat) ? db.getNumberUpTo(20) : 0, 
				(db.getNumberUpTo(9)<chanceToStat) ? db.getNumberUpTo(20) : 0, 
				db.getNumberUpTo(100), null, type);;
		
		return item;
	}
	
	@RequestMapping(value = "/addItem")
	public String addItemToInventory(Model model, Authentication authentication, @RequestParam int id) {
		
		String userName = authentication.getName();
		Character character = characterRepository.findByUserUserName(userName);
		
		if(itemBaseModelRepository.findById(id).isPresent())
		{
			ItemBaseModel itemBaseModel = itemBaseModelRepository.findById(id).get();
			ItemModel itemModel = new ItemModel(1, false, character, itemBaseModel);
			itemModelRepository.save(itemModel);			
		}
		
		character = characterRepository.findByUserUserName(userName);
		
		model.addAttribute("user",userRepository.findUser(userName));
		model.addAttribute("character",character);
		
		
		return "characterpage";
	}
	
	@RequestMapping(value = "/equipItem")
	public String equipItem(Model model, Authentication authentication, @RequestParam int id) {
		
		String userName = authentication.getName();
		Character character = characterRepository.findByUserUserName(userName);
		
		if(itemModelRepository.findById(id).isPresent())
		{
			ItemModel itemModel = itemModelRepository.findById(id).get();
			String itemType = itemModel.getItemBase().getItemType().getType();
			
			for(ItemModel item: itemModelRepository.findByItemBaseItemTypeTypeAndCharacterUserUserName(itemType, userName))
			{
				if(item.isEquipped()) {
					item.setEquipped(false);
					itemModelRepository.save(item);
				}
			}
			
			
			
			
			itemModel.setEquipped(!itemModel.isEquipped());
			itemModelRepository.save(itemModel);			
		}
		
		character = characterRepository.findByUserUserName(userName);
		
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
		
		model.addAttribute("helmet",helmet);
		model.addAttribute("chestArmor",chestArmor);
		model.addAttribute("armArmor",armArmor);
		model.addAttribute("legArmor",legArmor);
		model.addAttribute("weapon",weapon);
		
		model.addAttribute("user",userRepository.findUser(userName));
		model.addAttribute("character",character);
		
		
		return "characterpage";
	}
	
	@RequestMapping(value = "/dropItem")
	public String dropItem(Model model, Authentication authentication, @RequestParam int id) {
		
		String userName = authentication.getName();
		Character character = characterRepository.findByUserUserName(userName);
		
		if(itemModelRepository.findById(id).isPresent())
		{
			itemModelRepository.delete(itemModelRepository.findById(id).get());
		}
		
		character = characterRepository.findByUserUserName(userName);
		
		model.addAttribute("user",userRepository.findUser(userName));
		model.addAttribute("character",character);
		
		
		return "characterpage";
	}
	
	@RequestMapping(value = "/searchitem")
	public String searchItem(Model model, Authentication authentication, @RequestParam String text) {
		
		
		model.addAttribute("items",itemBaseModelRepository.findByNameContainingAllIgnoreCase(text));
		
		
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