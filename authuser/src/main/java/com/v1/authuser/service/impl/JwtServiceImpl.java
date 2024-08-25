package com.v1.authuser.service.impl;

import com.v1.authuser.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * JWT Service - Contains authentication, validation, etc. methods.
 *
 * @author Vansh Pratap Singh
 */
@Service
public class JwtServiceImpl implements JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

    @Value("${user.jwt.secret.key}")
    private String userJwtSecretKey;

    @Value("${user.jwt.issuer}")
    private String userJwtIssuer;

    /**
     * Generate a jwt token for the given user.
     *
     * @param email             User email.
     * @param username          User name.
     * @return                  Jwt token.
     */
    @Override
    public String createJwtToken(
            String email,
            String username
    ) {

        try {

            return Jwts
                    .builder()
                    .signWith(Keys.hmacShaKeyFor(userJwtSecretKey.getBytes()))
                    .claim("email", email)
                    .claim("username", username)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 900000))
                    .issuer(userJwtIssuer)
                    .compact();

        } catch (Exception ex) {

            logger.error("*** Exception occurred while generating jwt token for " +
                    "email {} and username {} ***\n" +
                    "Exception is => {}", email, username, ExceptionUtils.getStackTrace(ex));

            return null;

        }

    }

    /**
     * Validate the given jwt token.
     *
     * @param jwtToken          Jwt token.
     * @return                  Boolean.
     */
    @Override
    public boolean validateToken(String jwtToken) {

        try {

            Jwts
                    .parser()
                    .verifyWith(Keys.hmacShaKeyFor(userJwtSecretKey.getBytes()))
                    .requireIssuer(userJwtIssuer)
                    .build()
                    .parseSignedClaims(userJwtSecretKey);

            return true;

        } catch (Exception ex) {

            logger.error("*** Exception occurred while validating jwt token {} ***\n" +
                    "Exception is => {}", jwtToken, ExceptionUtils.getStackTrace(ex));

            return false;

        }

    }
}
