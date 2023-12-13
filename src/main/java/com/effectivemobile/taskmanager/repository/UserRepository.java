package com.effectivemobile.taskmanager.repository;

import com.effectivemobile.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "select * from taskmanager where name in(:username) limit 1")
    Optional<User> findByUsername(String username);

    @Query(nativeQuery = true, value = "SELECT\n" +
            "    CASE WHEN EXISTS \n" +
            "    (\n" +
            "        SELECT * FROM taskmanager WHERE name in(:username)\n" +
            "    )\n" +
            "    THEN 'TRUE'\n" +
            "    ELSE 'FALSE'\n" +
            "END")
    Boolean existsByUsername(String username);

    @Query(nativeQuery = true, value = "SELECT\n" +
            "    CASE WHEN EXISTS \n" +
            "    (\n" +
            "        SELECT * FROM taskmanager WHERE email in(:email)\n" +
            "    )\n" +
            "    THEN 'TRUE'\n" +
            "    ELSE 'FALSE'\n" +
            "END")
    Boolean existsByEmail(String email);
}
