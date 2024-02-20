package com.nvm.lesson2.repository;

import com.nvm.lesson2.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Long> {

    @Query("""
            select a from AppUser a where a.userName=:username
            """)
    AppUser existsByUserName(String username);

    @Query("""
            select a from AppUser a where a.passWord=:password and a.userName=:username
            """)
    AppUser existsUserLogin(String username,String password);

    @Query("""
            select a from AppUser a where a.userName=:username
            """)
    Optional<AppUser> findByUserName(String username);
}
