package io.maxbet.listeners;

/**
 * The names of the TestNG groups the suite is split into, kept as constants so a group is named the
 * same everywhere and a typo in one place cannot quietly drop a test out of a run.
 * <p>
 * {@link #SMOKE} is the small set of critical happy paths - one per area - that gates a commit;
 * {@link #REGRESSION} is the whole suite. Every test carries {@code REGRESSION} without naming it -
 * {@link TestSetupTransformer} adds it to each test - so a smoke run is a subset of a regression run
 * and a new test is part of the regression run the moment it is written.
 */
public final class TestGroups {
    public static final String SMOKE = "smoke";
    public static final String REGRESSION = "regression";

    private TestGroups() {
    }
}
