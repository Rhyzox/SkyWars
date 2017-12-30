package me.rhyzox.skywars.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.rhyzox.skywars.Manager;
import me.rhyzox.skywars.countdowns.LobbyCountdown;
import me.rhyzox.skywars.methods.Factory;
import me.rhyzox.skywars.methods.GameState;
import me.rhyzox.skywars.methods.ScoreboardMethods;
import me.rhyzox.skywars.utils.Items;
import me.rhyzox.skywars.utils.Var;

public class JoinListener implements Listener {

	@EventHandler
	public void on(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		if (Manager.gs == GameState.Lobby) {
			e.setJoinMessage(Var.Prefix + "§6" + p.getName() + " §7hat das Spiel betreten");
			Var.players.add(p);
			Items.setJoinItems(p);
			Bukkit.getOnlinePlayers().forEach(all -> {
				ScoreboardMethods.setLobbyBoard(all);
			});
			p.teleport(Factory.getSpawn("Spawn.Lobby", Var.spawnscfg));
			if (Var.players.size() >= Var.Min_Players) {
				if (!LobbyCountdown.isRunning) {
					LobbyCountdown.startCounter();
				}
			}

		} else if (Manager.gs == GameState.InGame) {
			e.setJoinMessage(null);
			Var.spectators.add(p);
			Var.players.remove(p);
			ScoreboardMethods.setInGameBoard(p);
			p.teleport(Factory.getSpawn("Spawn.Spectator", Var.spawnscfg));
			for (Player all : Var.players) {
				all.hidePlayer(p);
				p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
			}
		} else if (Manager.gs == GameState.End) {
			e.setJoinMessage(null);
			Var.spectators.add(p);
			Var.players.remove(p);
			ScoreboardMethods.setInGameBoard(p);
			p.teleport(Factory.getSpawn("Spawn.Spectator", Var.spawnscfg));
			for (Player all : Var.players) {
				all.hidePlayer(p);
				p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
			}
		}

	}

}
