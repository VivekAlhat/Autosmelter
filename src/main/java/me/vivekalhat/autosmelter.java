package me.vivekalhat;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public final class autosmelter extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Enabling Autosmelter ..");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if(!player.hasPlayedBefore()) {
            ItemStack pluginBook = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta bookMeta = (BookMeta) pluginBook.getItemMeta();
            assert bookMeta != null;
            bookMeta.setAuthor("VivekAlhat");
            bookMeta.setTitle("Autosmelter");

            ArrayList<String> pages = new ArrayList<>();
            pages.add("Stop worrying about smelting your ores.\n Autosmelter smelts them automatically for you.");
            pages.add("With this plugin enabled, breaking iron/gold ores will drop ingots without smelting the ores.");
            bookMeta.setPages(pages);
            pluginBook.setItemMeta(bookMeta);

            player.getInventory().addItem(pluginBook);
            player.sendMessage("Autosmelter is enabled on this server. Check your book for more info.");
        }
    }

    @EventHandler
    public void onOreBreak(BlockBreakEvent e) {
        e.setDropItems(false);
        Block block = e.getBlock();
        if(block.getType() == Material.IRON_ORE) {
            Location location = block.getLocation();
            ItemStack itemStack = new ItemStack(Material.IRON_INGOT);
            Objects.requireNonNull(location.getWorld()).dropItemNaturally(location,itemStack);
        } else if(block.getType() == Material.GOLD_ORE || block.getType() == Material.NETHER_GOLD_ORE) {
            Location location = block.getLocation();
            ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
            Objects.requireNonNull(location.getWorld()).dropItemNaturally(location,itemStack);
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disabling Autosmelter ..");
    }
}
