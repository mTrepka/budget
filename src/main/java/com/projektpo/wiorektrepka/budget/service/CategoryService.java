package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    void editCategory(int categoryId, Category categoryName);

    Category getById(Integer id);
}