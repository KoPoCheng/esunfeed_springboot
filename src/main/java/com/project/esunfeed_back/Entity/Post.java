package com.project.esunfeed_back.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @Column(name = "userId")
    private String userId;

    @Column(name="content",nullable = false,columnDefinition = "TEXT")
    private String content;

    @Column(name="image",columnDefinition = "LONGTEXT")
    private String image;

    @Column(name="createdAt")
    private LocalDateTime createdAt;

    @Column(name="isDeleted")
    private boolean isDeleted = false;

    public Post(String userId, String content, String image, LocalDateTime createdAt, boolean isDeleted) {
        this.userId = userId;
        this.content = content;
        this.image = image;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    public Post() {}

    public Long getPostId() {
        return id;
    }

    public void setPostId(Long postId) {
        this.id = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public boolean isDeleted() {        
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Post{" +"postId=" + id +", user=" + user +", userId='" + userId +", content='" + content +", image='" + image +", createdAt=" + createdAt + ", isDeleted=" + isDeleted + '}';
    }

}
