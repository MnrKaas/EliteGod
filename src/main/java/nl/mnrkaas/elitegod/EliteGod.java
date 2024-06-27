package nl.mnrkaas.elitegod;

import nl.mnrkaas.elitegod.commands.EliteGodCommand;
import nl.mnrkaas.elitegod.commands.GodCommand;
import nl.mnrkaas.elitegod.util.Configuration;
import nl.mnrkaas.elitegod.util.Misc;
import nl.mnrkaas.elitegod.util.UpdateListener;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class EliteGod extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        PluginManager pm = getServer().getPluginManager();
        Bukkit.getLogger().info("[EliteGod] Loading Update Listener...");
        pm.registerEvents(this, this);

        Bukkit.getLogger().info("[EliteGod] Loading Commands... (1/2)");
        getCommand("god").setExecutor(new GodCommand());
        Bukkit.getLogger().info("[EliteGod] Loading Commands... (2/2)");
        getCommand("elitegod").setExecutor(new EliteGodCommand());

        Bukkit.getLogger().info("[EliteGod] Loading Permissions... (1/2)");
        pm.addPermission(new Permission("elitegod.toggle"));
        Bukkit.getLogger().info("[EliteGod] Loading Permissions... (2/2)");
        pm.addPermission(new Permission("elitegod.admin"));

        Bukkit.getLogger().info("[EliteGod] Loading Configuration File... (language.yml)");
        Configuration.LangSetup();
        Configuration.getLang().addDefault("Reload", "&e&lElite&6&lGod &7&l| &7The configuration has been &asuccessfully &7reloaded");
        Configuration.getLang().addDefault("No Permission", "&e&lElite&6&lGod &7&l| &cYou don't have the correct permissions to perform this action.");
        Configuration.getLang().addDefault("God Enabled", "&e&lElite&6&lGod &7&l| &7God mode has been &aEnabled");
        Configuration.getLang().addDefault("God Disabled", "&e&lElite&6&lGod &7&l| &7God mode has been &cDisabled");
        Configuration.getLang().options().copyDefaults(true);
        Configuration.saveLang();
        Bukkit.getLogger().info("[EliteGod] Loaded Configuration File (language.yml)");

        Bukkit.getLogger().info("[EliteGod] Loading bStats...");
        int pluginId = 22415;
        Metrics metrics = new Metrics(this, pluginId);

        (new UpdateListener(this, 117634)).getLatestVersion(version -> {
            if (!getDescription().getVersion().equalsIgnoreCase(version)) {
                Bukkit.getLogger().info("\n\nAn update for EliteGod is available\nThe newest version is v" + version + ", you're on v" + getDescription().getVersion() + "\nDownload it here:\nhttps://www.spigotmc.org/resources/1-16x-1-21x-elitegod-by-mnrkaas.117634/\n");
            }

        });

        Bukkit.getLogger().info("[EliteGod] Plugin has been Enabled");

    }

    @Override
    public void onDisable() {

        PluginManager pm = getServer().getPluginManager();
        Bukkit.getLogger().info("[EliteGod] Unloading Permissions... (1/2)");
        pm.removePermission(new Permission("elitegod.toggle"));
        Bukkit.getLogger().info("[EliteGod] Unloading Permissions... (2/2)");
        pm.removePermission(new Permission("elitegod.admin"));
        Bukkit.getLogger().info("[EliteGod] Permissions Unloaded (2)");

        Bukkit.getLogger().info("[EliteGod] Plugin Disabled");
    }

    @EventHandler
    public void isUpToDate(PlayerJoinEvent e) {
        if (!e.getPlayer().hasPermission("EliteGod.updates"))
            return;
        (new UpdateListener(this, 117634)).getLatestVersion(version -> {
            if (!getDescription().getVersion().equalsIgnoreCase(version)) {
                e.getPlayer().sendMessage(Misc.colorize("\n&e&lElite&6&lGod &7&l| &7An update for &aEliteGod &7is available.\n  The newest version is &av" + version + "&7, you're on &av" + getDescription().getVersion() + "\n&7Download it here:\n&ahttps://www.spigotmc.org/resources/1-16x-1-21x-elitegod-by-mnrkaas.117634/\n"));
            }
        });
    }
}
