package pl.twitter.controller;

import java.util.Date;

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

import pl.twitter.bean.SessionManager;
import pl.twitter.entity.Message;
import pl.twitter.entity.User;
import pl.twitter.repository.MessageRepository;
import pl.twitter.repository.UserRepository;



@Controller
	@RequestMapping("/message")
	public class MessageController {
	
	
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	 UserRepository userRepository;
	
	@GetMapping("")
	public String massageList(Model m) {
		m.addAttribute("message", new Message());
		return "message_list";
	}
	
	@PostMapping("/add/{recieverId")
	public String addPost(@Valid @ModelAttribute Message message , @PathVariable long id, BindingResult bindingResult) {
		if(bindingResult.hasErrors() ) {
			return "redirect:/message/" + id;
		}
		HttpSession s = SessionManager.session();
		User sender = (User) s.getAttribute("user");
		message.setSender(sender);
		User reciever = this.userRepository.findOne(id);
		message.setReciever(reciever);
		message.setCreated(new Date());
		this.messageRepository.save(message);
		return "redirect:/message";	
		
	}

}
