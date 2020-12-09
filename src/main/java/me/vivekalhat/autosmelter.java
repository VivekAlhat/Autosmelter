package me.vivekalhat;

import org.bukkit.plugin.java.JavaPlugin;

public final class autosmelter extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Enabling Autosmelter ..");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disabling Autosmelter ..");
    }
}
