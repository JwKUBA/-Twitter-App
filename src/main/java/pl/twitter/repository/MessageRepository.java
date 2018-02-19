package pl.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.twitter.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	



}