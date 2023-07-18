package fr.kangpvp.addsurvival.utils;

import fr.kangpvp.addsurvival.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Config {
    static final Main main = Main.getInstance();

    static final File configFile = new File(main.getDataFolder(), "config.yml");

    static final String categoriesPath = "settings.categories";

    private static void create() {
        main.saveDefaultConfig();
        main.getLogger().info("Configuration file added");
    }

    public static void init() {
        if (!configFile.exists())
            create();
    }

    public static void reload() {
        if (configFile.exists()) {
            main.getLogger().info("Configuration file reloaded");
        } else {
            create();
        }
        main.reloadConfig();
    }

    public static String getString(String path) {
        if (main.getConfig().contains(path))
            return main.getConfig().getString(path);
        main.getLogger().severe("This value doesn't exist in configuration file :" + path);
        return "";
    }

    public static String getColored(String path) {
        if (main.getConfig().contains(path))
            return ChatColor.translateAlternateColorCodes('&', main.getConfig().getString(path));
        main.getLogger().severe("This value doesn't exist in configuration file :" + path);
        return "";
    }

    public static List<String> getColoredList(String path) {
        if (main.getConfig().contains(path)) {
            List<String> messages = main.getConfig().getStringList(path);
            messages.replaceAll(msgToColor -> ChatColor.translateAlternateColorCodes('&', msgToColor));
            return messages;
        }
        main.getLogger().severe("This value doesn't exist in configuration file :" + path);
        return null;
    }

    public static int getInt(String path) {
        if (main.getConfig().contains(path))
            return main.getConfig().getInt(path);
        main.getLogger().severe("This value doesn't exist in configuration file :" + path);
        return 0;
    }

    public static List<String> getList(String path) {
        if (main.getConfig().contains(path))
            return main.getConfig().getStringList(path);
        main.getLogger().severe("This value doesn't exist in configuration file :" + path);
        return null;
    }

    public static List<String> getCategoriesKeys() {
        if (main.getConfig().contains("settings.categories")) {
            ConfigurationSection configurationSection = main.getConfig().getConfigurationSection("settings.categories");
            return new ArrayList<>(configurationSection.getValues(false).keySet());
        }
        main.getLogger().severe("This value doesn't exist in configuration file :settings.categories");
        return null;
    }

    public static List<Object> getCategoriesValues() {
        if (main.getConfig().contains("settings.categories")) {
            ConfigurationSection configurationSection = main.getConfig().getConfigurationSection("settings.categories");
            return new ArrayList(configurationSection.getValues(false).values());
        }
        main.getLogger().severe("This value doesn't exist in configuration file :settings.categories");
        return null;
    }

    public static String get(String path) {
        if (main.getConfig().contains(path))
            return main.getConfig().getString(path);
        main.getLogger().severe("This value doesn't exist in configuration file :" + path);
        return null;
    }
}
