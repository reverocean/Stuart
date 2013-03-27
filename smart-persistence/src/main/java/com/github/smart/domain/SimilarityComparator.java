package com.github.smart.domain;

import com.github.smart.domain.Customer;

public interface SimilarityComparator<T> {
    public int compare(T target);
}
