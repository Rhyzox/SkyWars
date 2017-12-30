package me.rhyzox.skywars.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.rhyzox.skywars.methods.Factory;
import me.rhyzox.skywars.utils.Var;

public class SetSpawnCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
			if (p.hasPermission("sw.setspawn")) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("lobby")) {
						p.sendMessage(Var.Prefix + "§7Der §aLobby §7Spawn Point wurde gesetzt!");
						Factory.createSpawn(p.getLocation(), "Spawn.Lobby", Var.spawns, Var.spawnscfg);

					} else if (args[0].equalsIgnoreCase("spectator")) {
						p.sendMessage(Var.Prefix + "§7Der §aSpectator §7Spawn Point wurde gesetzt!");
						Factory.createSpawn(p.getLocation(), "Spawn.Spectator", Var.spawns, Var.spawnscfg);

					} else if (args[0].equalsIgnoreCase("game")) {
						p.sendMessage(Var.Prefix + "§7Bitte nutze: §c/setspawn game <ID>");
					}

				} else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("game")) {
						try {
							int id = Integer.parseInt(args[1]);
							if (id > 0 && id <= Var.Max_Players) {
								Factory.createSpawn(p.getLocation(), "Spawn.Game." + id, Var.spawns, Var.spawnscfg);
								p.sendMessage(
										Var.Prefix + "§7Der Spawn Point mit der ID §a'" + id + "' §7wurde gesetzt!");
							}
						} catch (Exception e) {
							p.sendMessage(Var.Prefix + "§cBitte gebe eine Zahl an!");
						}

					} else
						p.sendMessage(Var.Prefix + "§7Bitte nutze: §c/setspawn <Lobby | Spectator | Game>");

				} else
					p.sendMessage(Var.Prefix + "§7Bitte nutze: §c/setspawn <Lobby | Spectator | Game>");
			} else
				p.sendMessage(Var.noperm);
		}

		return false;
	}

}
