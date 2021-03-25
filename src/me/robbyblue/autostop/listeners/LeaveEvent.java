package me.robbyblue.autostop.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.robbyblue.autostop.MainClass;

public class LeaveEvent implements Listener {

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		if (Bukkit.getServer().getOnlinePlayers().size() == 1) { // idk why its 1 when no one is online. hope this doesnt cause problems with the server shutting down while one player is on
			MainClass.log("The last player just left. If no one re-joins within 1 minute the server turns off");
			MainClass.startCountdown();
		}
	}

}
