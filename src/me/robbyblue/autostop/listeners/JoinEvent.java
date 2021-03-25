package me.robbyblue.autostop.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.robbyblue.autostop.MainClass;

public class JoinEvent implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (MainClass.isCountingdown()) {
			MainClass.log("Countdown stopped because a player joined");
			MainClass.stopCountdown();
		}
	}

}
