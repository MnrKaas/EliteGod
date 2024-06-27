package nl.mnrkaas.elitegod.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Configuration {

    private static File lfile;

    private static FileConfiguration langFile;

    public static void LangSetup(){
        lfile = new File(Bukkit.getServer().getPluginManager().getPlugin("EliteGod").getDataFolder(), "language.yml");
        if (!lfile.exists()) {
            Bukkit.getLogger().info("[EliteGod] No File Found, Attempting to Create... (language.yml)");
            try {
                lfile.createNewFile();

            } catch (IOException e) {
                Bukkit.getLogger().warning("[EliteGod] Something went wrong while creating the language file 'language.yml'\n" + e);
                return;
            }
            Bukkit.getLogger().info("[EliteGod] Configuration File Created (language.yml)");
        }
        langFile = YamlConfiguration.loadConfiguration(lfile);
    }

    public static FileConfiguration getLang(){
        return langFile;
    }

    public static void saveLang() {
        Bukkit.getLogger().info("[EliteGod] Saving Configuration File... (language.yml)");
        try {
            langFile.save(lfile);
        } catch (IOException e) {
            Bukkit.getLogger().warning("[EliteGod] Something went wrong while saving the language file 'language.yml'\n" + e);
        }
        Bukkit.getLogger().info("[EliteGod] Saved Configuration File (language.yml)");
    }

    public static void reloadConfig() {
        langFile = YamlConfiguration.loadConfiguration(lfile);
    }

}
