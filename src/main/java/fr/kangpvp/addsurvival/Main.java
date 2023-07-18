package fr.kangpvp.addsurvival;

import fr.kangpvp.addsurvival.commands.home.PlayerHomes;
import fr.kangpvp.addsurvival.database.DbManager;
import fr.kangpvp.addsurvival.utils.Config;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public final class Main extends JavaPlugin {

    private static Main instance;
    private DbManager dbManager;

    public ArrayList<PlayerHomes> listPlayerHomes = new ArrayList<>();


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Config.init();
        dbManager = new DbManager();


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
