package com.indra.logistics;

import java.util.concurrent.ThreadLocalRandom;

public class TrackingIdGenerator {

    private static final String ALPHANUMERIC_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int RANDOM_SUFFIX_LENGTH = 8;

    public String generate(String origin, String destination) {
        TrackingRoute route = new TrackingRoute(origin, destination);
        return route.origin() + "-" + route.destination() + "-" + randomSuffix();
    }

    private String randomSuffix() {
        StringBuilder suffix = new StringBuilder(RANDOM_SUFFIX_LENGTH);
        for (int position = 0; position < RANDOM_SUFFIX_LENGTH; position++) {
            int characterIndex = ThreadLocalRandom.current().nextInt(ALPHANUMERIC_UPPERCASE.length());
            suffix.append(ALPHANUMERIC_UPPERCASE.charAt(characterIndex));
        }
        return suffix.toString();
    }
}
