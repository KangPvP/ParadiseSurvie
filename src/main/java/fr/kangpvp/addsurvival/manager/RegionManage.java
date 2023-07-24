package fr.kangpvp.addsurvival.manager;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import fr.kangpvp.addsurvival.utils.Config;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RegionManage {

    private final HashMap<String, ArrayList<String>> pRgCmds;

    public RegionManage(){
        this.pRgCmds = pRgCmdsLoad();

        System.out.println("Region Portail Load");


    }

    public HashMap<String, ArrayList<String>> pRgCmdsLoad(){
        HashMap<String, ArrayList<String>> pRgCmds = new HashMap<>();

        int portailnb = Config.getInt("portails.portailnb");
        String path = "portails.portail";

        for(int i = 0; i < portailnb; i++){
            String nameRegion = Config.getString(path + i + ".name");
            ArrayList<String> cmdsList = (ArrayList<String>) Config.getColoredList(path + i + ".commands");

            pRgCmds.put(nameRegion, cmdsList);
        }

        return pRgCmds;
    }


    public ProtectedRegion getRegionsByName(Player player,String name){
        World world = BukkitAdapter.adapt(player.getWorld());

        RegionManager regions = WorldGuard.getInstance().getPlatform().getRegionContainer().get(world);
        ProtectedRegion region = regions.getRegion(name);

        return region;
    }

    public boolean isInRegion(Player player, String regionName) {

            com.sk89q.worldedit.util.Location location = BukkitAdapter.adapt(player.getLocation());
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            ApplicableRegionSet set = query.getApplicableRegions(location);

            //Testé si une des regions dans le quelle se trouve le joueur est dans
            for (ProtectedRegion region : set) {
                if(region.getId().equalsIgnoreCase(regionName)){
                    return true;
                }
            }
        return false;
    }


    public boolean isInRegion(Player player, List regionsTp) {
        com.sk89q.worldedit.util.Location location = BukkitAdapter.adapt(player.getLocation());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(location);

        //Testé si une des regions dans le quelle se trouve le joueur est dans
        for (ProtectedRegion region : set) {
            if(regionsTp.contains( region.getId() )){
                return true;
            }
        }
        return false;
    }

    public HashMap<String, ArrayList<String>> getpRgCmds(){
        return this.pRgCmds;
    }

}



