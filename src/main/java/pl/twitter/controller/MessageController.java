package pl.twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.twitter.entity.Message;

@Controller
	@RequestMapping("/message")
	public class MessageController {
	
	@GetMapping("")
	public String massageList(Model m) {
		m.addAttribute("message", new Message());
		return "message_list";
	}
	
	

}
