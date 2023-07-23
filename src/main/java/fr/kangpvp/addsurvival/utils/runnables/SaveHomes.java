package fr.kangpvp.addsurvival.utils.runnables;

import fr.kangpvp.addsurvival.Main;
import fr.kangpvp.addsurvival.commands.home.HomeUtils;
import fr.kangpvp.addsurvival.commands.home.PlayerHomes;
import fr.kangpvp.addsurvival.database.DbConnection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.K;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

public class SaveHomes extends BukkitRunnable {
    @Override
    public void run() {
        for(Map.Entry<UUID, PlayerHomes> entry : PlayerHomes.mapPlayerHomes.entrySet()){
            UUID uuid = entry.getKey();
            PlayerHomes playerHomes = entry.getValue();

            //String homesData = HomeUtils.hashToStr(playerHomes.getHomeLoc());

            long time = System.currentTimeMillis();

            DbConnection playerConnection = Main.getInstance().getDbManager().getPlayerConnection();
            String sql = "UPDATE player SET homes = ?, update_at = ? WHERE uuid = '" + uuid + "'";

            try {
                Connection connection = playerConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, "s" );
                preparedStatement.setTimestamp(2, new Timestamp(time));
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }



        }


    }
}
