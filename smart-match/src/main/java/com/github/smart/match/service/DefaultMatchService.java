package com.github.smart.match.service;

import com.github.smart.match.Similarity;
import com.github.smart.match.strategy.MatchStrategy;
import com.github.smart.service.ProfileService;

public class DefaultMatchService implements MatchService{
    public static final int MATCH_THRESHOLD = 100;
    private MatchStrategy matchStrategy;
    private ProfileService profileService;

    @Override
    public boolean matchCustomer(int firstProfileId, int secondProfileId) {
        Similarity similarity = matchStrategy.match(
                profileService.findById(firstProfileId),
                profileService.findById(secondProfileId));
        return similarity.equalOrGreaterThan(MATCH_THRESHOLD);
    }

    public void setMatchStrategy(MatchStrategy matchStrategy) {
        this.matchStrategy = matchStrategy;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
