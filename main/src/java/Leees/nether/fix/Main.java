package Leees.nether.fix;

import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    public void onEnable() {
        this.saveDefaultConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getLogger().info("[LeeesNetherFix] Plugin Enabled!");
    }

    public void onDisable() {
        Bukkit.getServer().getLogger().info("[LeeesNetherFix] Plugin Disabled!");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getEnvironment() == Environment.NETHER) {
            if (player.hasPermission("nether.bypass")) {
                return;
            }

            int top = this.getConfig().getInt("netherTopLayer");
            if (player.getLocation().getY() > (double)top) {
                Location toSpawn = new Location(player.getLocation().getWorld(), (double)player.getLocation().getBlockX() + 0.5D, (double)top, (double)player.getLocation().getBlockZ() + 0.5D);
                toSpawn.subtract(0.0D, 1.0D, 0.0D).getBlock().setType(Material.AIR);
                toSpawn.subtract(0.0D, 1.0D, 0.0D).getBlock().setType(Material.AIR);
                toSpawn.subtract(0.0D, 1.0D, 0.0D).getBlock().setType(Material.BEDROCK);
                player.teleport(toSpawn.add(0.0D, 1.0D, 0.0D));
                event.getPlayer().setHealth(0.0D);
                player.sendMessage(this.convertedLang("netherTopMessage"));
            }

            int bottom = this.getConfig().getInt("netherBottomLayer");
            if (player.getLocation().getY() < (double)bottom) {
                Location toSpawn = new Location(player.getLocation().getWorld(), (double)player.getLocation().getBlockX() + 0.5D, (double)bottom, (double)player.getLocation().getBlockZ() + 0.5D);
                toSpawn.subtract(0.0D, 1.0D, 0.0D).getBlock().setType(Material.AIR);
                toSpawn.subtract(0.0D, 1.0D, 0.0D).getBlock().setType(Material.AIR);
                toSpawn.subtract(0.0D, 1.0D, 0.0D).getBlock().setType(Material.BEDROCK);
                player.teleport(toSpawn.add(0.0D, 1.0D, 0.0D));
                event.getPlayer().setHealth(0.0D);
                player.sendMessage(this.convertedLang("netherTopMessage"));
            }
        }
    }

    String convertedLang(String toConvert) {
        return ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(toConvert));
    }
}
