package fr.kangpvp.addsurvival.commands.home;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandHome implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            PlayerHomes playerHomes = Main.INSTANCE.tabPlayerToPlayerHome.get(player.getUniqueId().toString());

            if(args.length != 1){
                player.sendMessage(Main.INSTANCE.prefix() + "§7Ajoutez le nom de votre home. /home [name]");
                return false;
            }

            String nameHome = args[0];

            ArrayList<String> homesList = playerHomes.getHomeList();

            if(!homesList.contains(nameHome)){
                player.sendMessage(Main.INSTANCE.prefix() +  "§7Vous n'avez pas de home sous ce nom");
                return false;
            }

            HashMap<String, String> homeLoc = playerHomes.getHomeLoc();

            String strLocation = homeLoc.get(nameHome);

            Location locTp = (Location) HomeUtils.stringToLoc(strLocation);

            player.teleport(locTp);

        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        Player player = (Player) sender;
        PlayerHomes playerHomes = Main.INSTANCE.tabPlayerToPlayerHome.get(player.getUniqueId().toString());

        if (args.length == 1){
            ArrayList<String> homesList = playerHomes.getHomeList();
            return homesList;
        }
        return null;
    }


}
