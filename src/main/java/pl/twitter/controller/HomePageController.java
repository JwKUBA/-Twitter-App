package pl.twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.twitter.entity.Tweet;

@Controller
public class HomePageController {
	
	@GetMapping("")
	public String home(Model m) {//wyswietla home
		m.addAttribute("tweet", new Tweet());
		return "home";
	}
	
	
	

}
