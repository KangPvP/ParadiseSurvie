package fr.kangpvp.addsurvival.data;

import fr.kangpvp.addsurvival.manager.CustomGui;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerData {

    private Player player;
    private CustomGui openGui;
    private final HashMap<String, Long> cooldowns;
    private final HashMap<String, Integer> limit;
    public PlayerData(Player player){
        this.player = player;
        this.openGui = null;
        this.cooldowns = new HashMap<>();
        this.limit = new HashMap<>();
    }

    public Player getPlayer() {
        return player;
    }

    public CustomGui getOpenGui(){
        return openGui;
    }

    public void setOpenGui(CustomGui openGui){
        this.openGui = openGui;
    }


    //Cooldowns Methodes
    public Long getCdFurnace(){
        if(!this.cooldowns.containsKey("furnace")){
            this.cooldowns.put("furnace", System.currentTimeMillis());
        }

        return this.cooldowns.get("furnace");
    }

    public void setCdFurnace(){
        this.cooldowns.put("furnace", System.currentTimeMillis());
    }

    public Long getCdRepair(){
        if(!this.cooldowns.containsKey("repair")){
            this.cooldowns.put("repair", 0L);
        }

        return this.cooldowns.get("repair");
    }

    public void setCdRepair(){
        this.cooldowns.put("repair", System.currentTimeMillis());
    }


    //Limite Methodes
    public Integer getLimitFurnace(){
        if(!this.limit.containsKey("furnace")){
            this.limit.put("furnace", 0);
        }
        return this.limit.get("furnace");
    }

    public void setLimitFurnace(int limit){
        this.limit.put("furnace", limit);
    }
}
