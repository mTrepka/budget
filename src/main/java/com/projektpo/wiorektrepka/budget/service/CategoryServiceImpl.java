package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.Category;
import com.projektpo.wiorektrepka.budget.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryRepository.findAll();
        categories.forEach(e -> {
            e.getEventList().forEach(b->
            {
                b.setOwner(null);
                b.setCategory(null);
            });
        });
        return categories;
    }
}
