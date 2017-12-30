package me.rhyzox.skywars.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.rhyzox.skywars.Manager;
import me.rhyzox.skywars.countdowns.LobbyCountdown;
import me.rhyzox.skywars.methods.GameState;
import me.rhyzox.skywars.utils.Var;

public class StartCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		Player p = (Player) s;
		if (cmd.getName().equalsIgnoreCase("start")) {
			if (p.hasPermission("sw.start")) {
				if (args.length == 0) {
					if (Manager.gs == GameState.Lobby) {
						if (LobbyCountdown.isRunning) {
							if (LobbyCountdown.getSeconds() > 6) {
								LobbyCountdown.setSeconds(6);
								p.sendMessage(Var.Prefix + "§7Der Countdown wurde auf 6 Sekunden gesetzt!");
							} else {
								p.sendMessage(Var.Prefix + "§cDer Countdown ist bereits unter 6 Sekunden");
							}
						} else {
							p.sendMessage(Var.Prefix + "§cDer Countdown wurde noch nicht gestartet!");
						}
					}
				} else {
					p.sendMessage(Var.Prefix + "§7Bitte nutze: §c/start");
				}
			}
		}
		return false;
	}

}
