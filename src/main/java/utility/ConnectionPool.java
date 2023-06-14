package utility;

import utility.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

    private static ArrayBlockingQueue<Connection> pool;
    private ArrayBlockingQueue<Connection> inUse;
    private static ConnectionPool connectionPool;
    private static ConnectionPool instance = null;
    private static int connections = 5;
    private static String url = Config.getUrl();
    private static String user = Config.getUser();
    private static String pass = Config.getPass();

    private ConnectionPool(){

    }

    public static ConnectionPool getInstance(){
        if(connectionPool == null){
            connectionPool = new ConnectionPool();
        }
        return  connectionPool;
    }

    public synchronized Connection getConnect(){
        Connection connect = null;
        if(pool == null){
            pool = new ArrayBlockingQueue<>(connections);
        }
        if(!(pool.size() == 0)){
            try{connect = pool.take();}
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            inUse.add(connect);
        } else if (inUse == null || inUse.size()<connections) {
            if(inUse == null){
                inUse = new ArrayBlockingQueue<>(connections);
            }
            connect = createConnect();
            inUse.add(connect);
        }
        return connect;
    }

    public void release(Connection connect){
        pool.add(connect);
        inUse.remove(connect);
    }

    private static Connection createConnect(){
        try{ return DriverManager.getConnection(url, user, pass);}
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
