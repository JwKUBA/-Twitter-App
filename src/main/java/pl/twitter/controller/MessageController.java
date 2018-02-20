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
	
	@GetMapping("/{id}")
	public String message(Model m, @PathVariable long id) {
		HttpSession s = SessionManager.session();
		if (s != null) {
			User sender = (User) s.getAttribute("user");
			if (sender != null && sender.getId() == id) {
				return "redirect:/message";
			}
		}
		m.addAttribute("message", new Message());
		m.addAttribute("recieverId", id);
		return "send_message";
	}
	
	
	
	@PostMapping("/add/{recieverId}")
	public String addPost(@Valid @ModelAttribute Message message , @PathVariable long recieverId, BindingResult bindingResult) {
		if(bindingResult.hasErrors() ) {
			return "redirect:/message/" + recieverId;
		}
		HttpSession s = SessionManager.session();
		User sender = (User) s.getAttribute("user");//pobieramy z sesji usera
		message.setSender(sender);// ustawiamy go jako wysyłajacego
		User reciever = this.userRepository.findOne(recieverId);//znajdujemy w bazie danych po id usera odbiorce
		message.setReciever(reciever);//zapisujemy do encji message
		message.setCreated(new Date());
		this.messageRepository.save(message);
		return "redirect:/message";	
		
	}
	
	@GetMapping("/details/{id}")
	public String messageDetails(Model m ,@PathVariable long id) {
	Message message = this.messageRepository.findOne(id);
	message.setChecked(1);
	this.messageRepository.save(message);
	m.addAttribute("message", message);
	return "read_message";
	

	
	}
	

	@ModelAttribute("readMessages")//przeczytne
	public List <Message> getMessage(){
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		return this.messageRepository.findByRecieverAndCheckedLikeOrderByCreatedDesc(u,1);
	
}
	
	@ModelAttribute("unreadMessages")//nieprzeczytane
	public List <Message> getUnreadMessage(){
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		return this.messageRepository.findByRecieverAndCheckedLikeOrderByCreatedDesc(u,0);
	
}
	
	
	@ModelAttribute("sentMessages")//wysłane
	public List <Message> sentMessage(){
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		return this.messageRepository.findBySenderOrderByCreatedDesc(u);
	
}
	
	@ModelAttribute("userMessages")//wszystkie
	public List <Message> userMessages(){
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		return this.messageRepository.findByRecieverOrderByCreatedDesc(u);
	
	
	}
	

}
