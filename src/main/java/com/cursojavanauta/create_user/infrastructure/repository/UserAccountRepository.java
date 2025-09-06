package com.cursojavanauta.create_user.infrastructure.repository;

import com.cursojavanauta.create_user.infrastructure.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    boolean existsByEmail(String email);

    Optional<UserAccount> findByEmail(String email);

}
