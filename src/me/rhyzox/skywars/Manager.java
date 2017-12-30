package me.rhyzox.skywars;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.rhyzox.skywars.commands.SetSpawnCMD;
import me.rhyzox.skywars.commands.StartCMD;
import me.rhyzox.skywars.listener.BuildListener;
import me.rhyzox.skywars.listener.DamageListener;
import me.rhyzox.skywars.listener.DeathListener;
import me.rhyzox.skywars.listener.InteractListener;
import me.rhyzox.skywars.listener.JoinListener;
import me.rhyzox.skywars.listener.MoveListener;
import me.rhyzox.skywars.listener.QuitListener;
import me.rhyzox.skywars.methods.GameState;
import me.rhyzox.skywars.methods.Kits.KitManager;
import me.rhyzox.skywars.methods.chest.ChestItem;
import me.rhyzox.skywars.methods.chest.ChestManager;
import me.rhyzox.skywars.utils.Var;

public class Manager extends JavaPlugin {

	public static Manager manager;
	private ChestManager chestManager;
	public static GameState gs;
	private KitManager kitManager;

	@Override
	public void onEnable() {
		manager = this;
		gs = GameState.Lobby;
		register();
	}

	@Override
	public void onDisable() {

	}

	@SuppressWarnings("deprecation")
	private void register() {
		loadChestHandler();
		kitManager = new KitManager();
		/* Countdowns */
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				if (gs == GameState.Lobby) {
					if (Var.players.size() < Var.Min_Players) {
						Bukkit.broadcastMessage(Var.Prefix + "§cWarten auf Spieler....");
					}
				}
			}
		}, 20, 20 * 20);

		/* Listener */
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new JoinListener(), this);
		pluginManager.registerEvents(new InteractListener(), this);
		pluginManager.registerEvents(new QuitListener(), this);
		pluginManager.registerEvents(new DamageListener(), this);
		pluginManager.registerEvents(new BuildListener(), this);
		pluginManager.registerEvents(new MoveListener(), this);
		pluginManager.registerEvents(new DeathListener(), this);
		pluginManager.registerEvents(new KitManager(), this);

		/* Commands */
		getCommand("start").setExecutor(new StartCMD());
		getCommand("setspawn").setExecutor(new SetSpawnCMD());

	}

	@SuppressWarnings("deprecation")
	private void loadChestHandler() {
		File file = new File(getDataFolder(), "items.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

		ArrayList<String> defaults = new ArrayList<>();

		defaults.add("1-20-64-80");
		defaults.add("5:1-25-64-60");

		cfg.addDefault("Items", defaults);
		cfg.options().copyDefaults(true);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<ChestItem> chestItems = new ArrayList<>();
		ArrayList<String> items = (ArrayList<String>) cfg.getStringList("Items");
		for (String item : items) {
			String[] split = item.split("-");
			String[] subidsplit = split[0].split(":");
			ItemStack is = null;
			int id = Integer.parseInt(subidsplit[0]);
			if (subidsplit.length > 1) {
				short subID = (short) Integer.parseInt(subidsplit[1]);
				is = new ItemStack(id, 0, subID);
				
			} else {
				is = new ItemStack(id);
			}
			int min = Integer.parseInt(split[1]);
			int max = Integer.parseInt(split[2]);
			int prob = Integer.parseInt(split[3]);
			chestItems.add(new ChestItem(prob, min, max, is));
		}
		chestManager = new ChestManager(3, 11, chestItems);
	}

	public static Manager getManager() {
		return manager;
	}
	
	public ChestManager getChestManager() {
		return chestManager;
	}
	
	public KitManager getKitManager() {
		return kitManager;
	}

}
