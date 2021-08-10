package me.tobyhere123.staffmode.SQL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static me.tobyhere123.staffmode.Main.getInstance;

public class MySQL {

    private String host = getInstance().getConfig().getString("MySQL.host");
    private String username = getInstance().getConfig().getString("MySQL.username");
    private String password = getInstance().getConfig().getString("MySQL.password");
    private String database = getInstance().getConfig().getString("MySQL.database");
    private int port = getInstance().getConfig().getInt("MySQL.port");

    private Connection connection;


    public boolean isConnected() {
        return (connection != null);
    };

    public void connect() {
        try {
            if(!isConnected()) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "CONNECTING TO MYSQL DATABASE PLEASE WAIT....");
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", username, password);
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§cFAILED TO CONNECT TO MySQL DATABASE");
        }
    }

    public void disconnect() {
        if(isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
