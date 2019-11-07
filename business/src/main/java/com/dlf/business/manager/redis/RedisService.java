package com.dlf.business.manager.redis;

import java.util.Set;

public interface RedisService {
    /**
     * put
     * @param key
     * @param value
     */
    void put(String key, String value);
    void put(String key, Object value);

    /**
     * put timeout
     * @param key
     * @param value
     * @param timeoutSeconds
     */
    void put(String key, String value, Long timeoutSeconds);
    void put(String key, Object value, Long timeoutSeconds);

    /**
     * StringRedisTemplate
     * @param key
     * @return
     */
    String get(String key);
    Object getObj(String key);

    void removeKey(String key);

    Set<String> getKeysByPrefix(String prefix);

    void delKey(String key);

    Long incr(String key);



}
