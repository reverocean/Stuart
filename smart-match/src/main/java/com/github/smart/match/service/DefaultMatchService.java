package com.github.smart.match.service;

import com.github.smart.domain.Profile;
import com.github.smart.match.Similarity;
import com.github.smart.match.strategy.MatchStrategy;
import com.github.smart.service.ProfileService;
import org.springframework.beans.factory.annotation.Required;

public class DefaultMatchService implements MatchService{
    private MatchStrategy matchStrategy;
    private ProfileService profileService;

    @Override
    public boolean matchCustomer(Profile firstProfile, Profile secondProfile) {
        Similarity match = matchStrategy.match(firstProfile, secondProfile);
        return match.satisfied();
    }

    @Required
    public void setMatchStrategy(MatchStrategy matchStrategy) {
        this.matchStrategy = matchStrategy;
    }

    @Required
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
