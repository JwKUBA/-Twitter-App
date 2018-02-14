package pl.twitter.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import pl.twitter.entity.Comment;
import pl.twitter.entity.Tweet;


public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findByTweet(Tweet tweet);
	List<Comment> findByTweetIdOrderByCreatedDesc(Long id);

}
