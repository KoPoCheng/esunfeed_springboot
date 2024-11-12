package com.project.esunfeed_back.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.esunfeed_back.Entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

    Optional<Comment> findByMessageId(Long messageId);

    List<Comment> findByPostIdAndIsdeletedFalse(Long postId);

    // List<Message> findByUserid(Long userid);
    Optional<Comment> findById(Long messageId);
}
