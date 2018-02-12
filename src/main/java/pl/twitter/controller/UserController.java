package pl.twitter.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.twitter.bean.LoginData;
import pl.twitter.bean.SessionManager;
import pl.twitter.entity.User;
import pl.twitter.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/register")
	public String register(Model m) {
		m.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registeePost(@Valid @ModelAttribute User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/register";
		}

		this.userRepository.save(user);
		return "redirect:/";

	}
	
	@GetMapping("/login")
	public String login(Model m ) {
		m.addAttribute("loginData", new LoginData());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPost(@ModelAttribute  LoginData loginData , Model m) {
		User user = this.userRepository.findOneByEmail(loginData.getEmail());
		if(user!= null&& user.isPasswordCorrect(loginData.getPassword())) {
			HttpSession s = SessionManager.session();
			s.setAttribute("user", user);
			return "redirect:/";
		}
		m.addAttribute("msg","Wprowadz poprawne dane");
		return "login";
	}
	
	@GetMapping("logout")
	public String logout(Model m) {
		m.addAttribute("loginData", new LoginData());
		HttpSession s =SessionManager.session();
		s.invalidate();
		return"redirect:/";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
}
