package org.java.learn.lock;

import java.util.List;
import java.util.UUID;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

public class DistributeLock {
    
    private JedisPool pool = null;
    
    public DistributeLock(JedisPool pool){
        this.pool = pool;
    }
    
    public String lock(String lockName, long acquireTimeout, long timeout){
        Jedis conn = null;
        String retId = null;
        
        try{
            conn = pool.getResource();
            
            String id = UUID.randomUUID().toString();
            String lockKey = "lock:"+lockName;
            int lockExpire = (int) (timeout /1000);
            
            long end = System.currentTimeMillis() + acquireTimeout;
            while(System.currentTimeMillis() < end){
                if(conn.setnx(lockKey, id) == 1){
                    conn.expire(lockKey, lockExpire);
                    retId = id;
                    return id;
                }
                if(conn.ttl(lockKey) == -1){
                    conn.expire(lockKey, lockExpire);
                }
                
                try{
                    Thread.sleep(10);
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        
        return retId;
    }
    
    public void unlock(String lockName, String rid){
        Jedis conn = null;
        String lockKey = "lock:" + lockName;
        
        try{
            conn = pool.getResource();
            while(true){
                conn.watch(lockKey);
                if(rid.equals(conn.get(lockKey))){
                    Transaction t = conn.multi();
                    t.del(lockKey);
                    
                    List<Object> results = t.exec();
                    if(null == results){
                        continue;
                    }
                    
                }
                
                conn.unwatch();
                break;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(null != conn){
                conn.close();
            }
        }
        
    }

}
