package io.maxbet.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Sets up every test of the suite as it is read, before the tests are run, so no {@code @Test} has to
 * carry the setup itself and no new test can be added without it. TestNG runs the transformer once per
 * test, and it does two things to each:
 * <ul>
 *   <li>attaches {@link RetryAnalyzer}, unless the test already names a retry analyzer of its own, so
 *       a flake is retried rather than failing the run;</li>
 *   <li>adds the {@link TestGroups#REGRESSION} group, so the whole suite is the regression run and a
 *       {@code -Dgroups=regression} run picks up every test without each one naming the group. A test
 *       that names {@link TestGroups#SMOKE} keeps it and is handed regression on top, which is what
 *       makes a smoke run a subset of a regression run.</li>
 * </ul>
 */
public class TestSetupTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        Class<?> declared = annotation.getRetryAnalyzerClass();
        if (declared == null || declared.getName().endsWith("DisabledRetryAnalyzer")) {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
        }

        Set<String> groups = new LinkedHashSet<>();
        if (annotation.getGroups() != null) {
            for (String group : annotation.getGroups()) {
                groups.add(group);
            }
        }
        if (groups.add(TestGroups.REGRESSION)) {
            annotation.setGroups(groups.toArray(new String[0]));
        }
    }
}
