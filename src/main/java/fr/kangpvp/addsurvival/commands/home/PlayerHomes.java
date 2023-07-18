package fr.kangpvp.addsurvival.commands.home;

import fr.kangpvp.addsurvival.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerHomes {

    private String uuid;
    private String playerName;
    private ArrayList<String> homeList;
    private HashMap<String, String> homeLoc;

    public PlayerHomes(String uuid, String playerName, ArrayList<String> homeList, HashMap<String, String> homeLoc) {
        this.uuid = uuid;
        this.playerName = playerName;
        this.homeList = homeList;
        this.homeLoc = homeLoc;


    }

    public String getUuid() {
        return uuid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<String> getHomeList() {
        return homeList;
    }

    public HashMap<String, String> getHomeLoc() {
        return homeLoc;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setHomeList(ArrayList<String> homeList) {
        this.homeList = homeList;
    }

    public void setHomeLoc(HashMap<String, String> homeLoc) {
        this.homeLoc = homeLoc;
    }

    public static PlayerHomes getPlayerHomesFromUUID(UUID uuid) {
        System.out.println(Main.getInstance().listPlayerHomes);

        if(Main.getInstance().listPlayerHomes.size() == 0){ System.out.println(ChatColor.RED + "[ERREUR] La list listPlayerHomes est vide"); return null;}

        for (PlayerHomes playerHome : Main.getInstance().listPlayerHomes) {
            if (playerHome.getUuid().equals(uuid.toString())) {
                return playerHome;
            }
        }
        return null;
    }

    public static PlayerHomes playerDataHomes(Player player, ArrayList<String> homeList, HashMap<String, String> homeLoc){

        return new PlayerHomes(player.getUniqueId().toString(), player.getName(), homeList, homeLoc);
    }

}
