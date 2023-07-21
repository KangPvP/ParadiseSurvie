package fr.kangpvp.addsurvival;

import fr.kangpvp.addsurvival.commands.home.HomeUtils;
import fr.kangpvp.addsurvival.commands.home.PlayerHomes;
import fr.kangpvp.addsurvival.database.DbManager;
import fr.kangpvp.addsurvival.listeners.JoinListener;
import fr.kangpvp.addsurvival.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    public String prefix = "§9§lP§2§lSurvie §f| §r";
    private static Main instance;
    private DbManager dbManager;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Config.init();
        dbManager = new DbManager();
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static Main getInstance(){
        return instance;
    }
    public DbManager getDbManager() {
        return dbManager;
    }

}
