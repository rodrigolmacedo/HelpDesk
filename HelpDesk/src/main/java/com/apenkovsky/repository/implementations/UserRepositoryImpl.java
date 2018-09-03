package com.apenkovsky.repository.implementations;

import com.apenkovsky.entity.User;
import com.apenkovsky.enums.Role;
import com.apenkovsky.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public User loadUserByEmail(String email) {
        User user;
        String query = "from User u  where u.email=?";
        user = (User) sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter(0, email)
                .uniqueResult();
        return user;
    }

    @Override
    @Transactional
    public User loadUserById(Long id) {
        User user;
        String query = "from User u where u.id=?";
        user = (User) sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter(0, id)
                .uniqueResult();
        return user;
    }

    @Override
    @Transactional
    public List<User> loadManagers() {
        List<User> managers;
        String hql = "from User u where u.role = ?";
        managers = sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter(0, Role.MANAGER)
                .list();
        return managers;
    }

    @Override
    public List<User> loadEngineers() {
        List<User> managers;
        String hql = "from User u where u.role = ?";
        Session session = sessionFactory.openSession();
        managers = session.createQuery(hql)
                .setParameter(0, Role.ENGINER)
                .list();
        session.close();
        return managers;
    }

}
