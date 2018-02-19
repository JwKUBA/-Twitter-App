package pl.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.twitter.entity.Tweet;



public interface TweetRepository extends JpaRepository<Tweet, Long> {
	List<Tweet> findAllByUserUsername(String username);//wszystkie tweety
	List<Tweet> findAllByUserIdOrderByCreatedDesc(Long id);
	List<Tweet> findAllByOrderByIdDesc();


}