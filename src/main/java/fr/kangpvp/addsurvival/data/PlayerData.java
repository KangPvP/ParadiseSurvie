package fr.kangpvp.addsurvival.data;

import fr.kangpvp.addsurvival.manager.CustomGui;
import org.bukkit.entity.Player;

public class PlayerData {

    private Player player;
    private CustomGui openGui;

    public PlayerData(Player player){
        this.player = player;
        this.openGui = null;
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

}
