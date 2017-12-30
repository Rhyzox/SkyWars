package me.rhyzox.skywars.methods.chest;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Chest {

	private final String invName = "§7Chest";
	private static Random rnd;

	private int minItemsInInv;
	private int maxItemsInInv;
	private ArrayList<ChestItem> items = new ArrayList<ChestItem>();
	private Inventory inv;

	public Chest(int minItemsInInv, int maxItemsInInv, ArrayList<ChestItem> items) {
		this.inv = Bukkit.createInventory(null, 9 * 3, this.invName);
		this.minItemsInInv = minItemsInInv;
		this.maxItemsInInv = maxItemsInInv;
		this.items = items;

		createInventory();
	}

	static {
		rnd = new Random();
	}

	private void createInventory() {
		int itemAmount = Chest.rnd.nextInt(this.maxItemsInInv - this.minItemsInInv + 1) + this.minItemsInInv;

		for (int i = 0; i < itemAmount; i++) {
			int slot = getSlot(inv);

			ChestItem item = null;
			int ticket = rnd.nextInt(ChestItem.getCurrentTicket() - 1 + 1) + 1;
			for (ChestItem current : items) {
				if (ticket <= current.getMaxTicket() && ticket >= current.getMinTicket()) {
					item = current;
				}
			}

			int ItemInInvAmount = Chest.rnd.nextInt(item.getMax() - item.getMin() + 1) + item.getMin();
			ItemStack is = item.getItem();
			is.setAmount(ItemInInvAmount);
			inv.setItem(slot, is);
		}
	}

	private int getSlot(Inventory inv) {
		int slot = Chest.rnd.nextInt(inv.getSize());

		if (inv.getContents()[slot] == null || inv.getContents()[slot].getType() == Material.AIR) {
			return slot;
		}
		return getSlot(inv);
	}

	public Inventory getInv() {
		return inv;
	}

	public int getMinItemsInInv() {
		return minItemsInInv;
	}

	public int getMaxItemsInInv() {
		return maxItemsInInv;
	}

	public ArrayList<ChestItem> getItems() {
		return items;
	}

}
