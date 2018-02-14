package pl.twitter.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.fabric.xmlrpc.base.Data;

import pl.twitter.bean.SessionManager;
import pl.twitter.entity.Comment;
import pl.twitter.entity.Tweet;
import pl.twitter.entity.User;
import pl.twitter.repository.CommentRepository;
import pl.twitter.repository.TweetRepository;

@Controller
@RequestMapping("/tweet")
public class TweetController {
	
	@Autowired
	TweetRepository tweetRepository;
	@Autowired
	CommentRepository commentRepository;
	
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
	
	@GetMapping("/{id}")
	public String singleTweet(Model m ,@PathVariable Long id) {
		Tweet tweet = this.tweetRepository.findOne(id);//znajduje tweet uzytkowika
		List<Comment> comments = this.commentRepository.findByTweetIdOrderByCreatedDesc(id);//wszytkie komentarze 
		m.addAttribute("tweet", tweet);
		m.addAttribute("comments", comments);
		m.addAttribute("comment", new Comment());
		return "singleTweet";
		
	}
	
	
		@PostMapping("addComment/{Id}")
		public String addPost(@Valid @ModelAttribute Comment comment, BindingResult bindingResult,@PathVariable Long Id) {
		if(bindingResult.hasErrors()) {
			return "redirect:/tweet/"+Id;
		}
		HttpSession s = SessionManager.session();
		User u =(User)s.getAttribute("user");//pobieramy usera z sesji
		Tweet tweet = this.tweetRepository.findOne(Id);
		comment.setTweet(tweet);
		comment.setUser(u);//user z sesji
		comment.setCreated(new Date());
		this.commentRepository.save(comment);
		return "redirect:/tweet/"+Id;
		
		
		
		
		
		
		
		
		

}
}
