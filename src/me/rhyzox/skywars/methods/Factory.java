package me.rhyzox.skywars.methods;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import me.rhyzox.skywars.Manager;
import me.rhyzox.skywars.countdowns.EndCountdown;
import me.rhyzox.skywars.utils.Var;

public class Factory {

	public static void createSpawn(Location loc, String path, File file, YamlConfiguration cfg) {

		cfg.set(path + ".World", loc.getWorld().getName());
		cfg.set(path + ".X", loc.getX());
		cfg.set(path + ".Y", loc.getY());
		cfg.set(path + ".Z", loc.getZ());
		cfg.set(path + ".Yaw", loc.getYaw());
		cfg.set(path + ".Pitch", loc.getPitch());

		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Location getSpawn(String path, YamlConfiguration cfg) {
		String WorldName = cfg.getString(path + ".World");
		double x = cfg.getDouble(path + ".X");
		double y = cfg.getDouble(path + ".Y");
		double z = cfg.getDouble(path + ".Z");
		float yaw = (float) cfg.getDouble(path + ".Yaw");
		float pitch = (float) cfg.getDouble(path + ".Pitch");

		return new Location(Bukkit.getWorld(WorldName), x, y, z, yaw, pitch);
	}

	public static void checkWinning() {
		if (Manager.gs == GameState.InGame) {
			if (Var.players.size() <= 1) {
				Bukkit.broadcastMessage(
						Var.Prefix + "§6" + Var.players.get(0).getName() + " §ahat das Spiel gewonnen!");
				Manager.gs = GameState.End;
				EndCountdown.startCounter();
			}
		}
	}

}
