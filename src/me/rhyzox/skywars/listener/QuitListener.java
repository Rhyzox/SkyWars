package me.rhyzox.skywars.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.rhyzox.skywars.Manager;
import me.rhyzox.skywars.countdowns.LobbyCountdown;
import me.rhyzox.skywars.methods.Factory;
import me.rhyzox.skywars.methods.GameState;
import me.rhyzox.skywars.utils.Var;

public class QuitListener implements Listener {

	@EventHandler
	public void on(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		if (Manager.gs == GameState.Lobby) {
			e.setQuitMessage(Var.Prefix + "§c" + p.getName() + " §7hat das Spiel verlassen");
			Var.players.remove(p);
			if (LobbyCountdown.isRunning) {
				LobbyCountdown.stop();
			}

		} else if (Manager.gs == GameState.InGame) {
			e.setQuitMessage(null);
			if (Var.players.contains(p)) {
				Var.players.remove(p);
			} else {
				Var.spectators.remove(p);
			}
			Factory.checkWinning();

		} else if (Manager.gs == GameState.End) {
			e.setQuitMessage(null);
			Var.spectators.remove(p);
		}
	}

}
