package com.project.esunfeed_back.Dto;

import java.time.LocalDateTime;

public class PostDTO {
    private Long postId; 
    private String userId;
    private String content;
    private String image;
    private LocalDateTime createdAt;

    public PostDTO(Long postId, String userId, String content, String image, LocalDateTime createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.image = image;
        this.createdAt = createdAt;
    }

    public PostDTO() {
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return "PostDTO{" +"postId=" + postId +", userId='" + userId +", content='" + content +", image='" + image +", createdAt='" + createdAt +'}';
    }
}
