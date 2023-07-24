package fr.kangpvp.addsurvival.listeners;

import fr.kangpvp.addsurvival.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.Map;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        for(Map.Entry<String, ArrayList<String>> entry : Main.getInstance().getRegionManage().getpRgCmds().entrySet()){

        }



    }
}


