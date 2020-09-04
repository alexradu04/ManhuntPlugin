
package com.gmail.alexradu04.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerListener implements Listener {
    private final MainPlugin plugin;

    public PlayerListener(MainPlugin instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getLogger().info(event.getPlayer().getName() + " joined the server! :D");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getLogger().info(event.getPlayer().getName() + " left the server! :'(");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        final ItemStack compass = event.getItem();
        if (plugin.gameOn && (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK))) {
            if (player.getInventory().getItemInMainHand().getType() == Material.COMPASS ||
                    player.getInventory().getItemInOffHand().getType() == Material.COMPASS) {
                if (!Bukkit.getPlayerExact(plugin.getHunted()).getWorld().getName().equals(player.getWorld().getName())) {
                    player.sendMessage(String.format("Compass is pointing at %s's last position", plugin.getHunted()));
                    return;
                }
                final CompassMeta meta = (CompassMeta) compass.getItemMeta();
                meta.setLodestoneTracked(false);
                meta.setLodestone(plugin.getHuntedLocation());
                Location targetLocation = plugin.getHuntedLocation();
                compass.setItemMeta((ItemMeta) meta);
                player.sendMessage(String.format("Compass is now pointing at %s", plugin.getHunted()));
            }
        }
    }
}
