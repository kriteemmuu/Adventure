package com.system.travelmanagement.Repo;


import com.system.travelmanagement.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book,Integer> {
    @Query(value = "SELECT * FROM book where user_id=?1", nativeQuery = true)
    List<Book> findBookingById(Integer id);
}
