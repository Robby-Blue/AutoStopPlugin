package me.robbyblue.autostop;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.robbyblue.autostop.listeners.JoinEvent;
import me.robbyblue.autostop.listeners.LeaveEvent;

public class MainClass extends JavaPlugin {

	static MainClass instance;

	FileConfiguration config = getConfig();
	int minutes;
	int minPlayers;

	BukkitTask countdown;
	boolean isCountingdown;

	@Override
	public void onEnable() {

		config.addDefault("minutes", 1);
		config.addDefault("minimumPlayers", 1);
		config.options().copyDefaults(true);
		saveConfig();

		minutes = config.getInt("minutes");
		minPlayers = config.getInt("minimumPlayers");

		getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
		getServer().getPluginManager().registerEvents(new JoinEvent(), this);

		instance = this;
		log("Atleast " + (minPlayers == 1 ? "one player" : minPlayers + " players") + " has to join within "
				+ (minutes == 1 ? "one minute" : minutes + " minutes") + " or the server shuts down");
		startCountdown();
	}

	public void startCountdown() {
		isCountingdown = true;
		countdown = new BukkitRunnable() {
			public void run() {
				log("Shutting down server because no one is playing");
				Bukkit.getServer().shutdown();
			}
		}.runTaskTimer(getInstance(), minutes * 60 * 20, 0);
	}

	public void stopCountdown() {
		isCountingdown = false;
		countdown.cancel();
	}

	public static MainClass getInstance() {
		return instance;
	}

	public boolean isCountingdown() {
		return isCountingdown;
	}

	public void setCountingdown(boolean is) {
		isCountingdown = is;
	}

	public void log(String message) {
		System.out.println("[AutoStop] " + message);
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public int getMinutes() {
		return minutes;
	}

}
