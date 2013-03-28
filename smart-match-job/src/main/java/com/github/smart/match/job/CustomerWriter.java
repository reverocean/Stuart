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
        if (withoutSimilarCustomer(items)) {
            convertAndSaveCustomers(items);
            return;
        }
        writeSimilarCustomers(items);
    }

    private void writeSimilarCustomers(List<? extends Profile> items) {
        Map<Integer, Set<Profile>> matchedProfiles = newHashMap();
        fillSimilarCustomers(items, matchedProfiles);
        writeCustomers(matchedProfiles);
    }

    private void fillSimilarCustomers(List<? extends Profile> items, Map<Integer, Set<Profile>> matchedProfiles) {
        for (int first = 0; first < items.size() - 1; first++) {
            Profile firstProfile = items.get(first);
            if (firstProfile.matched()) {
                continue;
            }

            fillSimilarCustomersWithFirstProfile(items, firstProfile, matchedProfiles);
        }
    }

    private void fillSimilarCustomersWithFirstProfile(List<? extends Profile> items, Profile firstProfile, Map<Integer, Set<Profile>> matchedProfiles) {
        Set<Profile> matched = newHashSet();
        for (int second = 1; second < items.size(); second++) {
            matchAndSaveProfile(firstProfile, matched, items.get(second));
        }
        matched.add(firstProfile);
        matchedProfiles.put(firstProfile.getId(), matched);
        firstProfile.hit();
    }

    private boolean withoutSimilarCustomer(List<? extends Profile> items) {
        return items.size() < 2;
    }

    private void convertAndSaveCustomers(List<? extends Profile> items) {
        for (Profile item : items) {
            Customer customer = new Customer();
            customer.setName(item.getIndividual().getName());
            customerService.save(customer);
        }
    }

    private void matchAndSaveProfile(Profile firstProfile, Set<Profile> matched, Profile secondProfile) {
        if (matchService.matchCustomer(firstProfile, secondProfile)) {
            matched.add(secondProfile);
            secondProfile.hit();
        }
    }

    private void writeCustomers(Map<Integer, Set<Profile>> matchedProfiles) {
        for (Set<Profile> profiles : matchedProfiles.values()) {
            Customer customer = convertToCustomer(profiles);
            customerService.save(customer);
        }
    }

    private Customer convertToCustomer(Set<Profile> profiles) {
        Customer customer = new Customer();
        customer.setName(getName(profiles));
        if (profiles.size() > 0) {
            customer.setProfiles(profiles);
        }
        return customer;
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
