package com.apenkovsky.repository.implementations;

import com.apenkovsky.entity.Category;
import com.apenkovsky.repository.CategoryRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Category> loadCategories() {
        List<Category> categories;
        String query = "from Category";
        categories = sessionFactory.getCurrentSession().createQuery(query)
                .list();
        return categories;
    }

    @Override
    @Transactional
    public Category loadCategoryById(Long id) {
        Category category;
        String query = "from Category c where c.id=?";
        category = (Category) sessionFactory.getCurrentSession().createQuery(query)
                .setParameter(0, id)
                .uniqueResult();
        return category;
    }

}
