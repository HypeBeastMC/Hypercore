/*
 * MIT License
 *
 * Copyright (c) 2019 HypeBeastMC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

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
