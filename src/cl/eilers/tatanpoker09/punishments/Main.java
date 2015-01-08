package cl.eilers.tatanpoker09.punishments;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import cl.eilers.tatanpoker09.punishments.commands.DebugPunishmentsCommand;
import cl.eilers.tatanpoker09.punishments.commands.PKickCommand;
import cl.eilers.tatanpoker09.punishments.commands.PWarnCommand;
import cl.eilers.tatanpoker09.punishments.punishments.Punishment;
import cl.eilers.tatanpoker09.punishments.punishments.Warn;

public class Main extends JavaPlugin implements Listener{
	private static List<String> savedWarnList = new ArrayList<String>();
	private static List<Punishment> punishments = new ArrayList<Punishment>();
	private static JavaPlugin plugin;
	public void onEnable(){
		setPlugin(this);
		getCommand("pkick").setExecutor(new PKickCommand());
		getCommand("pwarn").setExecutor(new PWarnCommand());
		getCommand("debugp").setExecutor(new DebugPunishmentsCommand());
		savedWarnList = getConfig().getStringList("punishments.warns");
		Bukkit.getPluginManager().registerEvents(this, this);
		saveConfig();
	}
	public void onDisable(){
			for(Punishment punishment : getPunishments()){
				System.out.println("Saving punishment!");
				switch(punishment.getType()){
				case BAN:
					break;
				case KICK:
					break;
				case WARN:
					Warn.warnToString((Warn)punishment);
					break;
				default:
					break;
				
				}
			}
			saveConfig();
		System.out.println("SAVED");
	}

	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent event){

	}
	public static List<Punishment> getPunishments() {
		return punishments;
	}
	public static void addPunishments(Punishment punishments) {
		System.out.println(Main.getPunishments().size());
		System.out.println("ADDING PUNISHMENT");
		Main.punishments.add(punishments);
		System.out.println(Main.getPunishments().size());
	}
	public static JavaPlugin getPlugin() {
		return plugin;
	}
	public static void setPlugin(JavaPlugin plugin) {
		Main.plugin = plugin;
	}
	public static List<String> getSavedWarnList() {
		return savedWarnList;
	}
	public static void setSavedWarnList(List<String> savedWarnList) {
		Main.savedWarnList = savedWarnList;
	}
}
