package com.projektpo.wiorektrepka.budget.repository;

import com.projektpo.wiorektrepka.budget.domain.CodeEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeEventRepository extends JpaRepository<CodeEvent, Long> {
}
