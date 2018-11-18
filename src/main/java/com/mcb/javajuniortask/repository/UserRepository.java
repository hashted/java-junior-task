package com.mcb.javajuniortask.repository;

import com.mcb.javajuniortask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByLogin(String login);
}