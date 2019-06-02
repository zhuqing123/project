package com.english.project.utils;

import com.english.project.uidGenerator.impl.CachedUidGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * id生成器
 * @Author ZhuQing
 * @Date: 2019/4/14  18:38
 */
public class SnowflakeId implements IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        CachedUidGenerator cachedUidGenerator = SpringContextUtil.getBean(CachedUidGenerator.class);
        return cachedUidGenerator.getUID();
    }
}
