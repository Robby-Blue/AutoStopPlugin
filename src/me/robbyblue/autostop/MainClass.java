package me.robbyblue.autostop;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.robbyblue.autostop.listeners.JoinEvent;
import me.robbyblue.autostop.listeners.LeaveEvent;

public class MainClass extends JavaPlugin {

	static MainClass instance;
	static BukkitTask countdown;
	static boolean isCountingdown;

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
		getServer().getPluginManager().registerEvents(new JoinEvent(), this);

		instance = this;
		log("Atleast one player has to join within 1 minute or the server shuts down");
		startCountdown();
	}

	public static void startCountdown() {
		isCountingdown = true;
		countdown = new BukkitRunnable() {
			public void run() {
				log("Shutting down server because no one is playing");
				Bukkit.getServer().shutdown();
			}
		}.runTaskTimer(getInstance(), 60 * 20, 0); // 60 seconds times 20 ticks per second
	}

	public static void stopCountdown() {
		isCountingdown = false;
		countdown.cancel();
	}

	public static MainClass getInstance() {
		return instance;
	}

	public static boolean isCountingdown() {
		return isCountingdown;
	}

	public static void setCountingdown(boolean is) {
		isCountingdown = is;
	}

	public static void log(String message) {
		System.out.println("[AutoStop] " + message);
	}

}
