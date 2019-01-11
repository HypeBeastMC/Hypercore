
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
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.hypebeastmc.hypercore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.iface.RelationParticipator;
import com.massivecraft.factions.struct.Relation;

/**
 * @author JoeZwet
 */
public class TrenchPickListener implements Listener {

    /* Block Broken Event */
    @EventHandler
    public void onBlockBroken(BlockBreakEvent event)
    {
    	Player player = event.getPlayer();
    	FPlayer fp = FPlayers.getInstance().getByPlayer(player);
        Faction playersFaction = fp.getFaction();
        Faction fAt = Board.getInstance().getFactionAt(new FLocation(player.getLocation()));
  
        
        
        
        if(!FPlayers.getInstance().getByPlayer(player).isInOthersTerritory()) {
        	
        
	
        if ((!event.isCancelled()) &&
                (event.getPlayer().getItemInHand().getItemMeta().getLore() != null) && 
                (((String)event.getPlayer().getItemInHand().getItemMeta().getLore().get(0)).equalsIgnoreCase("Breaks a 3x3 area.")))
        {
            /* Item the server will use to break the blocks */
            ItemStack stack = new ItemStack(Material.DIAMOND_PICKAXE);
            stack.addUnsafeEnchantment(Enchantment.DIG_SPEED, 255);
            stack.addEnchantment(Enchantment.SILK_TOUCH, 1);
            Location loc = event.getBlock().getLocation();
            loc.setY(loc.getBlockY() - 1);
            loc.setX(loc.getBlockX() - 1);
            loc.setZ(loc.getBlockZ() - 1);
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    for (int k = 0; k < 3; k++)
                    {
                        Block block = Bukkit.getWorld("world").getBlockAt(loc);
                        if (!block.isEmpty()) {
                            block.breakNaturally(stack);
                        }
                        loc.setX(loc.getBlockX() + 1);
                    }
                    loc.setZ(loc.getBlockZ() + 1);
                    loc.setX(loc.getBlockX() - 3);
                }
                loc.setY(loc.getBlockY() + 1);
                loc.setZ(loc.getBlockZ() - 3);
            }
    
            	
            }
        }
    }

}
