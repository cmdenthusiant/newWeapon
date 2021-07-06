package src.coms.mains;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import src.coms.mains.commands.Fly;
import src.coms.mains.commands.getBow;
import src.coms.mains.commands.getWitherLordArmor;
import src.coms.mains.events.Events;

public class Main extends JavaPlugin{
    @Override
    public void onEnable() {
        getLogger().info("newweapon v1.0 Enabled");
        new getBow(this);
        new Fly(this);
        new getWitherLordArmor(this);
        //CommandBlock recipe
        ItemStack commandBlock = new ItemStack(Material.COMMAND_BLOCK);
        NamespacedKey cbKey = new NamespacedKey(this, "Command_Block");
        ShapedRecipe cbRecipe = new ShapedRecipe(cbKey, commandBlock);
        cbRecipe.shape("^&^","@%$","^*^");
        cbRecipe.setIngredient('^', Material.BRICK);
        cbRecipe.setIngredient('&', Material.DIAMOND);
        cbRecipe.setIngredient('@', Material.REDSTONE);
        cbRecipe.setIngredient('%', Material.NETHER_STAR);
        cbRecipe.setIngredient('$', Material.LAPIS_LAZULI);
        cbRecipe.setIngredient('*', Material.EMERALD);
        Bukkit.addRecipe(cbRecipe);
        //Unlimited bow
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Unlimited Bow");
        List<String> lore = new ArrayList<String>();
        lore.add("秒天秒地秒一切");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, false);
        meta.setUnbreakable(true);
        bow.setItemMeta(meta);
        NamespacedKey ubKey = new NamespacedKey(this, "Unlimited_Bow");
        ShapedRecipe UnlimitedBow = new ShapedRecipe(ubKey, bow);
        UnlimitedBow.shape("***","*%*","***");
        UnlimitedBow.setIngredient('*', Material.BOW);
        UnlimitedBow.setIngredient('%', Material.COMMAND_BLOCK);
        Bukkit.addRecipe(UnlimitedBow);
        new Events(this);
    }
}
