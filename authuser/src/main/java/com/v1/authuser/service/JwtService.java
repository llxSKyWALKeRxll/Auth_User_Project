package com.v1.authuser.service;

/**
 * JWT Service - Contains authentication, validation, etc. methods.
 *
 * @author Vansh Pratap Singh
 */
public interface JwtService {

    /**
     * Generate a jwt token for the given user.
     *
     * @param email             User email.
     * @param username          User name.
     * @return                  Jwt token.
     */
    String createJwtToken(
            String email,
            String username
    );

    /**
     * Validate the given jwt token.
     *
     * @param jwtToken          Jwt token.
     * @return                  Boolean.
     */
    boolean validateToken(
            String jwtToken
    );

}
