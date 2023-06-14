package utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Config {

    private static String url;
    private static String user;
    private static String pass;
    private static final Properties prop;
    static{
        prop = new Properties();
        try(FileReader fr = new FileReader(".\\src\\main\\resources\\database.properties")){
            prop.load(fr);
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            pass = prop.getProperty("password");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getUrl(){
        return url;
    }
    public static String getUser(){
        return user;
    }
    public static String getPass(){
        return pass;
    }
}
