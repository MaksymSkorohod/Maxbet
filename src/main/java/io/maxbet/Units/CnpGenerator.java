package io.maxbet.Units;

import java.time.LocalDate;
import java.util.Random;

public class CnpGenerator {
    private static final Random RANDOM = new Random();
    private static final String CONTROL_KEY = "279146358279";

    public static String generate() {

        int gender = RANDOM.nextBoolean() ? 1 : 2;

        int year = 80 + RANDOM.nextInt(20);   //1980-1999
        int month = 1 + RANDOM.nextInt(12);

        int maxDay = LocalDate.of(1900 + year, month, 1).lengthOfMonth();
        int day = 1 + RANDOM.nextInt(maxDay);

        int county = 1 + RANDOM.nextInt(52);

        int serial = 1 + RANDOM.nextInt(999);

        String first12 = String.format(
                "%d%02d%02d%02d%02d%03d",
                gender,
                year,
                month,
                day,
                county,
                serial
        );
        return first12 + calculateControlDigit(first12);
    }
    private static int calculateControlDigit(String cnp) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += Character.getNumericValue(cnp.charAt(i))
                    * Character.getNumericValue(CONTROL_KEY.charAt(i));
        }
        int control = sum % 11;
        return control == 10 ? 1 : control;
    }
}
