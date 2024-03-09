package com.nvm.lession4.repository;

import com.nvm.lession4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("""
            SELECT u FROM user u WHERE u.email = :userName
            """)
    Optional<User> getUserByUserName(String userName);

}
