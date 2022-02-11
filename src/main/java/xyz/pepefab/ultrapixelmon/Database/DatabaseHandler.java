package xyz.pepefab.ultrapixelmon.Database;

import xyz.pepefab.ultrapixelmon.Main;

import java.sql.*;

public class DatabaseHandler {

    public DatabaseHandler (String dBpath){

    }

    private static Connection connection = null;
    private static Statement statement = null;

    public static void connect(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + Main.dblocation);
            statement = connection.createStatement();
            Main.log.info("Database connection sucess");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            Main.log.error("Database connection error");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            Main.log.error("Database connection error");
        }
    }

    public static void init(){
        query("CREATE TABLE IF NOT EXISTS Legendary"
                + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Player varchar (40),"
                + "Pokemon varchar (255),"
                + "HeureSpawnPokemon varchar (255),"
                + "Talent varchar (40),"
                + "IVS int,"
                + "Nature varchar (40)"
                + ");");
    }

    public void close(){
        try{
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void query (String request){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(request);
            statement.closeOnCompletion();
        } catch (SQLException e) {
            e.printStackTrace();
            Main.log.error("Request error : " + request);
        }
    }

    public static ResultSet queryWithResult(String requet) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requet);
            statement.closeOnCompletion();
            return resultat;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Request error : " + requet);
        }
        return null;

    }

    public static void threadQuery(String request){
        Thread sqlThread = new Thread(() -> {
            query(request);
        });
        sqlThread.start();
    }

}
