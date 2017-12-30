package me.rhyzox.skywars.countdowns;

import org.bukkit.Bukkit;

import me.rhyzox.skywars.Manager;
import me.rhyzox.skywars.utils.Var;

public class TPPhaseCountdown {

	public static int sec = 3;
	public static int taskid;

	public static void startCounter() {

		taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Manager.getManager(), new Runnable() {

			@Override
			public void run() {
				sec--;

				switch (sec) {
				case 3:
				case 2:
					Bukkit.broadcastMessage(Var.Prefix + "§7Die Runde startet in §6" + sec + " §7Sekunden");
					break;

				case 1:
					Bukkit.broadcastMessage(Var.Prefix + "§7Die Runde startet in §6einer §7Sekunde");
					break;

				case 0:
					Var.canMove = true;
					Var.canBuild = true;
					Var.canAttack = false;
					Bukkit.broadcastMessage(Var.Prefix + "§6Die Aufbau Phase hat begonnen");
					SafePhaseCountdown.startCounter();
					break;
				default:
					break;
				}

			}
		}, 20, 20);
	}

}
