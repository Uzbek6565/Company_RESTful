package com.example.task2_1_1.repository;

import com.example.task2_1_1.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
}
