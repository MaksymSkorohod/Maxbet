package io.maxbet.Units;

public class UsernameGenerator {

    private UsernameGenerator() {
    }

    public static String generate() {
        return "TestAutoUser" + System.currentTimeMillis();
    }
}

