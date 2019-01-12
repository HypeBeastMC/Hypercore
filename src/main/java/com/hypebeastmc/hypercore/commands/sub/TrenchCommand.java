package com.hypebeastmc.hypercore.commands.sub;

import com.hypebeastmc.hypercore.commands.api.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**sNotNiko, JoeZwet
 * @author It
 */
public class TrenchCommand extends SubCommand {

    public boolean onSubCommand(CommandSender sender, Command command, String name, String[] args) {
        if (sender.hasPermission("hypercore.cmd.trench"))
        {
            ItemStack stack = new ItemStack(Material.DIAMOND_PICKAXE);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("Trench Pick");
            List<String> lore = new ArrayList<String>();
            lore.add("Breaks a 3x3 area.");
            meta.setLore(lore);

            stack.setItemMeta(meta);
            if ((sender instanceof Player)) {
                ((Player)sender).getInventory().addItem(stack);
            } else if (args.length == 2) {
                if (Bukkit.getPlayer(args[1]) != null)
                {
                    Bukkit.getPlayer(args[1]).getInventory().addItem(stack);
                    sender.sendMessage("Added trench pick to the inventory of " + Bukkit.getPlayer(args[1]).getDisplayName());
                }
                else
                {
                    sender.sendMessage("Could not find the player: " + Bukkit.getPlayer(args[1]).getDisplayName());
                }
            }
        }
        else
        {
            sender.sendMessage("Missing permission");
        }
        return true;
    }
}
