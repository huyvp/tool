package com.tool.repo;

import com.tool.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {
    Optional<List<Account>> findByEnvironmentAndActiveIsTrue(String environment);
}
