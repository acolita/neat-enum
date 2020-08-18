package br.com.acolita.neatenum;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SampleStateTest {

    @Test
    void getByStateName() {
        final long count = Stream.of(SampleState.values()).map(SampleState::getStateName).map(SampleState::getByStateName).filter(Objects::nonNull).count();
        assertEquals(count, SampleState.values().length, "Should be the same size");
    }
}