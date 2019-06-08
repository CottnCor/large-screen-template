package com.largeScreen.api.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.regex.Pattern;

public class CustomEncoder implements PasswordEncoder {

    private Pattern BCRYPT_PATTERN;
    private final Log logger;
    private final int strength;
    private final SecureRandom random;

    public CustomEncoder() {
        this(-1);
    }

    public CustomEncoder(int strength) {
        this(strength, (SecureRandom) null);
    }

    public CustomEncoder(int strength, SecureRandom random) {
        this.BCRYPT_PATTERN = Pattern.compile(".*");
        this.logger = LogFactory.getLog(this.getClass());
        if (strength == -1 || strength >= 4 && strength <= 31) {
            this.strength = strength;
            this.random = random;
        } else {
            throw new IllegalArgumentException("Bad strength");
        }
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword != null && encodedPassword.length() != 0) {
            if (!this.BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
                this.logger.warn("Encoded password does not look like MD5");
                return false;
            } else {
                return rawPassword.toString().equals(encodedPassword.toString());
            }
        } else {
            this.logger.warn("Empty encoded password");
            return false;
        }
    }
}
