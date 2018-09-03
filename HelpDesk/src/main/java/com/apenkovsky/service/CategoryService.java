package com.apenkovsky.service;

import com.apenkovsky.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> loadCategories();

    Category loadCategoryById(Long id);

}
