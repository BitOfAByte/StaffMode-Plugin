package me.tobyhere123.staffmode;

import me.tobyhere123.staffmode.SQL.MySQL;
import me.tobyhere123.staffmode.SQL.SQLGetter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;
    public static MySQL SQL;
    public static SQLGetter data;

    @Override
    public void onEnable() {
         instance = this;
        saveDefaultConfig();

        this.SQL = new MySQL();
        this.data = new SQLGetter();

        if(!SQL.isConnected()) {
            SQL.connect();
        }

        if(SQL.isConnected()) {
            data.createTable();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }
}
