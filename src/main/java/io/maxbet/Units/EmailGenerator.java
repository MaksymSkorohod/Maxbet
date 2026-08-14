package io.maxbet.Units;

import java.util.Random;

public class EmailGenerator {
    private static final Random RANDOM = new Random();

    private EmailGenerator() {
    }

    public static String generate() {

        int randomNumber = 100000 + RANDOM.nextInt(900000);

        return "testuser" + randomNumber + "@mail.com";
    }
}
