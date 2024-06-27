package nl.mnrkaas.elitegod.commands;

import nl.mnrkaas.elitegod.util.Configuration;
import nl.mnrkaas.elitegod.util.Misc;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EliteGodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase("info"))) {
            if (sender instanceof Player p) {
                p.sendMessage(Misc.colorize("&e\n&e&lElite&6&lGod &7&l| &7Version &av1.0.0 &7by &aMnrKaas\n&7    Use &a/eg help &7for a list of commands\n&6"));
            } else {
                Bukkit.getLogger().info(Misc.colorize("\nEliteGod | Version v1.0.0 by MnrKaas\n   Use /eg help for a list of commands\n"));
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender instanceof Player p) {
                    if (p.hasPermission("elitegod.admin")) {
                        Bukkit.getLogger().info("[EliteGod] Reloading Config...");
                        Configuration.reloadConfig();
                        Bukkit.getLogger().info("[EliteGod] Config Reloaded...");
                        p.sendMessage(Misc.colorize(Configuration.getLang().getString("Reload")));
                    } else {
                        p.sendMessage(Misc.colorize(Configuration.getLang().getString("No Permission")));
                    }
                } else {
                    Bukkit.getLogger().info("[EliteGod] Reloading Config...");
                    Configuration.reloadConfig();
                    Bukkit.getLogger().info("[EliteGod] Config Reloaded...");
                }
            } else if (args[0].equalsIgnoreCase("help")) {
                if (sender instanceof Player p) {
                    p.sendMessage(Misc.colorize("\n&e&lElite&6&lGod &7&l| &7List of commands\n  &a/elitegod &o[help, reload] &7- Main plugin command\n  &a/god &o[player] &7- Toggle God mode on/off for you or another specified player\n "));
                } else {
                    Bukkit.getLogger().info(Misc.colorize("\nEliteGod | List of commands\n  /elitegod [help, reload] - Main plugin command\n  /god [player] - Toggle God mode on/off for you or another specified player\n"));
                }
            } else if (sender instanceof Player p) {
                p.sendMessage(Misc.colorize("&e&lElite&6&lGod &7&l| &cInvalid argument provided"));
            } else {
                Bukkit.getLogger().info("[EliteGod] Invalid argument provided");
            }
        }

        return true;
    }
}
