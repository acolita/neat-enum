package br.com.acolita.neatenum;

public enum SampleStep {
    UNDEFINED("Undefined", -0x1),
    ONE("Init", 0x12),
    TWO("Loop Phase", 0x06),
    THREE("Epilogue", 0x02),
    FINISH("Done", 0x00);

    private final String stepName;
    private final int stepCode;

    private static final NeatEnumGetter<SampleStep, Integer> GET_BY_CODE = new NeatEnumGetter<>(SampleStep.class, SampleStep::getStepCode);
    private static final NeatEnumGetter<SampleStep, String> STEP_BY_NAME = new NeatEnumGetter<>(SampleStep.class, NeatCompose.compose(SampleStep::getStepName, String::toLowerCase));

    SampleStep(String stepName, int stepCode) {
        this.stepName = stepName;
        this.stepCode = stepCode;
    }

    public SampleStep getByCode(final int code) {
        return GET_BY_CODE.orThrow(code, IllegalArgumentException::new);
    }

    public SampleStep getByStepName(final String name) {
        return STEP_BY_NAME.orElse(name.trim().toLowerCase(), UNDEFINED);
    }

    public int getStepCode() {
        return stepCode;
    }

    public String getStepName() {
        return stepName;
    }
}
