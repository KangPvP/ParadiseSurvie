package fr.kangpvp.addsurvival.commands.home;

import fr.kangpvp.addsurvival.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CommandSethome implements CommandExecutor {

    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();

            PlayerHomes playerHomes = PlayerHomes.getPlayerHomesFromUUID(player.getUniqueId());

            if(args.length != 1){
                player.sendMessage(Main.getInstance().prefix + "Ajouter le nom de votre home. §7/sethome [name]");
                return false;
            }

            String nameHome = args[0];

            String world = player.getLocation().getWorld().getName();
            if(!world.equals("world") && !world.equals("world_nether") && !world.equals("world_the_end")) {
                player.sendMessage(Main.getInstance().prefix + "§7Vous ne pouvez pas placer de §fhomes dans ce monde");
                return false;
            }

            ArrayList<String> homesList = playerHomes.getHomeList();     //get homesList in PlayerHome
            HashMap<String, String> homeLoc = playerHomes.getHomeLoc();    //get homesLoc in PlayerHome

            int nbMaxHomes = HomeUtils.getMaxHome(player);
            int nbActualHome = homesList.size();

            if(nbActualHome > nbMaxHomes){
                player.sendMessage(Main.getInstance().prefix + "§7Vous avez atteint votre §fmaximum de Homes");
                return false;
            }
            if(homesList.contains(nameHome)){
                player.sendMessage(Main.getInstance().prefix + "§7Ce nom de home est §fdéja utilisé");
                return false;
            }

            homesList.add(nameHome);  //edit homesList
            homeLoc.put(nameHome, HomeUtils.locToString(player.getLocation()));   //edit homesList

            playerHomes.setHomeList(homesList);  //save homeList in PlayerHome
            playerHomes.setHomeLoc(homeLoc);  //save homeLoc in PlayerHome

            Main.getInstance().listPlayerHomes.set(index, playerHomes);   //save List PlayerHome

            player.sendMessage(Main.getInstance().prefix + "Votre home " + nameHome + " a été défini ici");

        }

        return false;
    }
}
