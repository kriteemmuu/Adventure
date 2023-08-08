package com.system.travelmanagement.Pojo;

import com.system.travelmanagement.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {

    private Integer id;

    private String email;


    private String fullname;

    private String password;

    public UserPojo(User user){
        this.id= user.getId();
        this.email= user.getEmail();
        this.fullname= user.getFullname();
        this.password=user.getPassword();
    }
}



