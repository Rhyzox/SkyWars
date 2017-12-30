package me.rhyzox.skywars.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.rhyzox.skywars.methods.Factory;
import me.rhyzox.skywars.methods.ScoreboardMethods;
import me.rhyzox.skywars.utils.Var;

public class DeathListener implements Listener {

	@EventHandler
	public void on(PlayerDeathEvent e) {
		Player o = e.getEntity();
		e.setDeathMessage(null);
		Var.players.remove(o);
		Bukkit.getOnlinePlayers().forEach(all -> {
			ScoreboardMethods.setInGameBoard(all);
		});
		if (o.getKiller() != null) {
			Player k = o.getKiller();
			Bukkit.broadcastMessage(Var.Prefix + "§c" + o.getName() + " §7wurde von §a" + k.getName() + " §7getötet");
			Var.spectators.add(o);
			Factory.checkWinning();
			return;

		}
		Bukkit.broadcastMessage(Var.Prefix + "§c" + o.getName() + " §7ist gestorben");
		Factory.checkWinning();
		Var.spectators.add(o);
		return;
	}

	@EventHandler
	public void on(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if (Var.spectators.contains(p)) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999, 9999));
			e.setRespawnLocation(Factory.getSpawn("Spawn.Spectator", Var.spawnscfg));
			for (Player all : Var.players)
				all.hidePlayer(p);
			p.setAllowFlight(true);
			p.setFlying(true);
		}
	}
}
