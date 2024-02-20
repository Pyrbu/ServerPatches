package summiner.serverpatches.utils;

import summiner.serverpatches.Main;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {

    StringFormatter stringFormatter = new StringFormatter();
    Plugin plugin = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String[] strings) {
        if(!sender.hasPermission("*")) {
            sender.sendMessage(stringFormatter.formatColor(Main.config.getString("Misc.no_permission")));
            return true;
        }
        if(strings.length==0||!strings[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(stringFormatter.formatColor("&7Please try with &d/spatches reload"));
            return true;
        }
        plugin.reloadConfig();
        Main.config = plugin.getConfig();
        Main.manager.reload();
        sender.sendMessage(stringFormatter.formatColor(Main.config.getString("Misc.config_reloaded")));
        return true;
    }

}
