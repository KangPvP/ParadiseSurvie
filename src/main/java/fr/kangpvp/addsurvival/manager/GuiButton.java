package fr.kangpvp.addsurvival.manager;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class GuiButton {

    private ItemStack stack;
    private ClickType clickType;
    private Runnable action;


    public GuiButton(ItemStack stack){
        this.stack = stack;
    }

    public void setAction(Runnable runnable){
        this.action = runnable;
    }

    public Runnable getAction(){
        return action;
    }


    public ItemStack getStack() {
        return stack;
    }
    public ClickType getClickType(){
        return this.clickType;
    }

    public void setClickType(ClickType clickType){
        this.clickType = clickType;
    }


}
