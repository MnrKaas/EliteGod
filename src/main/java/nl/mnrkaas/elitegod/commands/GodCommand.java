package nl.mnrkaas.elitegod.commands;

import nl.mnrkaas.elitegod.util.Configuration;
import nl.mnrkaas.elitegod.util.Misc;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player p) {
            if (!p.hasPermission("elitegod.toggle")){
                p.sendMessage(Misc.colorize(Configuration.getLang().getString("No Permission")));
            }
            if (p.isInvulnerable()) {
                p.setInvulnerable(false);
                p.sendMessage(Misc.colorize(Configuration.getLang().getString("God Disabled")));
            }else{
                p.setInvulnerable(true);
                p.sendMessage(Misc.colorize(Configuration.getLang().getString("God Enabled")));
            }

        }

        return true;
    }
}
