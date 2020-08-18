package br.com.acolita.neatenum;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NeatEnumByTest {

    @Test
    void getEnumBy() {
        final Map<String, SampleState> sampleStateByStateName = NeatEnumBy.getEnumBy(SampleState.values(), SampleState::getStateName);
        for(SampleState sampleState : SampleState.values()) {
            assertTrue(sampleStateByStateName.containsKey(sampleState.getStateName()), "Should contain all states");
        }
    }

    @Test
    void testGetEnumBy() {
        final Map<String, SampleState> sampleStateByStateNameLowercase = NeatEnumBy.getEnumBy(SampleState.values(), SampleState::getStateName, String::toLowerCase);
        for(SampleState sampleState : SampleState.values()) {
            assertTrue(sampleStateByStateNameLowercase.containsKey(sampleState.getStateName().toLowerCase()), "Should contain all states");
        }
    }
}