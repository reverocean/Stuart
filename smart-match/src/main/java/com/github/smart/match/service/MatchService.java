package com.github.smart.match.service;

import com.github.smart.domain.Profile;

public interface MatchService {
    public boolean matchCustomer(Profile firstProfile, Profile secondProfile);
}
