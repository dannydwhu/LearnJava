package org.java.learn.pattern.structrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {

    private Connection conn;
    
    public ResultSet get(String sql) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.execute();
        
        return ps.getResultSet();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public static void main(String[] args) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        
        Query query = new Query();
        query.setConn(conn);
        
        ResultSet rs = query.get("SELECT * FROM vcity_account.t_c_job limit 1");
        
        int columnCount = rs.getMetaData().getColumnCount();
        for(int i=0; i<columnCount ; i++){
            System.out.print(rs.getMetaData().getColumnName(i+1) + ",");
        }
        System.out.println();
        
        while(rs.next()){
            for(int i=0; i<columnCount ; i++){
                System.out.println(rs.getString(i+1));
            }
           
        }
        System.out.println();
    }
}
