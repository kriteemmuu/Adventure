package com.system.travelmanagement.Repo;
import com.system.travelmanagement.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ContactRepo extends JpaRepository<Contact,Integer> {
    Optional<Contact> findContactByEmail(String s);
}
