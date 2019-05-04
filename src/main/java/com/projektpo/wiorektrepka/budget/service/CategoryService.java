package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    void addNewCategory(Category category);

    void editCategory(int categoryId, Category categoryName);

    void deleteCategory(int categoryId);

    Category getById(Integer id);
}