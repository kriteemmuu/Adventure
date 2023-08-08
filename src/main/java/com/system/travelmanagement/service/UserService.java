package com.system.travelmanagement.service;
import com.system.travelmanagement.Entity.User;
import com.system.travelmanagement.Pojo.UserPojo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    String saveUser(UserPojo userPojo);


    UserPojo findByEmail(String email);

    List<User> fetchAll();
    void deletebyid(Integer id);
    User fetchbyid(Integer id);

    void processPasswordResetRequest(String email);

    void sendEmail();
}
