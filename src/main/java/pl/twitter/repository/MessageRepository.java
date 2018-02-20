package pl.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.twitter.entity.Message;

import pl.twitter.entity.User;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findByRecieverAndCheckedLikeOrderByCreatedDesc(User reciever, int checked);
	List<Message> findBySenderOrderByCreatedDesc( User sender);
	List<Message> findByRecieverOrderByCreatedDesc(User reciever);



}