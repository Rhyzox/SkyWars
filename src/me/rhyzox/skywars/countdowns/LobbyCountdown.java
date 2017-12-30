package me.rhyzox.skywars.countdowns;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.rhyzox.skywars.Manager;
import me.rhyzox.skywars.methods.Factory;
import me.rhyzox.skywars.methods.GameState;
import me.rhyzox.skywars.methods.ScoreboardMethods;
import me.rhyzox.skywars.utils.Var;

public class LobbyCountdown {

	public static int sec = 61;
	public static int taskid;
	public static boolean isRunning = false;

	public static void startCounter() {
		isRunning = true;
		taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Manager.getManager(), new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				sec--;
				Bukkit.getOnlinePlayers().forEach(all -> {
					all.setLevel(sec);
					all.setExp((float) sec / 60);
				});

				switch (sec) {
				case 60:
				case 50:
				case 40:
				case 30:
				case 15:
				case 10:
					Bukkit.getOnlinePlayers().forEach(all -> {
						all.sendMessage(Var.Prefix + "§7Das Spiel beginnt in §6" + sec + " §7Sekunden");
						all.playSound(all.getLocation(), Sound.LAVA_POP, 1, 1);
					});
					break;

				case 5:
					Bukkit.getOnlinePlayers().forEach(all -> {
						all.sendMessage(Var.Prefix + "§7Das Spiel beginnt in §6" + sec + " §7Sekunden");
						all.playSound(all.getLocation(), Sound.LAVA_POP, 1, 1);
						all.sendTitle("§d5", "");
					});
					break;

				case 4:
					Bukkit.getOnlinePlayers().forEach(all -> {
						all.sendMessage(Var.Prefix + "§7Das Spiel beginnt in §6" + sec + " §7Sekunden");
						all.playSound(all.getLocation(), Sound.LAVA_POP, 1, 1);
						all.sendTitle("§b4", "");
					});
					break;

				case 3:
					Bukkit.getOnlinePlayers().forEach(all -> {
						all.sendMessage(Var.Prefix + "§7Das Spiel beginnt in §6" + sec + " §7Sekunden");
						all.playSound(all.getLocation(), Sound.LAVA_POP, 1, 1);
						all.sendTitle("§63", "");
					});
					break;

				case 2:
					Bukkit.getOnlinePlayers().forEach(all -> {
						all.sendMessage(Var.Prefix + "§7Das Spiel beginnt in §6" + sec + " §7Sekunden");
						all.playSound(all.getLocation(), Sound.LAVA_POP, 1, 1);
						all.sendTitle("§12", "");
					});
					break;

				case 1:
					Bukkit.getOnlinePlayers().forEach(all -> {
						all.sendMessage(Var.Prefix + "§7Das Spiel beginnt in §6einer §7Sekunde");
						all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
						all.sendTitle("§a1", "");
					});
					break;

				case 0:
					Bukkit.getScheduler().cancelTask(taskid);
					Manager.gs = GameState.InGame;
					for (int i = 0; i < Var.players.size(); i++) {
						Player p = Var.players.get(i);
						p.teleport(Factory.getSpawn("Spawn.Game." + (i + 1), Var.spawnscfg));
					}
					Bukkit.getScheduler().runTaskLater(Manager.getManager(), new Runnable() {
						
						@Override
						public void run() {
							Var.canMove = false;
							Var.canBuild = false;
							Var.canAttack = false;
							
						}
					}, 5);
					Bukkit.broadcastMessage(Var.Prefix + "§7Alle Spieler werden jetzt teleportiert!");
					TPPhaseCountdown.startCounter();
					Bukkit.getOnlinePlayers().forEach(all -> {
						ScoreboardMethods.setInGameBoard(all);
					});
					break;
				default:
					break;
				}

			}
		}, 20, 20);
	}

	public static int getSeconds() {
		return sec;
	}

	public static void setSeconds(int seconds) {
		sec = seconds;
	}

	public static void stop() {
		Bukkit.getScheduler().cancelTask(taskid);
		isRunning = false;
		System.out.println("Canceled");
		sec = 61;
		Bukkit.getOnlinePlayers().forEach(all -> {
			all.setLevel(0);
			all.setExp(0);
		});
	}

}
