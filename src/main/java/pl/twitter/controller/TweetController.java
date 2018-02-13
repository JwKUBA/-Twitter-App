package pl.twitter.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.twitter.bean.SessionManager;
import pl.twitter.entity.Tweet;
import pl.twitter.entity.User;
import pl.twitter.repository.TweetRepository;

@Controller
@RequestMapping("/tweet")
public class TweetController {
	
	@Autowired
	TweetRepository tweetRepository;
	
	@PostMapping("/add")
	public String addPost(@Valid @ModelAttribute Tweet tweet,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "home";
		}
		HttpSession s = SessionManager.session();
		User u = (User)s.getAttribute("user");//pobieramy usera z sesji
		tweet.setUser(u);//zapisujemy usera z sesji jako autora tweeta
		tweet.setCreated(new Date());//zapisujemy date utworzenia
		this.tweetRepository.save(tweet); //zapisujemy tweeta
		return "redirect:/";
	}

}
