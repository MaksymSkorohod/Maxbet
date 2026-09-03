package io.maxbet.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Attaches {@link RetryAnalyzer} to every test method of the suite, so no {@code @Test} has to name the
 * analyzer itself and no new test can be added without the retry in place. TestNG runs the transformer
 * for each test as it is read, and only for tests that do not already declare a retry analyzer of their
 * own are left untouched.
 */
public class RetryTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        Class<?> declared = annotation.getRetryAnalyzerClass();
        if (declared == null || declared.getName().endsWith("DisabledRetryAnalyzer")) {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
    }
}
