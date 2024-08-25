package com.v1.authuser.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Contains utility methods related to passwords.
 *
 * @author Vansh Pratap Singh
 */
public class PasswordUtils {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Hash the given raw password.
     *
     * @param password          Raw password.
     * @return                  Encoded password.
     */
    public static String hashPassword(
            String password
    ) {

        return encoder.encode(password);

    }

    /**
     * Matches the given raw password with the encoded password.
     *
     * @param encodedPassword               Encoded password.
     * @param rawPassword                   Raw password.
     * @return                              Boolean.
     */
    public static boolean matchPassword(
            String encodedPassword,
            String rawPassword
    ) {

        return encoder.matches(rawPassword, encodedPassword);

    }

}
