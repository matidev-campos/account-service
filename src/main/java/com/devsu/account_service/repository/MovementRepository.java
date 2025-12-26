package com.devsu.account_service.repository;

import com.devsu.account_service.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovementRepository extends JpaRepository<Movement, UUID> {

    List<Movement> findByAccountIdOrderByDateDesc(UUID accountId);
}

