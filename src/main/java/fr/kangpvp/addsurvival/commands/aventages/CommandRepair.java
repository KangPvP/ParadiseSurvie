package fr.kangpvp.addsurvival.commands.aventages;

import fr.kangpvp.addsurvival.Main;
import fr.kangpvp.addsurvival.data.PlayerData;
import fr.kangpvp.addsurvival.data.PlayerDataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.jetbrains.annotations.NotNull;

public class CommandRepair implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            PlayerData playerData = PlayerDataManager.getData(player);

            if(playerData.getCdRepair() == 0L){
                repair(player);
                playerData.setCdRepair();
                return false;
            }
            double delay = (playerData.getCdRepair()+1000*3600*4) - System.currentTimeMillis();

            if(delay <= 0){
                repair(player);
            } else {
                String displayDelay = displayTime((double) delay/1000);
                player.sendMessage(Main.getInstance().prefix + "Attendez " + displayDelay + " avant de pouvoir executer cette commande");
            }
        }

        return false;
    }

    private void repair(Player player){
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getItemMeta() instanceof Damageable) {
            Damageable damageable = (Damageable) item.getItemMeta();
            damageable.setDamage(0);
            item.setItemMeta(damageable);
            player.sendMessage(Main.getInstance().prefix + "Votre item a été réparer");
        } else{
            player.sendMessage(Main.getInstance().prefix + "Vous n'avez pas d'item en main");
        }
    }


    private String displayTime(double ticksSec) {
        int hh = (int) Math.floor(ticksSec/3600);
        int mm = (int) Math.floor((ticksSec % 3600) / 60);

        if(hh == 0){
            return mm + "min";
        }else{
            return "" + hh + "h et " + mm + "min";
        }
    }

}
