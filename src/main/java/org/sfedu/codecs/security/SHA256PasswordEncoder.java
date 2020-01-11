package org.sfedu.codecs.security;

import org.sfedu.codecs.utils.PasswordUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SHA256PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        try {
            return PasswordUtils.getHash(charSequence.toString());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        try {
            return s.equals(PasswordUtils.getHash(charSequence.toString()));
        } catch (Exception e) {
            return false;
        }
    }
}
