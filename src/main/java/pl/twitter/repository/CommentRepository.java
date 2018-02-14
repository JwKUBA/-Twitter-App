package pl.twitter.repository;

import java.util.List;

import javax.xml.stream.events.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.twitter.entity.Tweet;
import pl.twitter.entity.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllByCommentTweet(Tweet tweet);
	List<Comment> findAllByCommentId(Long Id);


}
