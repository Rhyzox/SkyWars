package me.rhyzox.skywars.methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import me.rhyzox.skywars.methods.Kits.KitManager;
import me.rhyzox.skywars.utils.Var;

public class ScoreboardMethods {

	public static void setLobbyBoard(Player p) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.getObjective("aaa") != null ? board.getObjective("aaa")
				: board.registerNewObjective("aaa", "dummy");

		/* Tablist */
		Team admin = board.registerNewTeam("001A");
		Team dev = board.registerNewTeam("002D");
		Team mod = board.registerNewTeam("003M");
		Team builder = board.registerNewTeam("004B");
		Team supporter = board.registerNewTeam("005S");
		Team youtuber = board.registerNewTeam("006YT");
		Team premium = board.registerNewTeam("007Premium");
		Team spieler = board.registerNewTeam("008Spieler");

		admin.setPrefix("§4Admin §8┃ §4");
		mod.setPrefix("§cMod §8┃§c");
		dev.setPrefix("§dDev §8┃ §d");
		builder.setPrefix("§aBuilder §8┃ §a");
		supporter.setPrefix("§eSup §8┃ §e");
		youtuber.setPrefix("§5");
		premium.setPrefix("§6");
		spieler.setPrefix("§7");

		Bukkit.getOnlinePlayers().forEach(all -> {
			if (all.hasPermission("sw.admin")) {
				admin.addEntry(p.getName());
			} else if (all.hasPermission("sw.dev")) {
				dev.addEntry(p.getName());
			} else if (all.hasPermission("sw.mod")) {
				mod.addEntry(p.getName());
			} else if (all.hasPermission("sw.builder")) {
				builder.addEntry(p.getName());
			} else if (all.hasPermission("sw.supporter")) {
				supporter.addEntry(p.getName());
			} else if (all.hasPermission("sw.youtuber")) {
				youtuber.addEntry(p.getName());
			} else if (all.hasPermission("sw.premium")) {
				premium.addEntry(p.getName());
			} else {
				spieler.addEntry(p.getName());
			}
		});
		p.setScoreboard(board);

		/* Scoreboard-Right */

		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§8» §aSkyWars §8«");
		obj.getScore("§6§5§7§8§9").setScore(12);

		obj.getScore("§d\u00BB Kit").setScore(11);
		if(KitManager.getKitFromPlayer(p) != null) {
			obj.getScore("§8\u27A5 §d" + KitManager.getKitFromPlayer(p).getName()).setScore(10);	
		}else {
			obj.getScore("§8\u27A5 §7Nicht ausgewählt" /* Map */).setScore(10);
		}
		obj.getScore("§6§5§7§8").setScore(9);

		obj.getScore("§b\u29BF Coins").setScore(8);
		obj.getScore("§8\u27A5 §bCoins" /* Coins */).setScore(7);
		obj.getScore("§6§5§7").setScore(6);

		obj.getScore("§a\u2694 Kills").setScore(5);
		obj.getScore("§8\u27A5 §aKills" /* Kills */).setScore(4);
		obj.getScore("§6§5").setScore(3);

		obj.getScore("§c\u2020 Tode").setScore(2);
		obj.getScore("§8\u27A5 §cTode" /* Tode */).setScore(1);
		obj.getScore("§6").setScore(0);

	}

	public static void setInGameBoard(Player p) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.getObjective("aaa") != null ? board.getObjective("aaa")
				: board.registerNewObjective("aaa", "dummy");
		
		/* Tablist */
		Team admin = board.registerNewTeam("001A");
		Team dev = board.registerNewTeam("002D");
		Team mod = board.registerNewTeam("003M");
		Team builder = board.registerNewTeam("004B");
		Team supporter = board.registerNewTeam("005S");
		Team youtuber = board.registerNewTeam("006YT");
		Team premium = board.registerNewTeam("007Premium");
		Team spieler = board.registerNewTeam("008Spieler");

		admin.setPrefix("§4Admin §8┃ §4");
		mod.setPrefix("§cMod §8┃§c");
		dev.setPrefix("§dDev §8┃ §d");
		builder.setPrefix("§aBuilder §8┃ §a");
		supporter.setPrefix("§eSup §8┃ §e");
		youtuber.setPrefix("§5");
		premium.setPrefix("§6");
		spieler.setPrefix("§7");

		Bukkit.getOnlinePlayers().forEach(all -> {
			if (all.hasPermission("sw.admin")) {
				admin.addEntry(p.getName());
			} else if (all.hasPermission("sw.dev")) {
				dev.addEntry(p.getName());
			} else if (all.hasPermission("sw.mod")) {
				mod.addEntry(p.getName());
			} else if (all.hasPermission("sw.builder")) {
				builder.addEntry(p.getName());
			} else if (all.hasPermission("sw.supporter")) {
				supporter.addEntry(p.getName());
			} else if (all.hasPermission("sw.youtuber")) {
				youtuber.addEntry(p.getName());
			} else if (all.hasPermission("sw.premium")) {
				premium.addEntry(p.getName());
			} else {
				spieler.addEntry(p.getName());
			}
		});
		p.setScoreboard(board);

		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§8» §aSkyWars §8«");
		obj.getScore("§6§5§7§8§9").setScore(12);
		
		obj.getScore("§b\u29BF Spieler").setScore(8);
		obj.getScore("§8\u27A5 §b" + Var.players.size()).setScore(7);
		obj.getScore("§6§5§7").setScore(6);

		obj.getScore("§a\u2694 Kills").setScore(5);
		obj.getScore("§8\u27A5 §a").setScore(4);
		obj.getScore("§6§5").setScore(3);
		
		obj.getScore("§d\u00BB Kit").setScore(2);
		obj.getScore("§8\u27A5 §d" + KitManager.getKitFromPlayer(p).getName()).setScore(1);	
		
		obj.getScore("§6§5§7§8").setScore(0);


		p.setScoreboard(board);

	}
}
