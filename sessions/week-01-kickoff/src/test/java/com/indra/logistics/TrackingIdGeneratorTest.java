package com.indra.logistics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrackingIdGeneratorTest {

    private static final String EXPECTED_ID_PATTERN = "[A-Z0-9]+-[A-Z0-9]+-[A-Z0-9]{8}";

    private final TrackingIdGenerator generator = new TrackingIdGenerator();

    @Test
    @DisplayName("El ID generado debe tener el formato ORIG-DEST-XXXXXXXX en mayúsculas")
    void shouldGenerateIdWithCorrectFormat() {
        String trackingId = generator.generate("bog", "med");

        assertTrue(trackingId.matches("BOG-MED-[A-Z0-9]{8}"),
                () -> "El ID no cumple el formato esperado: " + trackingId);
    }

    @ParameterizedTest
    @CsvSource({
            "bog, med",
            "MDE, BOG",
            "  cali  , cartagena",
            "bcn, mad"
    })
    @DisplayName("El formato del ID es válido para múltiples combinaciones origen/destino")
    void shouldGenerateIdWithValidFormatForSeveralRoutes(String origin, String destination) {
        String trackingId = generator.generate(origin, destination);

        assertTrue(trackingId.matches(EXPECTED_ID_PATTERN),
                () -> "El ID no cumple el formato esperado: " + trackingId);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "NULL, med",
            "bog, NULL",
            "'', med",
            "bog, ''",
            "'   ', med"
    }, nullValues = "NULL")
    @DisplayName("Debe lanzar IllegalArgumentException si origen o destino es nulo o vacío")
    void shouldThrowWhenParametersAreInvalid(String origin, String destination) {
        assertThrows(IllegalArgumentException.class,
                () -> generator.generate(origin, destination));
    }
}
