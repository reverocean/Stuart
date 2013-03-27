package com.github.smart.match;

public class Similarity {
    private static final int SIMILARITY_THRESHOLD = 100;
    private int percentage;

    public Similarity(int percentage) {
        this.percentage = percentage;
    }

    public boolean satisfied() {
        return this.percentage >= SIMILARITY_THRESHOLD;
    }

    public int getPercentage() {
        return percentage;
    }
}
