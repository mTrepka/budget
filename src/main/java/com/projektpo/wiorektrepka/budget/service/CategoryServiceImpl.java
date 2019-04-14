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
        categories.forEach(e -> e.getEventList().forEach(b->
            {
                b.setOwner(null);
                b.setCategory(null);
            })
        );
        return categories;
    }

    @Override
    public void addNewCategory(Category category) {
        Category c = getCategoryFromBody(category);
        categoryRepository.saveAndFlush(c);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Optional<Category> c = categoryRepository.findById(categoryId);

        if (checkCategory(c, categoryId))
            categoryRepository.deleteById(categoryId);

    }

    @Override
    public void editCategory(int categoryId, String categoryName) {
        Optional<Category> c = categoryRepository.findById(categoryId);
        if(checkCategory(c,categoryId)){
            Category category = c.get();
            category.setName(categoryName);
            categoryRepository.saveAndFlush(category);
        }
    }

    private Category getCategoryFromBody(Category category){
        Category c = new Category();
        c.setName(category.getName());
        c.setCategoryId(category.getCategoryId());
        return c;
    }

    public boolean checkCategory(Optional<Category> c, Integer id) {
        return id != null && c.isPresent();
    }
}
