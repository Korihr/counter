package org.example.repository;

import org.example.entity.CounterIncrement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterIncrementRepository extends JpaRepository<CounterIncrement, Long> {
}