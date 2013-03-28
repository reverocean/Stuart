package com.github.smart.service;

import com.github.smart.domain.Profile;
import org.hibernate.SessionFactory;

public class DefaultProfileService implements ProfileService {
    private SessionFactory sessionFactory;

    @Override
    public Profile findById(int firstProfileId) {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
