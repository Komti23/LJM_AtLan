package com.AtLaN.lJM;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinLeaveMessages extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("ljmreload").setExecutor(this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration config = getConfig();
        String joinMessage = ChatColor.translateAlternateColorCodes('&', config.getString("join_message", "&aДобро пожаловать, %player%!"));
        event.setJoinMessage(joinMessage.replace("%player%", event.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        FileConfiguration config = getConfig();
        String quitMessage = ChatColor.translateAlternateColorCodes('&', config.getString("quit_message", "&c%player% вышел с сервера."));
        event.setQuitMessage(quitMessage.replace("%player%", event.getPlayer().getName()));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ljmreload")) {
            reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Конфигурация перезагружена!");
            return true;
        }
        return false;
    }
}
