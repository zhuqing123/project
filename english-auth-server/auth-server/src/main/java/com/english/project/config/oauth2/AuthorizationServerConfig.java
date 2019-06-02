package com.english.project.config.oauth2;


import com.english.project.service.Impl.RedisAuthorizationCodeServiceImpl;
import com.english.project.service.Impl.RedisClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.HashMap;
import java.util.Map;

/**
 * 授权服务器配置
 *
 * @Author: ZhuQing
 * @Date: 2019/2/1 14:35
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 认证管理器
     *
     * @see SecurityConfig 的authenticationManagerBean()
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private RedisAuthorizationCodeServiceImpl redisAuthorizationCodeServices;
    @Autowired
    private RedisClientDetailsServiceImpl redisClientDetailsService;
    /**
     * 令牌存储
     */
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setAuthenticationKeyGenerator(new RandomAuthenticationKeyGenerator());
        return redisTokenStore;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
        endpoints.tokenStore(tokenStore());
        // 授权码模式下，code存储
        //		endpoints.authorizationCodeServices(new JdbcAuthorizationCodeServices(dataSource));
        endpoints.authorizationCodeServices(redisAuthorizationCodeServices)
                .tokenEnhancer((accessToken, authentication) -> {
                    addLoginUserInfo(accessToken, authentication);
                    return accessToken;
                });
    }

    /**
     * 将当前用户信息追加到登陆后返回的json数据里
     *
     * @param accessToken
     * @param authentication
     */
    private void addLoginUserInfo(OAuth2AccessToken accessToken,
                                  OAuth2Authentication authentication) {

        if (accessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
            Authentication userAuthentication = authentication.getUserAuthentication();
            Object principal = userAuthentication.getPrincipal();
            if (principal instanceof User) {
                User loginUser = (User) principal;
                Map<String, Object> map = new HashMap<>(
                        defaultOAuth2AccessToken.getAdditionalInformation()); // 旧的附加参数
                //                loginUser.setRestaurantInfo(null);
                map.put("userInfo", loginUser); // 追加当前登陆用户
                defaultOAuth2AccessToken.setAdditionalInformation(map);
            }
        }
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients(); // 允许表单形式的认证
    }


    /**
     * 我们将client信息存储到oauth_client_details表里<br>
     * 并将数据缓存到redis
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //		clients.inMemory().withClient("system").secret(bCryptPasswordEncoder.encode("system"))
        //				.authorizedGrantTypes("password", "authorization_code", "refresh_token").scopes("app")
        //				.accessTokenValiditySeconds(3600);

        //		clients.jdbc(dataSource);
        clients.withClientDetails(redisClientDetailsService);
        redisClientDetailsService.loadAllClientToCache();
    }
}
