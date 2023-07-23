package fr.kangpvp.addsurvival;

import de.themoep.inventorygui.InventoryGui;
import fr.kangpvp.addsurvival.commands.home.CommandDelhome;
import fr.kangpvp.addsurvival.commands.home.CommandHome;
import fr.kangpvp.addsurvival.commands.home.CommandSethome;
import fr.kangpvp.addsurvival.commands.home.HomeUtils;
import fr.kangpvp.addsurvival.database.DbManager;
import fr.kangpvp.addsurvival.listeners.JoinListener;
import fr.kangpvp.addsurvival.menu.HomesGui;
import fr.kangpvp.addsurvival.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public String prefix = "§9§lP§2§lSurvie §f| §r";
    private static Main instance;
    private static HomeUtils homeUtils;
    private DbManager dbManager;

    public InventoryHolder homesGui;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Config.init();
        dbManager = new DbManager();
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        homeUtils = new HomeUtils();
        homesGui = new HomesGui();

        String[] guiSetup = {
                "  s i z  ",
                "  ggggg  ",
                "  fpdnl  "
        };
        InventoryGui gui = new InventoryGui(Main.getInstance(), "guiTitle", guiSetup);
        gui.build();
        gui.setFiller(new ItemStack(Material.GRAY_STAINED_GLASS, 1)); // fill the empty slots with this
        //System.out.println(gui.);
        Bukkit.getPluginCommand("delhome").setExecutor(new CommandDelhome());
        Bukkit.getPluginCommand("home").setExecutor(new CommandHome());
        Bukkit.getPluginCommand("sethome").setExecutor(new CommandSethome());


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
    public DbManager getDbManager() {
        return dbManager;
    }

}
