package me.rhyzox.skywars.methods.chest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;

public class ChestManager {

	public List<Location> fakeChests;
	
	private Map<Location, Chest> chests;
	private int minItems;
	private int maxItems;
	private ArrayList<ChestItem> items;
	
	public ChestManager(int minItems, int maxItems, ArrayList<ChestItem> items) {
		
		fakeChests = new ArrayList<>();
		
		this.chests = new HashMap<>();
		this.minItems = minItems;
		this.maxItems = maxItems;
		this.items = items;
	}
	
	public Chest IsAlreadyOpen(Location loc) {
		if(chests.containsKey(loc)) {
			return chests.get(loc);
		}
		return createChest(loc);
	}

	private Chest createChest(Location loc) {
		Chest chest = new Chest(minItems, maxItems, items);
		this.chests.put(loc, chest);
		return chest;
	}
	
	public Map<Location, Chest> getChests() {
		return chests;
	}

	public int getMinItems() {
		return minItems;
	}

	public int getMaxItems() {
		return maxItems;
	}

	public ArrayList<ChestItem> getItems() {
		return items;
	}
	
	
}
