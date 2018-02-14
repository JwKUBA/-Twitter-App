package pl.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.twitter.entity.Tweet;
import pl.twitter.repository.TweetRepository;

@Controller
public class HomePageController {
	@Autowired
	TweetRepository tweetRepository;
	
	@GetMapping("")
	public String home(Model m) {//wyswietla home
		m.addAttribute("tweet", new Tweet());
		return "home";
	}
	
	
	
	
	@ModelAttribute("availableTweets")
	public List<Tweet> getTweets() {
		return this.tweetRepository.findAll();
	}
	

}
