package com.english.project.config.oauth2;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;

import java.util.UUID;

/**
 * @Author: ZhuQing
 * @Date: 2019/2/1 14:57
 */
public class RandomAuthenticationKeyGenerator implements AuthenticationKeyGenerator {
    @Override
    public String extractKey(OAuth2Authentication authentication) {
        return UUID.randomUUID().toString();
    }
}
