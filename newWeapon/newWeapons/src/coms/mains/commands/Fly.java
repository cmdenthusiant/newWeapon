package src.coms.mains.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import src.coms.mains.Main;

public class Fly implements CommandExecutor {
    public Fly(Main plugin) {
        plugin.getCommand("fly").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.isOp()) {
            Player p = (Player) sender;
            if (p.getAllowFlight()) {
                p.setAllowFlight(false);
                p.sendMessage(ChatColor.RED + "Disabled Flight");
            } else {
                p.setAllowFlight(true);
                p.sendMessage(ChatColor.GREEN + "Enabled Flight");
            }
        }
        return false;
    }
}
