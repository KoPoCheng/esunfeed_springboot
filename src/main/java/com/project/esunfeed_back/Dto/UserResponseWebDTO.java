package com.project.esunfeed_back.Dto;

public class UserResponseWebDTO {
    
    private String userId;
    private String userName;
    private String userEmail;
    private String userProfilepic;
    private String userBio;
    private String userRole;
    
    public UserResponseWebDTO(String userId, String userName, String userEmail, String userProfilepic, String userBio, String userRole) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userProfilepic = userProfilepic;
        this.userBio = userBio;
        this.userRole = userRole;
    }
    public UserResponseWebDTO() {
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
        return "UserResponseWebDTO [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail
                + ", userProfilepic=" + userProfilepic + ", userBio=" + userBio + ", userRole=" + userRole + "]";
    }
}
