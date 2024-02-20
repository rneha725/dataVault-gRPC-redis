package org.projects.john.crickett._48.service;

import redis.clients.jedis.JedisPooled;

public class Jedis {
    private static JedisPooled jedis;
    private Jedis() {}
    public static JedisPooled getInstance() {
        if(jedis == null) {
            jedis = new JedisPooled(); //connects to the localhost
        }
        return jedis;
    }
}
