package com.cursojavanauta.create_user.infrastructure.repository;

import com.cursojavanauta.create_user.infrastructure.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
