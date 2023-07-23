package fr.kangpvp.addsurvival.listeners;

import fr.kangpvp.addsurvival.data.PlayerData;
import fr.kangpvp.addsurvival.data.PlayerDataManager;
import fr.kangpvp.addsurvival.manager.GuiButton;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GuiListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        PlayerData playerData = PlayerDataManager.getData(player);

        if(playerData.getOpenGui() != null && event.getSlot() != 999) {

            GuiButton guiButton = playerData.getOpenGui().getButtons().get(event.getSlot());

            if(guiButton != null){
                guiButton.setClickType(event.getClick());

                playerData.getOpenGui().handleButton( event.getSlot() );
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryCloseEvent event){
        PlayerData data = PlayerDataManager.getData((Player) event.getPlayer());
        data.setOpenGui(null);
    }


}
