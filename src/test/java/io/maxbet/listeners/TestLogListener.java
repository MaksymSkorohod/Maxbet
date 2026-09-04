package io.maxbet.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Prints one line per test as it starts and finishes, so a plain {@code mvn test} run shows which test
 * is running, its outcome and how long it took - Surefire on its own only logs at the class level and
 * the app logger only shows the framework's own messages. Registered as a TestNG listener next to the
 * Allure listener in the Surefire configuration.
 */
public class TestLogListener implements ITestListener {
    private static final Logger log = LogManager.getLogger(TestLogListener.class);

    private static String name(ITestResult result) {
        return result.getTestClass().getRealClass().getSimpleName() + "." + result.getName();
    }

    private static double seconds(ITestResult result) {
        return (result.getEndMillis() - result.getStartMillis()) / 1000.0;
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("STARTED  {}", name(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("PASSED   {} ({}s)", name(result), seconds(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable cause = result.getThrowable();
        log.error("FAILED   {} ({}s): {}", name(result), seconds(result),
                cause == null ? "" : cause.toString());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Throwable cause = result.getThrowable();
        log.warn("SKIPPED  {}: {}", name(result), cause == null ? "" : cause.getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("SUITE '{}' done: {} passed, {} failed, {} skipped",
                context.getName(),
                context.getPassedTests().size(),
                context.getFailedTests().size(),
                context.getSkippedTests().size());
    }
}
