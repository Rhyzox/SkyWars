package me.rhyzox.skywars.countdowns;

import org.bukkit.Bukkit;

import me.rhyzox.skywars.Manager;
import me.rhyzox.skywars.utils.Var;

public class EndCountdown {

	public static int sec = 15;
	public static int taskid;

	public static void startCounter() {
		Bukkit.getScheduler().cancelAllTasks();
		
		taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Manager.getManager(), new Runnable() {

			@Override
			public void run() {
				sec--;

				switch (sec) {
				case 15:
				case 10:
				case 5:
				case 4:
				case 3:
				case 2:
					Bukkit.broadcastMessage(Var.Prefix + "§7Der Server startet in §c" + sec + " §7Sekunden neu");
					break;

				case 1:
					Bukkit.broadcastMessage(Var.Prefix + "§7Der Server startet in §ceiner §7Sekunde neu");
					break;

				case 0:
					Bukkit.broadcastMessage(Var.Prefix + "§7Der Server startet §cjetzt §7Sekunde neu");
					Bukkit.getOnlinePlayers().forEach(all ->{all.kickPlayer("§cServer startet neu");});
					break;
				default:
					break;
				}

			}
		}, 20, 20);
	}

	
}
