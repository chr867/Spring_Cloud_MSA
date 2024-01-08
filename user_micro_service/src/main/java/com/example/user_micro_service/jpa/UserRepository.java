package com.example.user_micro_service.jpa;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUserId(String userId);
    UserEntity findByEmail(String email);
}
