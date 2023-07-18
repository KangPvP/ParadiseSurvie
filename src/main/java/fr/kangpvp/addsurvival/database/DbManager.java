package fr.kangpvp.addsurvival.database;

import fr.kangpvp.addsurvival.utils.Config;

import java.sql.SQLException;

public class DbManager {
    private DbConnection playerConnection;

    public DbManager(){
        this.playerConnection = new DbConnection(new DbCredentials(
                Config.getString("database.host"),
                Config.getString("database.user"),
                Config.getString("database.pass"),
                Config.getString("database.dbName"),
                Config.getInt("database.port")));
    }

    public DbConnection getPlayerConnection(){
        return playerConnection;
    }

    public void close() {
        try{
            this.playerConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
