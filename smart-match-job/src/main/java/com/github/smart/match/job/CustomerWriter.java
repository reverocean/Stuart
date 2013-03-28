package com.github.smart.match.job;

import com.github.smart.domain.Customer;
import com.github.smart.domain.Profile;
import com.github.smart.match.service.MatchService;
import com.github.smart.service.CustomerService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

public class CustomerWriter implements ItemWriter<Profile> {
    private MatchService matchService;
    private CustomerService customerService;

    @Override
    public void write(List<? extends Profile> items) throws Exception {
        if (items.size() < 2) {
            return;
        }
        Map<Integer, Set<Profile>> matchedProfiles = newHashMap();

        for (int first = 0; first < items.size() - 1; first++) {
            Profile firstProfile = items.get(first);
            Set<Profile> matched = newHashSet();
            for (int second = 1; second < items.size(); second++) {
                matchAndSaveProfile(firstProfile, matched, items.get(second));
            }
            matched.add(firstProfile);
            matchedProfiles.put(firstProfile.getId(), matched);
        }

        writeToCustomer(matchedProfiles);
    }

    private void matchAndSaveProfile(Profile firstProfile, Set<Profile> matched, Profile secondProfile) {
        if (secondProfile.matched()) {
            return;
        }
        if (matchService.matchCustomer(firstProfile.getId(), secondProfile.getId())) {
            matched.add(secondProfile);
            secondProfile.hit();
        }
    }

    private void writeToCustomer(Map<Integer, Set<Profile>> matchedProfiles) {
        for (Set<Profile> profiles : matchedProfiles.values()) {
            if (profiles.size() > 0) {
                Customer customer = new Customer();
                customer.setName(getName(profiles));
                customer.setProfiles(profiles);
                customerService.save(customer);
            }
        }
    }

    private String getName(Set<Profile> profiles) {
        return profiles.iterator().next().getIndividual().getName();
    }

    @Required
    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }

    @Required
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
