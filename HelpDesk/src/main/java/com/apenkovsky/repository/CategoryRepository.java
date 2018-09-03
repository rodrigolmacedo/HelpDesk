package com.apenkovsky.repository;

import com.apenkovsky.entity.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> loadCategories();

    Category loadCategoryById(Long id);

}
