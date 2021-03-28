package me.robbyblue.autostop.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.robbyblue.autostop.MainClass;

public class LeaveEvent implements Listener {

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		MainClass main = MainClass.getInstance();
		int minPlayers = main.getMinPlayers();
		int minutes = main.getMinutes();

		if (Bukkit.getServer().getOnlinePlayers().size() == minPlayers) {
			main.log("The last player just left. If no one re-joins within "
					+ (minutes == 1 ? "one minute" : minutes + " minutes") + " the server will automatically turn off");
			main.startCountdown();
		}
	}

}
