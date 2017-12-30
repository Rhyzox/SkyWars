package me.rhyzox.skywars.utils;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Var {

	/* Strings */
	public static String Prefix = "§aSkyWars §8| §r";
	public static String noperm = Prefix + "§cDazu hast du keine Berechtigung!";

	/* Integer */
	public static int Min_Players = 2;
	public static int Max_Players = 8;
	
	/* Boolean */
	public static boolean canAttack = false;
	public static boolean canMove = true;
	public static boolean canBuild = false;

	/* Files */
	public static File spawns = new File("plugins//SkyWars//spawns.yml");
	public static YamlConfiguration spawnscfg = YamlConfiguration.loadConfiguration(spawns);

	/* Maps & Lists */
	public static ArrayList<Player> players = new ArrayList<>();
	public static ArrayList<Player> spectators = new ArrayList<>();
	
}
