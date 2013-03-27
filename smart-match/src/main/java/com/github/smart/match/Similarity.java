package com.github.smart.match;

public class Similarity {
    private int percentage;

    public Similarity(int percentage) {
        this.percentage = percentage;
    }

    public boolean greaterThan(int matchThreshold) {
        return this.percentage > matchThreshold;
    }
}
