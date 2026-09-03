package io.maxbet.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Re-runs a failed test a fixed number of times before it is reported as a failure. The suite runs a
 * browser per test against a shared, live environment, so a failure is as likely to be a dropped login
 * click or a slow frame as it is a real defect - a single retry keeps a flake from failing the run
 * while a genuine break still fails every attempt and is reported.
 * <p>
 * A fresh instance is created per test method by TestNG, so {@link #count} tracks the attempts of one
 * method alone and does not have to be reset between methods.
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    private static final Logger log = LogManager.getLogger(RetryAnalyzer.class);
    private static final int MAX_RETRIES = 2;
    private int count = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (result.isSuccess() || count >= MAX_RETRIES) {
            return false;
        }
        count++;
        log.warn("Retrying '{}' after failure, attempt {} of {}",
                result.getMethod().getMethodName(), count, MAX_RETRIES);
        return true;
    }
}
