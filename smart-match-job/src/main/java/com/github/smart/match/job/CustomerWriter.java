package com.github.smart.match.job;

import com.github.smart.domain.MatchIndicator;
import com.github.smart.domain.Profile;
import com.github.smart.match.service.MatchService;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class CustomerWriter implements ItemWriter<Profile> {
    private MatchService matchService;

    @Override
    public void write(List<? extends Profile> items) throws Exception {
        if (items.size() < 2) {
            return;
        }
        Map<Integer, List<Profile>> matchedProfiles = newHashMap();

        for (int first = 0; first < items.size() - 1; first++) {
            Profile firstProfile = items.get(first);
            List<Profile> matched = newArrayList();
            for (int second = 1; second < items.size(); second++) {
                matchAndSaveProfile(firstProfile, matched, items.get(second));
            }
            matched.add(firstProfile);
            matchedProfiles.put(firstProfile.getId(), matched);
        }

        writeToCustomer(matchedProfiles);
    }

    private void matchAndSaveProfile(Profile firstProfile, List<Profile> matched, Profile secondProfile) {
        if (hadBeenMatched(secondProfile)) {
            return;
        }
        if (matchService.matchCustomer(firstProfile.getId(), secondProfile.getId())) {
            matched.add(secondProfile);
            ((MatchIndicator) secondProfile).hit();
        }
    }

    private void writeToCustomer(Map<Integer, List<Profile>> matchedProfiles) {
        for (List<Profile> profiles : matchedProfiles.values()) {

        }
    }

    private boolean hadBeenMatched(Profile secondProfile) {
        return ((MatchIndicator)secondProfile).matched();
    }
}
