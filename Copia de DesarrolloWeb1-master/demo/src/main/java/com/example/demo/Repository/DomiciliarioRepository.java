package com.example.demo.Repository;

import com.example.demo.Model.DomiciliarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomiciliarioRepository extends JpaRepository<DomiciliarioModel, Long> {
}
