package com.system.travelmanagement.service;

import com.system.travelmanagement.Entity.Contact;
import com.system.travelmanagement.Pojo.ContactPojo;

import java.util.List;

public interface ContactService {
    String save (ContactPojo contactPojo);
    List<Contact> fetchAll();
}

