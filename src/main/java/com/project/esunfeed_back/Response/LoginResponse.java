package com.project.esunfeed_back.Response;

import com.project.esunfeed_back.Dto.UserResponseWebDTO;

public class LoginResponse {
    
    private String message;
    private Boolean status;
    private UserResponseWebDTO user;
    private String token;

    public LoginResponse(String message, Boolean status, UserResponseWebDTO user, String token) {
        this.message = message;
        this.status = status;
        this.user = user;
        this.token = token;
    }

    public LoginResponse(String message, Boolean status, UserResponseWebDTO user) {
        this.message = message;
        this.status = status;
        this.user = user;
    }

    public LoginResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public LoginResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public UserResponseWebDTO getUser() {
        return user;
    }

    public void setUser(UserResponseWebDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +"message='" + message +", status=" + status +", user=" + user +", token='" + token  +'}';
    }
    

}
