package com.project.esunfeed_back.Service;

import java.util.List;

import com.project.esunfeed_back.Dto.LoginDTO;
import com.project.esunfeed_back.Dto.RegistrationDTO;
import com.project.esunfeed_back.Entity.User;
import com.project.esunfeed_back.Response.LoginResponse;

public interface UserService {

    LoginResponse loginUser(LoginDTO loginDTO);

    List<User> getUsersByIds(List<Long> userid);

    void registerUser(RegistrationDTO registrationDTO) throws Exception;
}
