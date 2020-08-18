package br.com.acolita.neatenum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NeatEnumGetterTest {

    private final int INVALID_KEY = 0x1000;

    @Test
    void orElse() {
        final NeatEnumGetter<SampleStep, Integer> getter = getGetter();
        final SampleStep shouldBeFINISH = getter.orElse(SampleStep.FINISH.getStepCode(), SampleStep.UNDEFINED);
        assertEquals(SampleStep.FINISH, shouldBeFINISH, "Should return FINISH");
        final SampleStep shouldBeUNDEFINED = getter.orElse(INVALID_KEY, SampleStep.UNDEFINED);
        assertEquals(SampleStep.UNDEFINED, shouldBeUNDEFINED, "Should return UNDEFINED");
    }

    private NeatEnumGetter<SampleStep, Integer> getGetter() {
        return new NeatEnumGetter<>(SampleStep.class, SampleStep::getStepCode);
    }

    @Test
    void orNull() {
        final NeatEnumGetter<SampleStep, Integer> getter = getGetter();
        final SampleStep expectNull = getter.orNull(INVALID_KEY);
        assertNull(expectNull, "Invalid key should be null");
    }

    @Test
    void orThrow() {
        final NeatEnumGetter<SampleStep, Integer> getter = getGetter();
        assertThrows(IllegalArgumentException.class, () -> getter.orThrow(INVALID_KEY, IllegalArgumentException::new), "Invalid key should throw exception");
        assertDoesNotThrow(() -> getter.orThrow(SampleStep.FINISH.getStepCode(), IllegalArgumentException::new), "Valid key should not throw exception");
    }
}