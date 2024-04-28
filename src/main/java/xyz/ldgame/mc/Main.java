package xyz.ldgame.mc;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.ldgame.mc.command.RemoveCommand;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginCommand command = getCommand("nodamageimmunity");
        if (command != null) {
            RemoveCommand rc = new RemoveCommand();
            command.setExecutor(rc);
            command.setTabCompleter(rc);
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage("找不到命令配置");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
