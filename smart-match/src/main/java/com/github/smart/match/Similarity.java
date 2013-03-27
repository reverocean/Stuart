package com.github.smart.match;

public class Similarity {
    private int percentage;

    public Similarity(int percentage) {
        this.percentage = percentage;
    }

    public boolean equalOrGreaterThan(int matchThreshold) {
        return this.percentage >= matchThreshold;
    }

    public int getPercentage() {
        return percentage;
    }
}
