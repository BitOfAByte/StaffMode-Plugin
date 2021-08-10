package me.tobyhere123.staffmode.SQL;

import me.tobyhere123.staffmode.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {
    PreparedStatement ps;

    public void createTable() {
        try {
            ps = Main.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS players(NAME VARCHAR(100), UUID VARCHAR(100), IP VARCHAR(100), STAFFMODE BOOLEAN, PRIMARY KEY(NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(UUID uuid, Player player) {
        try {
            if (!playerExist(uuid)) {
                ps = Main.SQL.getConnection().prepareStatement("INSERT INTO players (NAME,UUID,IP,STAFFMODE) VALUES (?,?,?,?)");
                ps.setString(1, player.getName());
                ps.setString(2, uuid.toString());
                ps.setString(3, player.getAddress().getHostName());
                ps.setInt(4, 0);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playerExist(UUID uuid) {
        try {
            ps = Main.SQL.getConnection().prepareStatement("SELECT * FROM players WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean isWhitelistGetter(UUID uuid) {
        boolean b = false;
        try {
            ps = Main.SQL.getConnection().prepareStatement("SELECT * FROM players WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                b = results.getBoolean("STAFFMODE");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public void isWhitelistSetter(UUID uuid, boolean b) {
        try {
            ps = Main.SQL.getConnection().prepareStatement("UPDATE players SET STAFFMODE=? WHERE UUID=?");
            ps.setBoolean(1, b);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
