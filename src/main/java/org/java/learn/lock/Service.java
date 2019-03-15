package org.java.learn.lock;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Service {

    private static JedisPool pool = null;
    
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(200);
        config.setMaxIdle(8);
        config.setMaxWaitMillis(1000*100);
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "192.168.31.249", 6379, 3000);
    }
    
    int n = 500;
    private DistributeLock lock = new DistributeLock(pool);
    
    public void secKill(){
//        String rid = lock.lock("danny", 5000, 2000);
        System.out.println(Thread.currentThread().getName() + "  get lock " +  --n);
//        lock.unlock("danny", rid);
    }
    
    
}
