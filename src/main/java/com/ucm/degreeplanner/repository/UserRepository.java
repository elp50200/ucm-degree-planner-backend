package com.ucm.degreeplanner.repository;

import com.ucm.degreeplanner.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

//this is just a placeholder file
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUsername(String username);
}
