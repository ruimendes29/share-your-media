package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public final class Passwords {
    private static final Random RANDOM = new SecureRandom();

    private Passwords() {
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static String getSalt(final int length) {
        StringBuilder salt = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            salt.append((char) (RANDOM.nextInt(26) + 'a'));
        }
        return new String(salt);
    }

    public static String generate(final String password, final String salt) {
        try {
            byte[] hash = MessageDigest.getInstance("SHA-512").digest((password + salt).getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verify(final String input, final String password, final String salt) {
        return Passwords.generate(input, salt).equalsIgnoreCase(password);
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private static int score(final String input) {
        int score = 0;

        if (input.length() > 8) {
            score += 5;

            if (input.matches("(?=.*[0-9]).*"))
                score += 5;

            if ((input.matches("(?=.*[a-z]).*")) && (input.matches("(?=.*[A-Z]).*")))
                score += 5;

            if (input.matches("(?=.*[~!@#$%^&*()_-]).*"))
                score += 1;
        }

        return score;
    }
}
