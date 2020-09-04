package com.gmail.alexradu04.manhunt;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class HuntCommand implements CommandExecutor {
    private final MainPlugin plugin;

    public HuntCommand(MainPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (sender instanceof Player && split.length == 1) {
            String victim = split[0];
            boolean findPlayer = false;
            for (int i = 0; i < plugin.getServer().getOnlinePlayers().toArray().length; ++i) {
                CraftPlayer craftPlayer = (CraftPlayer) plugin.getServer().getOnlinePlayers().toArray()[i];
                Player player = (Player) craftPlayer;
                if (Objects.equals(victim, player.getDisplayName())) {
                    findPlayer = true;
                }
            }
            if (!findPlayer) {
                sender.sendMessage("Nonexistent Player");
                return false;
            }
            for (int i = 0; i < plugin.getServer().getOnlinePlayers().toArray().length; ++i) {
                CraftPlayer craftPlayer = (CraftPlayer) plugin.getServer().getOnlinePlayers().toArray()[i];
                Player player = (Player) craftPlayer;
                player.getInventory().clear();
                if (!Objects.equals(victim, player.getDisplayName())) {
                    player.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
                }
            }
            plugin.startHunting(victim);
            return true;
        } else
            return false;
    }
}
