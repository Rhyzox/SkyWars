package me.rhyzox.skywars.utils;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Items {
	
	public static void setJoinItems(Player p) {
		p.setGameMode(GameMode.SURVIVAL);
		p.setHealth(20);
		p.setFoodLevel(20);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setLevel(0);
		p.setExp(0);
		p.getInventory().setItem(0, new ItemManager(Material.CHEST).setDisplayName("§7Kits").build());
	}

}
