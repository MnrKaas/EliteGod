package nl.mnrkaas.elitegod.util;

import nl.mnrkaas.elitegod.EliteGod;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateListener {
    private final EliteGod plugin;

    private final int resourceId;

    public UpdateListener(EliteGod plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getLatestVersion(Consumer<String> consumer) {
        Bukkit.getLogger().info("[EliteGod] Checking For Updates...");
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)this.plugin, () -> {
            try {
                InputStream inputStream = (new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId)).openStream();
                try {
                    Scanner scanner = new Scanner(inputStream);
                    try {
                        if (scanner.hasNext())
                            consumer.accept(scanner.next());
                        scanner.close();
                    } catch (Throwable throwable) {
                        try {
                            scanner.close();
                        } catch (Throwable throwable1) {
                            throwable.addSuppressed(throwable1);
                        }
                        throw throwable;
                    }
                    if (inputStream != null)
                        inputStream.close();
                } catch (Throwable throwable) {
                    if (inputStream != null)
                        try {
                            inputStream.close();
                        } catch (Throwable throwable1) {
                            throwable.addSuppressed(throwable1);
                        }
                    throw throwable;
                }
            } catch (IOException exception) {
                Bukkit.getLogger().warning("[EliteSpawn] Something went wrong while checking for an update." + exception.getMessage());
            }
        });
    }
}
