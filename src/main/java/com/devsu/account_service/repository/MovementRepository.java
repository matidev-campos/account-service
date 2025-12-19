package com.devsu.account_service.repository;

import com.devsu.account_service.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MovementRepository extends JpaRepository<Movement, UUID> {

    List<Movement> findByAccountIdOrderByDateDesc(UUID accountId);
}

