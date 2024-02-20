package com.nvm.backend.repository;

import com.nvm.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("""
            select u from User u where u.userName=:username
            """)
    Optional<User> findUserByUserName(String username);
}
