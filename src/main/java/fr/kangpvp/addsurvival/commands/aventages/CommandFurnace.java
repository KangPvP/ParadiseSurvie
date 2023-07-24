package fr.kangpvp.addsurvival.commands.aventages;

import fr.kangpvp.addsurvival.Main;
import fr.kangpvp.addsurvival.data.PlayerData;
import fr.kangpvp.addsurvival.data.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class CommandFurnace implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack resultat = onFurnaceTest(player);
            PlayerData playerData = PlayerDataManager.getData(player);


            if(resultat == null){
                player.sendMessage(Main.getInstance().prefix + "§3Cet item ne peut pas cuire");
                return false;
            }

            if(System.currentTimeMillis() - playerData.getCdFurnace() < 300*1000){
                int limit = playerData.getLimitFurnace();
                int timeReste = (int) (3600*1000 - (System.currentTimeMillis() - playerData.getCdFurnace()));
                if(limit == 10){
                    player.sendMessage(Main.getInstance().prefix + "§7Tu ne peux pas executer cet commande que §f10 fois par heure");
                    player.sendMessage(Main.getInstance().prefix + "§7Attends §f" + displayTime(timeReste) + "§7 avant de pouvoir executer de nouveau cette commande");
                } else {
                    playerData.setLimitFurnace(limit + 1);
                    onFurnace(player, resultat);
                }
            } else {
                playerData.setCdFurnace();
                playerData.setLimitFurnace(1);
                onFurnace(player, resultat);
            }

        }else {
            sender.sendMessage("§cVous n'êtes pas un joueur");
        }
        return false;
    }

    public ItemStack onFurnaceTest(Player player){
        ItemStack resultat = null;
        final ItemStack baseItem = player.getInventory().getItemInMainHand();
        final Iterator<Recipe> i = Bukkit.recipeIterator();
        while (i.hasNext()) {
            Recipe r = i.next();
            if (!(r instanceof FurnaceRecipe)) continue;
            FurnaceRecipe fr = (FurnaceRecipe) r;
            if (fr.getInput().getType() != baseItem.getType()) continue;
            resultat = fr.getResult();
            break;
        }
        return resultat;
    }


    public void onFurnace(Player player, ItemStack resultat){
        final ItemStack baseItem = player.getInventory().getItemInMainHand();
        player.sendMessage(Main.getInstance().prefix + "§3Tu as fais cuire x§2" + baseItem.getAmount() + " " + baseItem.getType().name());
        resultat.setAmount(baseItem.getAmount());
        player.getInventory().setItemInMainHand(resultat);
    }

    private String displayTime(int millisec) {

        double ticksSec = Math.floor(millisec/1000);
        int mm = (int) Math.floor(ticksSec / 60);
        int ss = (int) Math.floor((ticksSec % 60));

        return "" + mm + "min et " + ss + "s";

    }


}
