package me.rhyzox.skywars.methods.Kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.rhyzox.skywars.methods.ScoreboardMethods;
import me.rhyzox.skywars.utils.Var;

public class KitManager implements Listener {

	private static ArrayList<Kit> kits;
	private static HashMap<Player, Kit> kitConnection;

	public KitManager() {
		kits = new ArrayList<>();
		kitConnection = new HashMap<>();
		
		kits.add(new KitStandart("Standart Kit", "§7Das Standart Kit//", Material.IRON_PICKAXE, getStandartItems()));
		kits.add(new KitBeispiel("Beispiel Kit", "§7Das Beispiel Kit//", Material.ENDER_PEARL, getBeispielItems()));
	}

	@EventHandler
	public void on(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getClickedInventory() == null || e.getCurrentItem() == null)
			return;

		if (e.getClickedInventory().getName().equals("§7Wähle ein Kit!")) {
			e.setCancelled(true);
			for (Kit kit : kits) {
				String name = kit.getName();
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(name)) {
					kitConnection.put(p, kit);
					p.sendMessage(Var.Prefix + "§7Du hast das Kit §6" + name + " §7ausgewählt");
					p.closeInventory();
					ScoreboardMethods.setLobbyBoard(p);
					break;
				}
			}
		}
	}

	public static void openGui(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 3, "§7Wähle ein Kit!");
		for (int i = 0; i < kits.size(); i++)
			inv.setItem(i, kits.get(i).getMenuItem());
		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1, 1);
	}

	public static ArrayList<Kit> getKits() {
		return kits;
	}

	public static Kit getKitFromPlayer(Player p) {
		return kitConnection.containsKey(p) ? kitConnection.get(p) : null;
	}
	
	public static void addKitPlayer(Player p, Kit kit) {
		kitConnection.put(p, kit); 
	}
	
	
	private ArrayList<ItemStack> getStandartItems(){
		ArrayList<ItemStack> items = new ArrayList<>();
		items.add(new ItemStack(Material.IRON_PICKAXE));
		items.add(new ItemStack(Material.IRON_AXE));
		items.add(new ItemStack(Material.IRON_SPADE));
		return null;
	}
	
	private ArrayList<ItemStack> getBeispielItems(){
		ArrayList<ItemStack> items = new ArrayList<>();
		items.add(new ItemStack(Material.ENDER_PEARL));
		return null;
	}

}
