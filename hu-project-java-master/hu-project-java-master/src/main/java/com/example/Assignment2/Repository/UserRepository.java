package com.example.Assignment2.Repository;
import com.example.Assignment2.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    @Query("SELECT s FROM User s WHERE s.displayname=?1")
    Optional<User> findUserByDisplayName(String displayname);

}
