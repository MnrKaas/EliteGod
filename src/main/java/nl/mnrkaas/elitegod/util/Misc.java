package nl.mnrkaas.elitegod.util;

import org.bukkit.ChatColor;

public class Misc {
    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
