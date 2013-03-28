package com.github.smart.service;

import com.github.smart.domain.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class DefaultProfileService implements ProfileService {
    private SessionFactory sessionFactory;

    @Override
    public Profile findById(int firstProfileId) {
        return null;
    }

    @Override
    @Transactional
    public void save(Profile profile)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(profile);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
