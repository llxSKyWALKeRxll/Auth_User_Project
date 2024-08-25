package com.v1.authuser.repository;

import com.v1.authuser.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Contains db methods for the UserEntity (user table) class.
 *
 * @author Vansh Pratap Singh
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Find user by email.
     *
     * @param email             Email.
     * @return                  Response object.
     */
    @Query(nativeQuery = true,
    value = "select * from user where email = :email")
    Optional<UserEntity> findByEmail(
            @Param(value = "email") String email
    );

}
