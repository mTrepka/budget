package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("categoryService")
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
}
