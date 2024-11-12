package com.project.esunfeed_back.Dto;

import com.project.esunfeed_back.Entity.User;

public class UserDTO {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userProfilepic;
    private String userBio;
    private String userRole;

    public UserDTO(User user){
        this.userId=user.getUserId();
        this.userName=user.getUserName();
        this.userEmail=user.getUserEmail();
        this.userPassword=user.getUserPassword();
        this.userProfilepic=user.getUserProfilepic();
        this.userBio=user.getUserBio();
        this.userRole=user.getUserRole();
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

}
