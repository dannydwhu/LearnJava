package org.java.learn.pattern.structrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool {
                          
    private String url = "jdbc:mysql://192.168.31.246:3306/vcity_account";
    private String user = "pxsjuser";
    private String pwd  = "pxsj_123";
    private String driver = "com.mysql.jdbc.Driver";
    
    private int poolSize = 10;
    
    private static ConnectionPool instance = new ConnectionPool();
    
    private Vector<Connection> pool = null;
    
    Connection conn = null;
    
    public static ConnectionPool getInstance(){
        return instance;
    }
    
    private ConnectionPool(){
        pool = new Vector<>(poolSize);
        
        for(int i=0; i<poolSize; i++){
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, pwd);
                pool.add(conn);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public synchronized void backConnection(Connection conn){
        this.pool.add(conn);
    }
    
    public synchronized Connection getConnection(){
        Connection conn = null;
        if(pool.size() > 0){
            conn = pool.get(0);
            pool.remove(conn);
        }
        return conn;
    }
    
}
