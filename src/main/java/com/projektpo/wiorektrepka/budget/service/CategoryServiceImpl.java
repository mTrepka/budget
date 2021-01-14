package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.Category;
import com.projektpo.wiorektrepka.budget.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("categoryService")
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }


    @Override
    public void editCategory(int categoryId, Category category) {
        Optional<Category> c = categoryRepository.findById(categoryId);
        if(checkCategory(c,categoryId)){
            Category cat = c.get();
            cat.setName(category.getName());
            categoryRepository.saveAndFlush(category);
        }
    }

    private Category getCategoryFromBody(Category category){
        Category c = new Category();
        c.setName(category.getName());
        return c;
    }

    @Override
    public Category getById(Integer id) {
        Category c = categoryRepository.findById(id).get();
        return c;
    }

    private boolean checkCategory(Optional<Category> c, Integer id) {
        return id != null && c.isPresent();
    }
}
