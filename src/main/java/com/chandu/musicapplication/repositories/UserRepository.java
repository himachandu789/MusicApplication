package com.chandu.musicapplication.repositories;

import com.chandu.musicapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByMail(String mail);
}
