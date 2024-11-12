package com.project.esunfeed_back.Entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="user",uniqueConstraints = @UniqueConstraint(columnNames = "userEmail"))
public class User {
    @Id
    @Column(name="userId",length=45)
    private String userId="User"+UUID.randomUUID().toString();
;

    @Column(name="userName",length=255)
    private String userName;

    @Column(name="userEmail",length=255)
    private String userEmail;

    @Column(name="userPassword",length=255)
    private String userPassword;

    @Column(name="userProfilepic",length=255)
    private String userProfilepic;

    @Column(name="userBio",length=255)
    private String userBio;

    @Column(name="userRole",length=255)
    private String userRole;

    public User(){

    }
    public User(String userId,String userName,String userEmail,String userPassword,String userProfilepic
    ,String userBio,String userRole){
        this.userId=userId;
        this.userName=userName;
        this.userEmail=userEmail;
        this.userPassword=userPassword;
        this.userProfilepic=userProfilepic;
        this.userBio=userBio;
        this.userRole=userRole;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getUserProfilepic() {
        return userProfilepic;
    }
    public void setUserProfilepic(String userProfilepic) {
        this.userProfilepic = userProfilepic;
    }
    public String getUserBio() {
        return userBio;
    }
    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
                + userPassword + ", userProfilepic=" + userProfilepic + ", userBio=" + userBio + ", userRole=" + userRole
                + "]";
    }
    
}
