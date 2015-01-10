package cl.eilers.tatanpoker09.punishments.punishments;

import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import cl.eilers.tatanpoker09.punishments.Main;

public class Punishment {
	private PunishmentType type;
	private String reason;
	private OfflinePlayer player;
	private Calendar date;
	private String ip;

	public Punishment(String reason, PunishmentType type, OfflinePlayer player, Calendar date, String ip){
		this.setType(type);
		this.setReason(reason);
		this.setPlayer(player);
		this.setDate(date);
		this.ip = ip;
	}
	public Punishment(String reason, PunishmentType type, OfflinePlayer player, Calendar date){
		this.setType(type);
		this.setReason(reason);
		this.setPlayer(player);
		this.setDate(date);
	}

	public Punishment add(){
		Main.addPunishments(this);
		return this;
	}

	public PunishmentType getType() {
		return type;
	}

	public void setType(PunishmentType type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public OfflinePlayer getPlayer() {
		return player;
	}

	public void setPlayer(OfflinePlayer player) {
		this.player = player;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	@SuppressWarnings("deprecation")
	public static void loadPunishments() {
		for(String warn : Main.getSavedWarnList()){
			String[] warnDetails = warn.split(",");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(warnDetails[0]), Integer.parseInt(warnDetails[1]), Integer.parseInt(warnDetails[2]));
			if(warnDetails.length==6){
				new Warn(Bukkit.getOfflinePlayer(warnDetails[3]),warnDetails[4],calendar, warnDetails[5]).add();;
			} else {
				new Warn(Bukkit.getOfflinePlayer(warnDetails[3]),warnDetails[4],calendar).add();;
			}
		}
		for(String kick : Main.getSavedKickList()){
			String[] kickDetails = kick.split(",");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(kickDetails[0]), Integer.parseInt(kickDetails[1]), Integer.parseInt(kickDetails[2]));
			if(kickDetails.length==6){
				new Kick(Bukkit.getOfflinePlayer(kickDetails[3]), kickDetails[4], calendar, kickDetails[5]).add();
			} else {
				new Kick(Bukkit.getOfflinePlayer(kickDetails[3]),kickDetails[4],calendar).add();
			}
		}
		for(String ban : Main.getSavedBanList()){
			String[] banDetails = ban.split(",");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(banDetails[0]), Integer.parseInt(banDetails[1]), Integer.parseInt(banDetails[2]));
			String ip = banDetails[6];
			if(banDetails.length==7){
				new Ban(Bukkit.getOfflinePlayer(banDetails[3]),banDetails[4],calendar, Integer.parseInt(banDetails[5]), ip).add();;
			} else {
				new Ban(Bukkit.getOfflinePlayer(banDetails[3]), banDetails[4], calendar, Integer.parseInt(banDetails[5])).add();
			}
		}
		for(String mute : Main.getSavedMuteList()){
			String[] muteDetails = mute.split(",");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(muteDetails[0]), Integer.parseInt(muteDetails[1]), Integer.parseInt(muteDetails[2]));
			if(muteDetails.length==7){
				if(Boolean.parseBoolean(muteDetails[6])){
					if(!Mute.getMutedPlayers().contains(Bukkit.getOfflinePlayer(muteDetails[3]))){
						Mute.getMutedPlayers().add(Bukkit.getOfflinePlayer(muteDetails[3]));
					}
				}
				((Mute) ((Mute) new Mute(Bukkit.getOfflinePlayer(muteDetails[3]), muteDetails[4], calendar, muteDetails[5]).add()).addPlayer()).setActive(Boolean.parseBoolean(muteDetails[6]));
			} else {
				if(Boolean.parseBoolean(muteDetails[5])){
					if(!Mute.getMutedPlayers().contains(Bukkit.getOfflinePlayer(muteDetails[3]))){
						Mute.getMutedPlayers().add(Bukkit.getOfflinePlayer(muteDetails[3]));
					}
				}
				((Mute) ((Mute) new Mute(Bukkit.getOfflinePlayer(muteDetails[3]), muteDetails[4], calendar).add()).addPlayer()).setActive(Boolean.parseBoolean(muteDetails[5]));
			}
		}
	}
	public String getIp(){
		return ip;
	}
}
