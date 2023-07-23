package fr.kangpvp.addsurvival.commands.home;

import fr.kangpvp.addsurvival.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CommandDelhome implements CommandExecutor, TabCompleter {


    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();

            PlayerHomes playerHomes = PlayerHomes.getPlayerHomesFromUUID(uuid);

            if(args.length != 1){
                player.sendMessage(Main.getInstance().prefix + "§7Ajouter le nom de votre home /delhome [name]");
                return false;
            }

            String nameHome = args[0];

            assert playerHomes != null;
            ArrayList<String> homesList = playerHomes.getHomeList();
            HashMap<String, Location> homesLoc = playerHomes.getHomeLoc();
            int nbActualHome = homesList.size();

            if(nbActualHome == 0){
                player.sendMessage(Main.getInstance().prefix + "§7Vous n'avez pas de home");
                return false;
            }
            if(!homesList.contains(nameHome)){
                player.sendMessage(Main.getInstance().prefix + "§7Vous n'avez pas de §fhome sous ce nom");
                return false;
            }

            homesList.remove(nameHome);
            homesLoc.remove(nameHome);

            playerHomes.setHomeList(homesList);
            playerHomes.setHomeLoc(homesLoc);

            playerHomes.savePlayerHome(player);
            Main.getInstance().getHomeUtils().saveHomeDb(player); //SQL save

            player.sendMessage(Main.getInstance().prefix + "§7Vous avez supprimé votre home §f" + nameHome);

        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        Player player = (Player) sender;
        //PlayerHomes playerHomes = Main.INSTANCE.tabPlayerToPlayerHome.get(player.getUniqueId().toString());

        if (args.length == 1){
            //ArrayList<String> homesList = playerHomes.getHomeList();
            //return homesList;
        }
        return null;
    }
}
