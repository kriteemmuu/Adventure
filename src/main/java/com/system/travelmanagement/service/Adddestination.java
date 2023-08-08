package com.system.travelmanagement.service;

import com.system.travelmanagement.Entity.Destination;
import com.system.travelmanagement.Pojo.DestinationPojo;

import java.io.IOException;
import java.util.List;

public interface Adddestination {
    DestinationPojo saveDestination(DestinationPojo destinationPojo) throws IOException;
    List<Destination> fetchAll();
    void deletebyid(Integer id);
   Destination fetchById(Integer id);
}
