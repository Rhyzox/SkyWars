package me.rhyzox.skywars.countdowns;

import org.bukkit.Bukkit;

import me.rhyzox.skywars.Manager;
import me.rhyzox.skywars.utils.Var;

public class SafePhaseCountdown {

	public static int sec = 30;
	public static int taskid;

	public static void startCounter() {

		taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Manager.getManager(), new Runnable() {

			@Override
			public void run() {
				sec--;

				switch (sec) {
				case 30:
				case 20:
				case 10:
				case 3:
				case 2:
					Bukkit.broadcastMessage(Var.Prefix + "§7Die Aufbau Phase endet in §6" + sec + " §7Sekunden");
					break;

				case 1:
					Bukkit.broadcastMessage(Var.Prefix + "§7Die Aufbau Phase endet in §6einer §7Sekunde");
					break;
					
				case 0:
					Bukkit.broadcastMessage(Var.Prefix + "§6Die Aufbau Phase ist beendet, der Kampf kann beginnen!");
					Var.canMove = true;
					Var.canBuild = true;
					Var.canAttack = true;
					break;
				}

			}
		}, 20, 20);
	}

}
