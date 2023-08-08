package com.system.travelmanagement.Repo;


import com.system.travelmanagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = "select * from USERS where email=?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "UPDATE users SET password =?1 WHERE email = ?2", nativeQuery = true)
    void updatePassword(String password, String email);


}
