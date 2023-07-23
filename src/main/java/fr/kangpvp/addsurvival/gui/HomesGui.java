package fr.kangpvp.addsurvival.gui;

import fr.kangpvp.addsurvival.commands.home.PlayerHomes;
import fr.kangpvp.addsurvival.manager.CustomGui;
import fr.kangpvp.addsurvival.manager.GuiButton;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomesGui {

    CustomGui gui;
    PlayerHomes playerHomes;

    public HomesGui(CustomGui menuGui, PlayerHomes playerHomes){
        this.gui = menuGui;
        this.playerHomes = playerHomes;
    }

    public void build(Player player) {
        ArrayList<String> listHomes = playerHomes.getHomeList();

        int slot = 0;
        for(String home : listHomes){
            Location loc = playerHomes.getHomeLoc().get(home);
            World.Environment worldType = loc.getWorld().getEnvironment();

            Material worldMat = Material.GRASS_BLOCK;
            String worldName = "§2Normal";


            if (worldType == World.Environment.NETHER){
                worldMat = Material.NETHERRACK;
                worldName = "§4Nether";
            } else if (worldType == World.Environment.THE_END){
                worldMat = Material.END_STONE;
                worldName = "§5End";
            }

            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ();

            List<String> lore = Arrays.asList ("§7Monde: " + worldName, "§7 x: §f" + x + "§7 y: §f" + y + "§7 z: §f" + z, "", "§eClic droit pour se TP");

            GuiButton homeButton = new GuiButton(CustomGui.itemGui(worldMat, "§7Name: §f" + home, lore));

            homeButton.setAction(() -> {
                player.teleport(loc);
                player.closeInventory();
            });

            gui.setItem(homeButton, slot);

            slot++;
        }



    }


}
