package io.maxbet.Units;

import java.util.Random;

public class PhoneNumberGenerator {
    private static final Random RANDOM = new Random();

    private PhoneNumberGenerator() {
    }

    public static String generate() {

        StringBuilder phone = new StringBuilder("07");

        for (int i = 0; i < 8; i++) {
            phone.append(RANDOM.nextInt(10));
        }

        return phone.toString();
    }
}
