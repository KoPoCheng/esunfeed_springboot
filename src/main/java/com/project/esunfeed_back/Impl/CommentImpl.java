package com.project.esunfeed_back.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.esunfeed_back.Dto.CommentDTO;
import com.project.esunfeed_back.Entity.Comment;
import com.project.esunfeed_back.Entity.Post;
import com.project.esunfeed_back.Entity.User;
import com.project.esunfeed_back.Function.ResourceNotFoundException;
import com.project.esunfeed_back.Repo.CommentRepo;
import com.project.esunfeed_back.Repo.PostRepo;
import com.project.esunfeed_back.Repo.UserRepo;
import com.project.esunfeed_back.Service.CommentService;

@Service
public class CommentImpl implements CommentService {
    
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        // 獲取用戶和帖子
        User user = userRepo.findById(commentDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + commentDTO.getUserId()));
        Post post = postRepo.findById(commentDTO.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + commentDTO.getPostId()));

        // 創建新的 Message 實體
        Comment comment = new Comment();
        comment.setUser(user); // 設置用戶
        comment.setPost(post); // 設置帖子
        comment.setComment(commentDTO.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        // 保存 Message
        commentRepo.save(comment);

        // 創建並返回 MessageDTO
        return new CommentDTO(
                comment.getCommentId(),
                comment.getUser().getUserId(),
                comment.getPost().getPostId(),
                comment.getComment(),
                comment.getCreatedAt());
    }
    
    @Override
    public List<CommentDTO> getCommentByPostId(Long postId) {
        List<Comment> comments = commentRepo.findByPostId(postId);
        return comments.stream()
                .map(comment -> new CommentDTO(
                        comment.getCommentId(),
                        comment.getUser().getUserId(),
                        comment.getPost().getPostId(),
                        comment.getComment(),
                        comment.getCreatedAt()))
                .collect(Collectors.toList());
    }

    
}
