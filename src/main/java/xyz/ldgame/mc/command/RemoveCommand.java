package xyz.ldgame.mc.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class RemoveCommand implements TabExecutor {
    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label,@Nonnull String[] args) {
        if (args.length > 0) {
            if (sender instanceof Player player && !player.isOp()) {
                sendMessage(sender, "你没有权限进行此操作");
                return false;
            }
            Player victim = Bukkit.getPlayer(args[0]);
            if (victim != null) {
                victim.setMaximumNoDamageTicks(0);
                sendMessage(sender, "设置成功");
            } else {
                sendMessage(sender, "当前玩家不在线");
            }
        } else {
            sendMessage(sender, "/ndi 玩家名字");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label,@Nonnull String[] args) {
        ArrayList<String> onlinePlayer = new ArrayList<>(Bukkit.getOnlinePlayers().stream().map(Player::getName).toList());
        if (!args[0].equals("")) {
            onlinePlayer.removeIf(e -> !e.startsWith(args[0].toLowerCase()));
        }
        return onlinePlayer;
    }

    private void sendMessage(CommandSender sender, String msg) {
        if (sender instanceof Player player) {
            player.sendMessage(msg);
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage(msg.replace("/", ""));
        }
    }
}
