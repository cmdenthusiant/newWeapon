package src.coms.mains.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import src.coms.mains.Main;

public class getWitherLordArmor implements CommandExecutor {
    @SuppressWarnings("unused")
    private Main plugin;

    public getWitherLordArmor(Main plugin) {
        plugin.getCommand("getwitherlordarmor").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (!(p instanceof Player)) {return true;}
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        helmetMeta.setDisplayName(ChatColor.DARK_GRAY + "WitherLord Helmet");
        chestplateMeta.setDisplayName(ChatColor.DARK_GRAY + "WitherLord Chestplate");
        leggingsMeta.setDisplayName(ChatColor.DARK_GRAY + "WitherLord Leggings");
        bootsMeta.setDisplayName(ChatColor.DARK_GRAY + "WitherLord Boots");
        helmetMeta.setColor(Color.BLACK);
        helmetMeta.setUnbreakable(true);
        chestplateMeta.setColor(Color.BLACK);
        chestplateMeta.setUnbreakable(true);
        leggingsMeta.setColor(Color.BLACK);
        leggingsMeta.setUnbreakable(true);
        bootsMeta.setColor(Color.BLACK);
        bootsMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<String>();
        lore.add("當穿上全套裝備時:");
        lore.add(ChatColor.WHITE + " -不受遠程傷害");
        lore.add(ChatColor.WHITE + " -WitherSkull傷害提升一倍");
        lore.add(ChatColor.WHITE + " -蹲下以切換模式");
        helmetMeta.setLore(lore);
        chestplateMeta.setLore(lore);
        leggingsMeta.setLore(lore);
        bootsMeta.setLore(lore);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);
        p.getInventory().addItem(helmet);
        p.getInventory().addItem(chestplate);
        p.getInventory().addItem(leggings);
        p.getInventory().addItem(boots);
        return false;
    }
    
}
