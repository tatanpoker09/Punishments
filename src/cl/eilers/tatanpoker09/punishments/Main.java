package cl.eilers.tatanpoker09.punishments;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import cl.eilers.tatanpoker09.punishments.commands.PBanCommand;
import cl.eilers.tatanpoker09.punishments.commands.PGetCommand;
import cl.eilers.tatanpoker09.punishments.commands.PKickCommand;
import cl.eilers.tatanpoker09.punishments.commands.PMuteCommand;
import cl.eilers.tatanpoker09.punishments.commands.PRemoveCommand;
import cl.eilers.tatanpoker09.punishments.commands.PWarnCommand;
import cl.eilers.tatanpoker09.punishments.listeners.PlayerListener;
import cl.eilers.tatanpoker09.punishments.punishments.Ban;
import cl.eilers.tatanpoker09.punishments.punishments.Kick;
import cl.eilers.tatanpoker09.punishments.punishments.Mute;
import cl.eilers.tatanpoker09.punishments.punishments.Punishment;
import cl.eilers.tatanpoker09.punishments.punishments.Warn;

public class Main extends JavaPlugin{
	private static List<String> savedWarnList = new ArrayList<String>();
	private static List<String> savedKickList = new ArrayList<String>();
	private static List<String> savedBanList = new ArrayList<String>();
	private static List<String> savedMuteList = new ArrayList<String>();
	private static List<Punishment> punishments = new ArrayList<Punishment>();
	private static JavaPlugin plugin;
	public void onEnable(){
		setPlugin(this);
		getCommand("pkick").setExecutor(new PKickCommand());
		getCommand("pwarn").setExecutor(new PWarnCommand());
		getCommand("pget").setExecutor(new PGetCommand());
		getCommand("pban").setExecutor(new PBanCommand());
		getCommand("pmute").setExecutor(new PMuteCommand());
		getCommand("premove").setExecutor(new PRemoveCommand());
		savedWarnList = getConfig().getStringList("punishments.warns");
		savedKickList = getConfig().getStringList("punishments.kicks");
		savedBanList = getConfig().getStringList("punishments.bans");
		savedMuteList = getConfig().getStringList("punishments.mutes");
		Punishment.loadPunishments();
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		savedBanList.clear();
		savedKickList.clear();
		savedWarnList.clear();
		savedMuteList.clear();
		saveConfig();
	}
	public void onDisable(){
		Main.getPlugin().getConfig().set("punishments.bans", "");
		Main.getPlugin().getConfig().set("punishments.mutes", "");
		Main.getPlugin().getConfig().set("punishments.kicks", "");
		Main.getPlugin().getConfig().set("punishments.warns", "");
		Main.getPlugin().saveConfig();
			for(Punishment punishment : getPunishments()){
				switch(punishment.getType()){
				case BAN:
					Ban.banToString((Ban)punishment);
					break;
				case KICK:
					Kick.kickToString((Kick)punishment);
					break;
				case WARN:
					Warn.warnToString((Warn)punishment);
					break;
				case MUTE:
					Mute.muteToString((Mute)punishment);
					break;
				default:
					break;
				
				}
			}
			saveConfig();
	}
	public static List<Punishment> getPunishments() {
		return punishments;
	}
	public static void addPunishments(Punishment punishments) {
		Main.punishments.add(punishments);
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
	public static List<String> getSavedKickList() {
		return savedKickList;
	}
	public static void setSavedKickList(List<String> savedKickList) {
		Main.savedKickList = savedKickList;
	}
	public static List<String> getSavedBanList() {
		return savedBanList ;
	}
	public static List<String> getSavedMuteList() {
		return savedMuteList;
	}
	public static void setSavedMuteList(List<String> savedMuteList) {
		Main.savedMuteList = savedMuteList;
	}
}
