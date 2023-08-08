package com.system.travelmanagement.service.impl;

import com.system.travelmanagement.Entity.Contact;
import com.system.travelmanagement.Pojo.ContactPojo;
import com.system.travelmanagement.Repo.ContactRepo;
import com.system.travelmanagement.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepo contactRepo;



    @Override
    public String save(ContactPojo contactPojo) {
        Contact contact=new Contact();
        contact.setName(contactPojo.getName());
        contact.setEmail(contactPojo.getEmail());
        contact.setMessage(contactPojo.getMessgae());
        contactRepo.save(contact);
        return "Created";
    }

    @Override
    public List<Contact> fetchAll() {
        return this.contactRepo.findAll()  ;
    }
}
