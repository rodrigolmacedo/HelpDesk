package com.apenkovsky.service.implementations;

import com.apenkovsky.entity.Category;
import com.apenkovsky.repository.CategoryRepository;
import com.apenkovsky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> loadCategories() {
        return categoryRepository.loadCategories();
    }

    @Override
    public Category loadCategoryById(Long id) {
        return categoryRepository.loadCategoryById(id);
    }

}
