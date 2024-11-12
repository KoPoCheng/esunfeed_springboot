package com.project.esunfeed_back.Dto;

public class RegistrationDTO {

    private String userName;
    private String userEmail;
    private String userPassword;
    private String userProfilepic;
    private String userBio;
    
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

    
}
