package me.rhyzox.skywars.listener;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.rhyzox.skywars.Manager;
import me.rhyzox.skywars.methods.GameState;
import me.rhyzox.skywars.methods.Kits.KitManager;

public class InteractListener implements Listener {

	@EventHandler
	public void on(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		try {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (Manager.gs == GameState.InGame) {
				if (e.getClickedBlock().getType() == Material.CHEST) {
					if (!Manager.getManager().getChestManager().fakeChests
							.contains(e.getClickedBlock().getLocation())) {
						e.setCancelled(true);
						Chest chest = (Chest) e.getClickedBlock().getState();
						chest.getBlockInventory().setContents(Manager.getManager().getChestManager()
								.IsAlreadyOpen(e.getClickedBlock().getLocation()).getInv().getContents());
						e.getPlayer().openInventory(chest.getInventory());
						Manager.getManager().getChestManager().fakeChests.add(e.getClickedBlock().getLocation());
					}
				}
			}
		} else if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (Manager.gs == GameState.Lobby) {
				if (e.getItem().getItemMeta().getDisplayName().equals("§7Kits")) {
					KitManager.openGui(p);
				}
			}
		}
	}catch (Exception e1) {
	}
		
	}

}
