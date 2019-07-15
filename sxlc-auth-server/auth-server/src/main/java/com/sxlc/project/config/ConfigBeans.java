package com.sxlc.project.config;

import com.sxlc.project.uidGenerator.impl.CachedUidGenerator;
import com.sxlc.project.uidGenerator.impl.DefaultUidGenerator;
import com.sxlc.project.uidGenerator.worker.DisposableWorkerIdAssigner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ZhuQing
 * @Date: 2019/4/14  22:02
 */
@Configuration
public class ConfigBeans {

    @Bean
    public DefaultUidGenerator defaultUidGenerator() {
        DefaultUidGenerator defaultUidGenerator = new DefaultUidGenerator();
        defaultUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner());
        defaultUidGenerator.setTimeBits(29);
        defaultUidGenerator.setWorkerBits(21);
        defaultUidGenerator.setSeqBits(13);
        defaultUidGenerator.setEpochStr("2019-04-14");
        return defaultUidGenerator;
    }

    @Bean
    public CachedUidGenerator cachedUidGenerator() {
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner());
        cachedUidGenerator.setTimeBits(29);
        cachedUidGenerator.setWorkerBits(21);
        cachedUidGenerator.setSeqBits(13);
        cachedUidGenerator.setEpochStr("2019-04-14");
        cachedUidGenerator.setBoostPower(3);
        return cachedUidGenerator;

    }


    @Bean
    public DisposableWorkerIdAssigner disposableWorkerIdAssigner() {
        return new DisposableWorkerIdAssigner();
    }
}
