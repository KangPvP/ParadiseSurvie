package fr.kangpvp.addsurvival.commands.home;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerHomes {

    public static HashMap<UUID, PlayerHomes> mapPlayerHomes = new HashMap<>();
    private String uuid;
    private String playerName;
    private ArrayList<String> homeList;
    private HashMap<String, Location> homeLoc;

    public PlayerHomes(String uuid, String playerName, ArrayList<String> homeList, HashMap<String, Location> homeLoc) {
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

    public HashMap<String, Location> getHomeLoc() {
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

    public void setHomeLoc(HashMap<String, Location> homeLoc) {
        this.homeLoc = homeLoc;
    }

    public void savePlayerHome(Player player){
        mapPlayerHomes.put(player.getUniqueId(), this);
    }

    public static PlayerHomes getPlayerHomesFromUUID(UUID uuid) {

        if(mapPlayerHomes.size() == 0){ System.out.println(ChatColor.RED + "[ERREUR] La list mapPlayerHomes est vide"); return null;}

        return mapPlayerHomes.get(uuid);
    }

    public static PlayerHomes playerDataHomes(Player player, ArrayList<String> homeList, HashMap<String, Location> homeLoc){

        return new PlayerHomes(player.getUniqueId().toString(), player.getName(), homeList, homeLoc);
    }

}
