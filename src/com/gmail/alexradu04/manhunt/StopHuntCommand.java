package com.gmail.alexradu04.manhunt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopHuntCommand implements CommandExecutor {
    private final MainPlugin plugin;

    public StopHuntCommand(MainPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (sender instanceof Player && split.length == 0) {
            plugin.stopHunting();
            return true;
        }
        return false;
    }
}
