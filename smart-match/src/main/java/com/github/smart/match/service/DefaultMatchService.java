package com.github.smart.match.service;

import com.github.smart.match.Similarity;
import com.github.smart.match.strategy.MatchStrategy;
import com.github.smart.service.ProfileService;
import org.springframework.beans.factory.annotation.Required;

public class DefaultMatchService implements MatchService{
    private MatchStrategy matchStrategy;
    private ProfileService profileService;

    @Override
    public boolean matchCustomer(int firstProfileId, int secondProfileId) {
        Similarity similarity = matchStrategy.match(
                profileService.findById(firstProfileId),
                profileService.findById(secondProfileId));
        return similarity.satisfied();
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
