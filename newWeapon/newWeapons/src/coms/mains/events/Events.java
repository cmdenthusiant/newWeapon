package src.coms.mains.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.util.Vector;

import net.minecraft.world.level.Explosion;
import src.coms.mains.Main;

public class Events implements Listener{
    private Main plugin;

    public Events(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    private String BowMode = "Arrow";//*****just for ONE PLAYER*****

    /*private final ItemStack WitherLordArmor(String armor) {
        ItemStack helmet1 = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chestplate1 = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggings1 = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots1 = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet1.getItemMeta();
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate1.getItemMeta();
        LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings1.getItemMeta();
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots1.getItemMeta();
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
        helmet1.setItemMeta(helmetMeta);
        chestplate1.setItemMeta(chestplateMeta);
        leggings1.setItemMeta(leggingsMeta);
        boots1.setItemMeta(bootsMeta);
        List<ItemStack> items = new ArrayList<ItemStack>();
        items.add(helmet1);
        items.add(chestplate1);
        items.add(leggings1);
        items.add(boots1);
        if (armor.equalsIgnoreCase("Helmet")) {
            return helmet1;
        }
        if (armor.equalsIgnoreCase("Chestplate")) {
            return chestplate1;
        }
        if (armor.equalsIgnoreCase("Leggings")) {
            return leggings1;
        }
        if (armor.equalsIgnoreCase("Boots")) {
            return boots1;
        }
        return null;
    }*/

    @EventHandler
    public void onPlayerClicks(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Action act = event.getAction();
        if (act.equals(Action.LEFT_CLICK_AIR) || act.equals(Action.LEFT_CLICK_BLOCK)) {
            ItemStack item = p.getInventory().getItemInMainHand();
            if (item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null) {return;}
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "unlimited bow") && item.getType() == Material.BOW) {
                //Arrow stand = (Arrow) p.getWorld().spawnEntity(p.getEyeLocation().toVector().add(p.getLocation().getDirection().multiply(2)).toLocation(p.getWorld(), p.getLocation().getYaw(), p.getLocation().getPitch()), EntityType.ARROW);
                if (this.BowMode == "WitherSkull") {
                    WitherSkull stand = p.launchProjectile(WitherSkull.class);
                    stand.setCustomName("Unlimited bow's arrow");
                    stand.setShooter(p);
                }
                if (this.BowMode == "Arrow") {
                    Arrow stand = p.launchProjectile(Arrow.class);
                    stand.setCustomName("Unlimited bow's arrow");
                    stand.setShooter(p);
                    stand.setCritical(true);
                    stand.setVelocity(stand.getVelocity().multiply(1.5));
                }
            }
        }
        if (act.equals(Action.RIGHT_CLICK_AIR) || act.equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack item = p.getInventory().getItemInMainHand();
            if (item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null) {return;}
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "unlimited bow") && item.getType() == Material.BOW) {
                event.setCancelled(true);
                if (this.BowMode == "Arrow") {
                    this.BowMode = "WitherSkull";
                } else {
                    this.BowMode = "Arrow";
                }
                p.sendMessage(ChatColor.AQUA + "[" + ChatColor.GOLD + "Unlimited bow" + ChatColor.AQUA +"]" + ChatColor.WHITE+ ": Your bow shoots " + this.BowMode + " now.");
            }
        }
    }
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity entity = event.getEntity();
        if (!(damager.getCustomName() == null) && (damager.getCustomName().equals("Unlimited bow's arrow"))) {
            event.setDamage(200.0);
            
        }
        if (damager instanceof WitherSkull) {
            WitherSkull witherSkull = (WitherSkull) damager;
            if(witherSkull.getShooter() instanceof Player) {
                Player p = (Player) witherSkull.getShooter();
                ItemStack helmet = p.getInventory().getHelmet();
                if (helmet != null && helmet.getItemMeta() != null && helmet.getItemMeta().hasLore() && helmet.getItemMeta().getDisplayName() != null && helmet.getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "WitherLord Helmet")){
                    ItemStack chestplate = p.getInventory().getChestplate();
                    if (chestplate != null && chestplate.getItemMeta() != null && chestplate.getItemMeta().hasLore() && chestplate.getItemMeta().getDisplayName() != null && chestplate.getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "WitherLord Chestplate")){
                        ItemStack leggings = p.getInventory().getLeggings();
                        if (leggings != null && leggings.getItemMeta() != null && leggings.getItemMeta().hasLore() && leggings.getItemMeta().getDisplayName() != null && leggings.getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "WitherLord Leggings")){
                            ItemStack boots = p.getInventory().getBoots();
                            if (boots != null && boots.getItemMeta() != null && boots.getItemMeta().hasLore() && boots.getItemMeta().getDisplayName() != null && boots.getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "WitherLord Boots")){
                                event.setDamage(event.getDamage() * 2);
                            }
                        }
                    } 
                }
            }
        }
        //if (!(entity instanceof Player) && !(entity.getCustomName() == null) &&entity.getCustomName().equalsIgnoreCase("WitherLord's Wither")) {event.setCancelled(true);}
        if (entity instanceof Player) {
            Player p = (Player) entity;
            if (damager instanceof Arrow || damager instanceof WitherSkull || damager instanceof Fireball || damager instanceof Explosion ) {
                ItemStack helmet = p.getInventory().getHelmet();
                if (helmet != null && helmet.getItemMeta() != null && helmet.getItemMeta().hasLore() && helmet.getItemMeta().getDisplayName() != null && helmet.getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "WitherLord Helmet")){
                    ItemStack chestplate = p.getInventory().getChestplate();
                    if (chestplate != null && chestplate.getItemMeta() != null && chestplate.getItemMeta().hasLore() && chestplate.getItemMeta().getDisplayName() != null && chestplate.getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "WitherLord Chestplate")){
                        ItemStack leggings = p.getInventory().getLeggings();
                        if (leggings != null && leggings.getItemMeta() != null && leggings.getItemMeta().hasLore() && leggings.getItemMeta().getDisplayName() != null && leggings.getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "WitherLord Leggings")){
                            ItemStack boots = p.getInventory().getBoots();
                            if (boots != null && boots.getItemMeta() != null && boots.getItemMeta().hasLore() && boots.getItemMeta().getDisplayName() != null && boots.getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "WitherLord Boots")){
                                event.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        Projectile arrow = event.getEntity();
        if ((arrow instanceof Arrow) && !(arrow.getCustomName() == null) && arrow.getCustomName().equals("Unlimited bow's arrow")) {arrow.remove();}
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        /*this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            public void run() {
                Player p = (Player) event.getPlayer();
                ItemStack helmet = p.getInventory().getHelmet();
                ItemStack chestplate = p.getInventory().getChestplate();
                ItemStack leggings = p.getInventory().getLeggings();
                ItemStack boots = p.getInventory().getBoots();
                ItemStack helmet1 = WitherLordArmor("Helmet");
                ItemStack chestplate1 = WitherLordArmor("Chestplate");
                ItemStack leggings1 = WitherLordArmor("Leggings");
                ItemStack boots1 = WitherLordArmor("Boots");
                if ((helmet == null) || (chestplate == null) || (leggings == null) || (boots == null) || !(helmet.isSimilar(helmet1)) || !(chestplate.isSimilar(chestplate1)) || !(leggings.isSimilar(leggings1)) || !(boots.isSimilar(boots1))){
                    List<Entity> entitys = p.getNearbyEntities(10.0, 10.0, 10.0);
                    if (!(entitys.size() == 0)) {
                        for (Entity entityNearBy : entitys) {
                            if (entityNearBy != null && entityNearBy.getCustomName() != null && entityNearBy.getCustomName().equals(p.getUniqueId() + " WitherLord's Wither")) {
                                entityNearBy.remove();
                            }
                        }
                    }
                } else {
                    List<Entity> entitys = p.getNearbyEntities(10.0,10.0,10.0);
                    if (entitys.size() <= 0) {
                        Wither wither = (Wither) p.getWorld().spawnEntity(p.getLocation(), EntityType.WITHER);
                        wither.setAI(false);
                        wither.setAware(false);
                        wither.setInvulnerable(true);
                        wither.setSilent(true);
                        wither.setHealth(10.0);
                        wither.setCustomName(p.getUniqueId() + " WitherLord's Wither");
                        //wither.setInvisible(true);
                        wither.getBossBar().setVisible(false);
                        wither.getBoundingBox().resize(1.0, 1.0, 1.0, 2.0, 2.0, 2.0);
                    } else {
                        for (Entity entityNearBy : entitys) {
                            if (entityNearBy != null && entityNearBy.getCustomName() != null && entityNearBy.getCustomName().equals(p.getUniqueId() + " WitherLord's Wither")) {
                                Vector vec = new Vector(p.getLocation().getDirection().getX()-1, p.getLocation().getDirection().getY(), p.getLocation().getDirection().getX());
                                Location loca = p.getLocation().getDirection().subtract(vec).toLocation(p.getWorld(), p.getLocation().getYaw(), p.getLocation().getPitch());
                                entityNearBy.teleport(loca);
                                //plugin.getLogger().info(p.getLocation().toVector().toString() + " ;; " + p.getLocation().getDirection());
                            } else {
                                if (entitys.get(entitys.size()-1) == entityNearBy) {
                                    Wither wither = (Wither) p.getWorld().spawnEntity(p.getLocation(), EntityType.WITHER);
                                    wither.setAI(false);
                                    wither.setAware(false);
                                    wither.setInvulnerable(true);
                                    wither.setSilent(true);
                                    wither.setHealth(10.0);
                                    wither.setCustomName(p.getUniqueId() + " WitherLord's Wither");
                                    //wither.setInvisible(true);
                                    wither.getBossBar().setVisible(false);
                                    wither.getBoundingBox().resize(1.0, 1.0, 1.0, 2.0, 2.0, 2.0);
                                }
                            }
                        }
                    }
                }
            }
        }, 0, 10);*/
    }
}