package com.english.project;

import com.english.project.service.Impl.RedisClientDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServerApplicationTests {

    @Autowired
    private RedisClientDetailsServiceImpl  redisClientDetailsService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Test
    public void contextLoads() {
        this.redisClientDetailsService.updateClientSecret("system",passwordEncoder.encode("app"));
    }

}
