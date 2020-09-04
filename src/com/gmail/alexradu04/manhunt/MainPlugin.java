
package com.gmail.alexradu04.manhunt;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MainPlugin extends JavaPlugin {
    private final PlayerListener playerListener = new PlayerListener(this);
    private String hunted = null;
    public boolean gameOn = false;

    @Override
    public void onDisable() {
        getLogger().info("Goodbye world!");
    }

    @Override
    public void onEnable() {
        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);

        // Register our commands
        getCommand("hunt").setExecutor(new com.gmail.alexradu04.manhunt.HuntCommand(this));
        getCommand("stophunt").setExecutor(new com.gmail.alexradu04.manhunt.StopHuntCommand(this));

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
    }

    public void startHunting(final String player) {
        hunted = player;
        gameOn = true;
    }

    public void stopHunting() {
        gameOn = false;
        hunted = null;
    }

    public String getHunted() {
        return hunted;
    }

    public Location getHuntedLocation() {
        for (int i = 0; i < this.getServer().getOnlinePlayers().toArray().length; ++i) {
            CraftPlayer craftPlayer = (CraftPlayer) this.getServer().getOnlinePlayers().toArray()[i];
            Player player = (Player) craftPlayer;
            if (player.getDisplayName().equals(hunted)) {
                return player.getLocation();
            }
        }
        return null;
    }
}
