package com.system.travelmanagement.Pojo;

import com.system.travelmanagement.Entity.Contact;
import com.system.travelmanagement.Entity.Destination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactPojo {
    private Integer id;

    private String name;

    private String email;

    private String messgae;

    public ContactPojo(Contact contact){
        this.id=contact.getId();
        this.name= contact.getName();
        this.email= contact.getEmail();
        this.messgae= contact.getMessage();

    }
}
