package fr.kangpvp.addsurvival.commands.home;

import fr.kangpvp.addsurvival.gui.HomesGui;
import fr.kangpvp.addsurvival.manager.CustomGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandHomes implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            PlayerHomes playerHomes = PlayerHomes.getPlayerHomesFromUUID(player.getUniqueId());

            CustomGui customGui = new CustomGui("Vos Homes", 3);
            new HomesGui(customGui, playerHomes).build(player);

            customGui.show(player);
        }

        return false;
    }
}
