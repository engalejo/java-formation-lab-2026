package com.indra.logistics;

import java.util.Locale;

public record TrackingRoute(String origin, String destination) {

    public TrackingRoute {
        origin = normalizeRequiredCode(origin, "origin");
        destination = normalizeRequiredCode(destination, "destination");
    }

    private static String normalizeRequiredCode(String code, String fieldName) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be null or empty");
        }
        return code.strip().toUpperCase(Locale.ROOT);
    }
}
