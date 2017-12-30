package me.rhyzox.skywars.methods.Kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class Kit {

	private String name;
	private String description;
	private Material item;
	private List<ItemStack> startItems;

	public Kit(String name, String description, Material item, List<ItemStack> startItems) {
		this.name = name;
		this.description = description;
		this.item = item;
		this.startItems = startItems;
	}

	public ItemStack getMenuItem(){
		ItemStack item = new ItemStack(this.item);
		ItemMeta imeta = item.getItemMeta();
		imeta.setDisplayName(name);
		
		String[] split = description.split("//");
		ArrayList<String> lore = new ArrayList<>();
		for(String lorePart : split) {
			lore.add(lorePart);
		}
		imeta.setLore(lore);
		item.setItemMeta(imeta);
		return item;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Material getItem() {
		return item;
	}

	public void setItem(Material item) {
		this.item = item;
	}

	public List<ItemStack> getStartItems() {
		return startItems;
	}

	public void setStartItems(List<ItemStack> startItems) {
		this.startItems = startItems;
	}

}
