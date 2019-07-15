package com.sxlc.project.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: ZhuQing
 * @Date: 2019/2/1 14:51
 */
@Service
public class RedisAuthorizationCodeServiceImpl extends RandomValueAuthorizationCodeServices {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        redisTemplate.execute(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(codeKey(code).getBytes(),
                        SerializationUtils.serialize(authentication),
                        Expiration.from(10, TimeUnit.MINUTES), RedisStringCommands.SetOption.UPSERT);
                return 1L;
            }
        });
    }

    @Override
    protected OAuth2Authentication remove(final String code) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) redisTemplate
                .execute(new RedisCallback<OAuth2Authentication>() {

                    @Override
                    public OAuth2Authentication doInRedis(RedisConnection connection) throws DataAccessException {
                        byte[] keyByte = codeKey(code).getBytes();
                        byte[] valueByte = connection.get(keyByte);

                        if (valueByte != null) {
                            connection.del(keyByte);
                            return SerializationUtils.deserialize(valueByte);
                        }

                        return null;
                    }
                });

        return oAuth2Authentication;
    }

    /**
     * 拼装redis中key的前缀
     *
     * @param code
     */
    private String codeKey(String code) {
        return "oauth2:codes:" + code;
    }
}
