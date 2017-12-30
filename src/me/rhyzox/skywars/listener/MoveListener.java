package me.rhyzox.skywars.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.rhyzox.skywars.utils.Var;

public class MoveListener implements Listener {

	@EventHandler
	public void on(PlayerMoveEvent e) {
		if (!Var.canMove) {
			if (e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getFrom().getZ()) {
				e.getPlayer().teleport(e.getFrom());
			}
		}
	}

}
