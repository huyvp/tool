package com.tool.repo;

import com.tool.entity.Tab;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TabRepo extends JpaRepository<Tab, String> {
    Optional<Tab> findByName(String name);

    List<Tab> findAll(Sort sort);
}
