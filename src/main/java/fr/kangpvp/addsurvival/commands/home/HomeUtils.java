package fr.kangpvp.addsurvival.commands.home;

import fr.kangpvp.addsurvival.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeUtils {

    public static String hashToStr(HashMap<String, Location> dataHome){
        String dataStrHome = "";
        if(dataHome.size() != 0){
            for(Map.Entry<String, Location> entry : dataHome.entrySet()) {
                String key = entry.getKey();
                Location loc = entry.getValue();
                String value = locToString(loc);
                dataStrHome = "/" + key + ";" + value;

                if(dataStrHome.charAt(0) == '/'){
                    dataStrHome.substring(1, dataStrHome.length());
                }
            }

        }

        return dataStrHome;
    }

    public static HashMap<String, Location> strToHash(String str){
        HashMap<String, Location> dataHomes = new HashMap<>();


        if(str.length() != 0){

            if(str.charAt(0) == '/'){
                str = str.substring(1);
            }

            String[] listHomes = str.split("/");

            for(String homeData : listHomes){
                String[] listHome = homeData.split(";");
                dataHomes.put(listHome[0], stringToLoc(listHome[1]));
            }
        }

        return dataHomes;
    }


    public static String locToString(Location loc){

        String world = loc.getWorld().getName();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        float yaw = loc.getYaw();
        float pitch = loc.getPitch();

        // world:x:y:z:yaw:pitch
        return world + ":" + x + ":" + y + ":" + z + ":" + yaw + ":" + pitch;
    }

    public static Location stringToLoc(String str) {

        String[] list = str.split(":");

        String world = list[0];
        double x = Double.parseDouble(list[1]);
        double y = Double.parseDouble(list[2]);
        double z = Double.parseDouble(list[3]);
        float yaw = Float.parseFloat(list[4]);
        float pitch = Float.parseFloat(list[5]);

        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);

    }

 /*   public static int getMaxHome(Player player){
        String titreName =  PlaceholderAPI.setPlaceholders(player, "%luckperms_first_group_on_tracks_titres%");
        String gradeName = PlaceholderAPI.setPlaceholders(player, "%luckperms_first_group_on_tracks_grades%");   //if gradeName == null  =>  gradeName == ""

        Titre titre = Titres.getGradeFromName(titreName.toLowerCase());

        int homeNb;
        if(titre == null) {
            homeNb = 2;
        }else {
            homeNb = titre.getHomes();
        }

        if(gradeName.equals("VIP")) {
            return homeNb + 3;
        }else if(gradeName.equals("Heros")){
            return homeNb + 7;
        }else if(gradeName.equals("Legende")){
            return homeNb + 12;
        }else {
            return homeNb;
        }
    }*/

    public static List<String> getHomes(Player player) {
        PlayerHomes playerHome = PlayerHomes.getPlayerHomesFromUUID(player.getUniqueId());

        assert playerHome != null;
        return playerHome.getHomeList();
    }


}
