package com.example.mooclistService;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoocListRepository extends JpaRepository<MoocEntity, String> {

    List<MoocEntity> findAll();
}

