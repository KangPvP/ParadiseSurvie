package fr.kangpvp.addsurvival.commands.home;

import fr.kangpvp.addsurvival.Main;
import fr.kangpvp.addsurvival.database.DbConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeUtils {

    public HomeUtils(){

    }

    public String hashToStr(HashMap<String, Location> dataHome){
        String dataStrHome = "";
        if(dataHome.size() != 0) {

            for(Map.Entry<String, Location> entry : dataHome.entrySet()) {
                String key = entry.getKey();
                Location loc = entry.getValue();
                String value = locToString(loc);
                dataStrHome = dataStrHome + "/" + key + ";" + value;

            }

        }

        return dataStrHome;
    }



    public HashMap<String, Location> strToHash(String str){
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


    public String locToString(Location loc){

        String world = loc.getWorld().getName();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        float yaw = loc.getYaw();
        float pitch = loc.getPitch();

        // world:x:y:z:yaw:pitch
        return world + ":" + x + ":" + y + ":" + z + ":" + yaw + ":" + pitch;
    }

    public Location stringToLoc(String str) {

        String[] list = str.split(":");

        String world = list[0];
        double x = Double.parseDouble(list[1]);
        double y = Double.parseDouble(list[2]);
        double z = Double.parseDouble(list[3]);
        float yaw = Float.parseFloat(list[4]);
        float pitch = Float.parseFloat(list[5]);

        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);

    }

    public static int getMaxHome(Player player){

        return permHomesNb(player) + permHomesNbAdd(player);

    }

    public static int permHomesNb(Player player){
        if(player.hasPermission("psurvie.homes.nb1.15")){
            return 15;
        } else if(player.hasPermission("psurvie.homes.nb1.10")){
            return 10;
        } else if(player.hasPermission("psurvie.homes.nb1.5")){
            return 5;
        } else if(player.hasPermission("psurvie.homes.nb1.1")){
            return 1;
        }
        return 1;
    }

    public static int permHomesNbAdd(Player player){
        if(player.hasPermission("psurvie.homes.nb2.2")){
            return 0;
        }else if(player.hasPermission("psurvie.homes.nb2.1")){
            return 1;
        } else if(player.hasPermission("psurvie.homes.nb2.0")){
            return 2;
        } else {
            return 1;
        }
    }

    public void saveHomeDb(Player player){

        UUID uuid = player.getUniqueId();
        PlayerHomes playerHomes = PlayerHomes.getPlayerHomesFromUUID(uuid);
        assert playerHomes != null;

        String homesData = Main.getInstance().getHomeUtils().hashToStr(playerHomes.getHomeLoc());

        long time = System.currentTimeMillis();

        DbConnection playerConnection = Main.getInstance().getDbManager().getPlayerConnection();
        String sql = "UPDATE dataplayers SET homes = ?, update_at = ? WHERE uuid = '" + uuid + "'";

        try {
            Connection connection = playerConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, homesData );
            preparedStatement.setTimestamp(2, new Timestamp(time));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
