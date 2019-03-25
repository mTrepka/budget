package com.projektpo.wiorektrepka.budget.repository;

import com.projektpo.wiorektrepka.budget.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
