package com.example.practicatfgbd.repositories;

import com.example.practicatfgbd.entities.TareaKids;
import com.example.practicatfgbd.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaKidsRepository extends JpaRepository<TareaKids, Long> {
    List<TareaKids> findAllByUser(User user);
}
