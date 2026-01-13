package com.example.practicatfgbd.repositories;

import com.example.practicatfgbd.entities.TareaMonitor;
import com.example.practicatfgbd.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaMonitorRepository extends JpaRepository<TareaMonitor, Long> {

    List<TareaMonitor> findByUser(User user);
}