package com.hypebeastmc.hypercore.commands.api;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {

    public abstract boolean onSubCommand(CommandSender sender, Command command, String name, String[] args);

}
