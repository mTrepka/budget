package com.projektpo.wiorektrepka.budget.repository;

import com.projektpo.wiorektrepka.budget.domain.AuthorizationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorizationLogRepository extends JpaRepository<AuthorizationLog, Long> {
	List<AuthorizationLog> findAllByUser(String user);
}
