package fr.kangpvp.addsurvival.menu;

import de.themoep.inventorygui.InventoryGui;
import fr.kangpvp.addsurvival.Main;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class HomesGui implements InventoryHolder {
    Inventory inventory;
    InventoryGui gui;
    public String[] guiSetup = {
            "  s i z  ",
            "  ggggg  ",
            "  fpdnl  "
    };

    public HomesGui(){
        this.inventory = Main.getInstance().getServer().createInventory(this, 9*3);
    }



    public static void buildGui(){


    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}

