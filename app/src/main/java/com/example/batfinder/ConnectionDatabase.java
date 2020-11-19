package com.example.batfinder;

import android.os.StrictMode;
import com.mysql.cj.conf.ConnectionUrl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    String IP, DB, user, password;

    public Connection connection() {
        IP ="3306";
        DB = "batfinder";
        user ="root";
        password = "WalkerSchoolPSU";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection connection = null;
        String connectionURL = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connectionURL = "jdbc:mysql:// localhost" + IP + "/" + DB + "," + user + "," + password;
            connection = DriverManager.getConnection(connectionURL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
