package com.apenkovsky.service;

import com.apenkovsky.entity.Category;
import com.apenkovsky.repository.CategoryRepository;
import com.apenkovsky.service.implementations.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);


    }

    @Test
    public void loadCategoryByIdTest(){

        Category category1 = categoryService.loadCategoryById(2L);

        verify(categoryRepository, times(1)).loadCategoryById(2L);
    }

}
