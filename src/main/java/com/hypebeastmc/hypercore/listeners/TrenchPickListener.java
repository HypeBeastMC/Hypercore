package com.hypebeastmc.hypercore.listeners;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TrenchPickListener
  implements Listener
{
  public static boolean BlockCheckBreak(Block block, Player player, boolean workInWild)
  {
    FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
    List<Faction> FacsToCheck = new ArrayList();
    Faction playerFac = fPlayer.getFaction();
    Faction wild = Factions.getInstance().getWilderness();
    FacsToCheck.add(playerFac);
    if (workInWild) {
      FacsToCheck.add(wild);
    }
    for (Faction faction : FacsToCheck) {
        FLocation fLocation = new FLocation(block.getLocation());
        boolean factionBuild = false;
        if (Board.getInstance().getFactionAt(fLocation) == faction) {
            if (Board.getInstance().getFactionAt(fLocation).getOwnerList(fLocation) != null) {
                if (Board.getInstance().getFactionAt(fLocation).isPlayerInOwnerList(fPlayer, fLocation)) {
                    factionBuild = true;
                } else {
                    factionBuild = false;
                }
            } else {
                factionBuild = true;
            }
        } else {
            factionBuild = false;
        }

          if ((WGBukkit.getPlugin().canBuild(player, block)) && (factionBuild)) {       return true;
        }
   
    }
    return false;
  }
  
  @EventHandler
  public void onBlockBroken(BlockBreakEvent event)
  {
    Player player = event.getPlayer();
    FPlayer fp = FPlayers.getInstance().getByPlayer(player);
    Faction playersFaction = fp.getFaction();
    Faction fAt = Board.getInstance().getFactionAt(new FLocation(player.getLocation()));
    
    Location loc = event.getBlock().getLocation();
    FLocation floc = new FLocation(loc);
    if ((!event.isCancelled()) && (event.getPlayer().getItemInHand().getItemMeta().getLore() != null)) {
      if (((String)event.getPlayer().getItemInHand().getItemMeta().getLore().get(0)).equalsIgnoreCase("Breaks a 3x3 area."))
      {
        ItemStack stack = new ItemStack(Material.DIAMOND_PICKAXE);
        stack.addUnsafeEnchantment(Enchantment.DIG_SPEED, 255);
        stack.addEnchantment(Enchantment.SILK_TOUCH, 1);
        Bukkit.getWorld("World").createExplosion(loc, 0.0F, false);
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
              if ((!block.isEmpty()) && (BlockCheckBreak(block, player, true))) {
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
