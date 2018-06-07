package at.fh.swenga.controller;
 
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class CharacterController {
		
	@RequestMapping(value = "/character")
	public String characterPage(Model model, Authentication authentication) {
		model.addAttribute("user",authentication.getName());
		return "characterpage";
	}
	
	@RequestMapping(value = "/contacts")
	public String contacts(Model model, Authentication authentication) {
		model.addAttribute("user",authentication.getName());
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