package pl.twitter.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.twitter.bean.SessionManager;
import pl.twitter.entity.Tweet;
import pl.twitter.entity.User;
import pl.twitter.repository.TweetRepository;
import pl.twitter.repository.UserRepository;

@Controller
public class UserViewController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	TweetRepository tweetRepository;

	@GetMapping("/user")
	public String register(Model m) {
		m.addAttribute("tweet", new Tweet());
		return "/user";
	}
	
	@ModelAttribute("allTweets")
	public List<Tweet> allTweets() {
		HttpSession s = SessionManager.session();
		User u =(User)s.getAttribute("user");//bo wyswietlam tweety usera z sesji
		tweetRepository.findAllByUserIdOrderByCreatedDesc(u.getId());
		return tweetRepository.findAllByUserIdOrderByCreatedDesc(u.getId());
		
		
		
		
		
		
		
		
		
		
		
		
}
}