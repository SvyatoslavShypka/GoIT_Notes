package com.goit.goit_notes.repository;

import com.goit.goit_notes.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long>, ListCrudRepository<UserAccountEntity, Long> {

    UserAccountEntity findUserAccountEntityByUsername(String username);
//    List<UserAccountEntity> findAll();

    void deleteByUsername(String username);

}
