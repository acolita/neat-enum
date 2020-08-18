package br.com.acolita.neatenum;

import java.util.Map;
import java.util.Optional;

public enum SampleStep {
    UNDEFINED("Undefined", -0x1),
    ONE("Init", 0x12),
    TWO("Loop Phase", 0x06),
    THREE("Epilogue", 0x02),
    FINISH("Done", 0x00);

    private final String stepName;
    private final int stepCode;

    private static final Map<Integer, SampleStep> SAMPLE_STEP_BY_CODE = NeatEnumBy.getEnumBy(values(), SampleStep::getStepCode);
    private static final Map<String, SampleStep> SAMPLE_STEP_BY_NAME = NeatEnumBy.getEnumBy(values(), SampleStep::getStepName, String::toLowerCase);

    SampleStep(String stepName, int stepCode) {
        this.stepName = stepName;
        this.stepCode = stepCode;
    }

    public SampleStep getByCode(final int code) {
        return Optional.ofNullable(SAMPLE_STEP_BY_CODE.getOrDefault(code, null)).orElseThrow(IllegalArgumentException::new);
    }

    public SampleStep getByStepName(final String name) {
        return SAMPLE_STEP_BY_NAME.getOrDefault(name.trim().toLowerCase(), UNDEFINED);
    }

    public int getStepCode() {
        return stepCode;
    }

    public String getStepName() {
        return stepName;
    }
}
