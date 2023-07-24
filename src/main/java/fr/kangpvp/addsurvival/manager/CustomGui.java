package fr.kangpvp.addsurvival.manager;

import fr.kangpvp.addsurvival.data.PlayerData;
import fr.kangpvp.addsurvival.data.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class CustomGui {

    private int rows;

    private Inventory inventory;
    private HashMap<Integer, GuiButton> buttons = new HashMap<>();

    public CustomGui(String title, int rows){

        if(rows > 6){
            System.out.println("Erreur Rows GUIss");
            return;
        }

        this.rows = rows;
        this.inventory = Bukkit.createInventory(null, rows * 9, title);

    }

    public void setItem(GuiButton button, int slot){
        buttons.put(slot ,button);
        inventory.setItem(slot, button.getStack());
    }

    public void handleButton(int slot){
        GuiButton button = buttons.get(slot);

        if(button != null){
            button.getAction().run();
        }
    }

    public HashMap<Integer, GuiButton> getButtons(){
        return buttons;
    }

    public void show(Player player){
        player.openInventory(inventory);
        PlayerData data = PlayerDataManager.getData((Player) player);
        data.setOpenGui(this);

    }

    public static ItemStack itemGui(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack itemGui(Material material, String name, List<String> lore, int amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inv) {
        this.inventory = inv;
    }

}
