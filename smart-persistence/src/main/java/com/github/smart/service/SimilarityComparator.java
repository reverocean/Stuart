package com.github.smart.service;

import com.github.smart.domain.Customer;

public interface SimilarityComparator<T> {
    public int compare(T target);
}
