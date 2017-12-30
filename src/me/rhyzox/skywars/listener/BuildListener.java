package me.rhyzox.skywars.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import me.rhyzox.skywars.Manager;
import me.rhyzox.skywars.methods.chest.ChestItem;
import me.rhyzox.skywars.utils.Var;

public class BuildListener implements Listener {

	@EventHandler
	public void on(BlockPlaceEvent e) {
		if (!Var.canBuild) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getBlock().getType().equals(Material.CHEST)) {
			Manager.getManager().getChestManager().fakeChests.add(e.getBlock().getLocation());
		}
	}

	@EventHandler
	public void on(BlockBreakEvent e) {
		if (!Var.canBuild) {
			e.setCancelled(true);
			return;
		}
		if(e.getBlock().getType().equals(Material.CHEST)) {
			if(!Manager.getManager().getChestManager().fakeChests.contains(e.getBlock().getLocation())) {
				for(ChestItem chestItems : Manager.getManager().getChestManager().IsAlreadyOpen(e.getBlock().getLocation()).getItems()) {
					ItemStack is = chestItems.getItem();
					e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), is);
				}
				Manager.getManager().getChestManager().fakeChests.add(e.getBlock().getLocation());
			}
		}
	}

	@EventHandler
	public void on(PlayerDropItemEvent e) {
		if (!Var.canBuild) {
			e.setCancelled(true);
		}
		if(Var.spectators.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void on(PlayerPickupItemEvent e) {
		if (!Var.canBuild) {
			e.setCancelled(true);
		}
		if(Var.spectators.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
}
