package me.rhyzox.skywars.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import me.rhyzox.skywars.Manager;
import me.rhyzox.skywars.methods.GameState;
import me.rhyzox.skywars.utils.Var;

public class DamageListener implements Listener {

	@EventHandler
	public void on(EntityDamageEvent e) {
		if (Manager.gs == GameState.Lobby) {
			e.setCancelled(true);
		} else if (Manager.gs == GameState.InGame) {
			if (!Var.canAttack) {
				e.setCancelled(true);
			}
		} else if (Manager.gs == GameState.End) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void on(EntityDamageByEntityEvent e) {
		if (Manager.gs == GameState.Lobby) {
			e.setCancelled(true);
		} else if (Manager.gs == GameState.InGame) {
			if (!Var.canAttack) {
				e.setCancelled(true);	
			}
			if(Var.spectators.contains(e.getEntity())) {
				e.setCancelled(true);
			}
		} else if (Manager.gs == GameState.End) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void on(EntityDamageByBlockEvent e) {
		if (Manager.gs == GameState.Lobby) {
			e.setCancelled(true);

		} else if (Manager.gs == GameState.End) {
			e.setCancelled(true);
		}
	}
}
