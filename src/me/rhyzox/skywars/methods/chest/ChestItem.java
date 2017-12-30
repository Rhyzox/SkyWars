package me.rhyzox.skywars.methods.chest;

import org.bukkit.inventory.ItemStack;

public class ChestItem {

	private int probability;
	private int min;
	private int max;
	private ItemStack item;
	private int minTicket, maxTicket;
	private static int currentTicket;

	public ChestItem(int probability, int min, int max, ItemStack item) {
		this.probability = probability;
		this.min = min;
		this.max = max;
		this.item = item;
		
		this.minTicket = currentTicket + 1;
		this.maxTicket = minTicket + probability;
		currentTicket = maxTicket;
	}
	
	public static int getCurrentTicket() {
		return currentTicket;
	}
	
	public int getMaxTicket() {
		return maxTicket;
	}
	
	public int getMinTicket() {
		return minTicket;
	}
	
	public int getProbability() {
		return probability;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public ItemStack getItem() {
		return item;
	}
}
