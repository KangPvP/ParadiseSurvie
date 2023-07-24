package fr.kangpvp.addsurvival;

import fr.kangpvp.addsurvival.commands.aventages.CommandFurnace;
import fr.kangpvp.addsurvival.commands.home.*;
import fr.kangpvp.addsurvival.database.DbManager;
import fr.kangpvp.addsurvival.listeners.GuiListener;
import fr.kangpvp.addsurvival.listeners.JoinListener;
import fr.kangpvp.addsurvival.manager.RegionManage;
import fr.kangpvp.addsurvival.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public String prefix = "§9§lP§2§lSurvie §f| §r";
    private static Main instance;
    private static HomeUtils homeUtils;
    private DbManager dbManager;

    private RegionManage regionManage;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Config.init();
        dbManager = new DbManager();
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);

        homeUtils = new HomeUtils();
        regionManage = new RegionManage();


        Bukkit.getPluginCommand("delhome").setExecutor(new CommandDelhome());
        Bukkit.getPluginCommand("home").setExecutor(new CommandHome());
        Bukkit.getPluginCommand("sethome").setExecutor(new CommandSethome());
        Bukkit.getPluginCommand("homes").setExecutor(new CommandHomes());
        Bukkit.getPluginCommand("furnace").setExecutor(new CommandFurnace());



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static Main getInstance(){
        return instance;
    }

    public HomeUtils getHomeUtils(){
        return homeUtils;
    }
    public RegionManage getRegionManage() {
        return regionManage;
    }
    public DbManager getDbManager() {
        return dbManager;
    }

}
