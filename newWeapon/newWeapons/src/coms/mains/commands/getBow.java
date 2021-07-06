package src.coms.mains.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import src.coms.mains.Main;

public class getBow implements CommandExecutor {
    @SuppressWarnings("unused")
    private Main plugin;
    
    public getBow(Main plugin) {
        plugin.getCommand("getbow").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String iabel, String[] args) {
        Player p = (Player) sender;
        if (!(p instanceof Player)) {return true;}
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Unlimited Bow");
        List<String> lore = new ArrayList<String>();
        lore.add("秒天秒地秒一切");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, false);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        bow.setItemMeta(meta);
        p.getInventory().addItem(bow);
        return false;
    }
}
