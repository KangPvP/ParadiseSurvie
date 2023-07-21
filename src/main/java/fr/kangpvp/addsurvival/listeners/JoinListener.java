package fr.kangpvp.addsurvival.listeners;

import fr.kangpvp.addsurvival.Main;
import fr.kangpvp.addsurvival.commands.home.HomeUtils;
import fr.kangpvp.addsurvival.commands.home.PlayerHomes;
import fr.kangpvp.addsurvival.database.DbConnection;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        DbConnection playerConnection = Main.getInstance().getDbManager().getPlayerConnection();

        try{
            Connection connection = playerConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, name, homes FROM dataplayers WHERE uuid = ?");

            preparedStatement.setString(1, uuid.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                PlayerHomes playerHomes = PlayerHomes.getPlayerHomesFromUUID(uuid);

                if(playerHomes == null){
                    String homeData = resultSet.getString("home");

                    //Save Home in listPlayerHomes
                    HashMap<String, Location> datahomes = HomeUtils.strToHash(homeData);
                    ArrayList<String> nameHomes = new ArrayList<>();

                    datahomes.forEach((key, value) -> {
                        nameHomes.add(key);
                    });

                    playerHomes = PlayerHomes.playerDataHomes(player, nameHomes, datahomes);
                    playerHomes.savePlayerHome(player);
                }

            } else {
                //If is the First Connection
                createUserDb(connection, player); //Add player in SQL DB
                PlayerHomes playerHomes = PlayerHomes.playerDataHomes(player, new ArrayList<String>(), new HashMap<String, Location>());
                playerHomes.savePlayerHome(player);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void createUserDb(Connection connection, Player player){
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT id FROM dataplayers ORDER BY id DESC LIMIT 1";
            ResultSet rs = st.executeQuery(sql);

            int id = 0;

            while (rs.next()) {
                id = rs.getInt("id");
            }

            rs.close();
            st.close();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO dataplayers (id, uuid, name, homes, join_at, update_at) VALUES (?, ?, ?, ?, ?, ?)");
            long time = System.currentTimeMillis();

            preparedStatement.setInt(1, id + 1);
            preparedStatement.setString(2, player.getUniqueId().toString());
            preparedStatement.setString(3, player.getName());
            preparedStatement.setString(4, "");
            preparedStatement.setTimestamp(5, new Timestamp(time));
            preparedStatement.setTimestamp(6, new Timestamp(time));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
