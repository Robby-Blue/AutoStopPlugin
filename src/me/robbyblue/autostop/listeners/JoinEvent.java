package me.robbyblue.autostop.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.robbyblue.autostop.MainClass;

public class JoinEvent implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		MainClass main = MainClass.getInstance();
		if (main.isCountingdown()) {
			main.log("Countdown stopped because a player joined");
			main.stopCountdown();
		}
	}

}
