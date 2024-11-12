package com.project.esunfeed_back.Dto;

import java.time.LocalDateTime;

public class CommentDTO {
    
    private Long commentId;
    private String userId;
    private Long postId;
    private String content;
    private LocalDateTime createdAt;
    public CommentDTO(Long commentId, String userId, Long postId, String content, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public CommentDTO() {}

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +"commentId=" + commentId +", userId='" + userId +", postId=" + postId +", content='" + content +", createdAt='" + createdAt +'}';
    }

}
