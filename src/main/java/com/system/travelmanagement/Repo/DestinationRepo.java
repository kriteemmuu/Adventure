package com.system.travelmanagement.Repo;

import com.system.travelmanagement.Entity.Destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepo extends JpaRepository<Destination, Integer> {
}
