package com.hypebeastmc.hypercore.commands;

import com.hypebeastmc.hypercore.commands.api.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class HyperCoreSubCommandHandler {

    private Map<String, SubCommand> subCommands = new HashMap<String, SubCommand>();


    public void registerSubCommand(String name, SubCommand handler) {
        subCommands.put(name, handler);
    }

    public void execute(CommandSender sender, Command command, String name, String[] args) {
        if(subCommands.containsKey(args[0])) {
            subCommands.get(args[0]).onSubCommand(sender, command, name, args);
        }
    }
}
