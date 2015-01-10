package cl.eilers.tatanpoker09.punishments.punishments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bukkit.OfflinePlayer;

import cl.eilers.tatanpoker09.punishments.Main;

public class Mute extends Punishment{
	private static List<OfflinePlayer> mutedPlayers = new ArrayList<OfflinePlayer>();
	private boolean isActive;
	public Mute(OfflinePlayer player,String reason, Calendar date){
		super(reason, PunishmentType.MUTE, player, date);
		isActive = true;
	}
	public Mute(OfflinePlayer player, String reason, Calendar date, String ip){
		super(reason, PunishmentType.MUTE, player, date, ip);
		isActive = true;
	}
	public Punishment addPlayer(){
		if(!mutedPlayers.contains(this.getPlayer())){
			mutedPlayers.add(this.getPlayer());
		}
		return this;
	}
	public static List<OfflinePlayer> getMutedPlayers() {
		return mutedPlayers;
	}
	public static void setMutedPlayers(List<OfflinePlayer> mutedPlayers) {
		Mute.mutedPlayers = mutedPlayers;
	}
	public static void muteToString(Mute mute) {
		String ip = "";
		if(mute.getIp()!=null){
			ip = ","+mute.getIp();
		}
		String muteToAdd = mute.getDate().get(Calendar.YEAR)+","+mute.getDate().get(Calendar.MONTH)+","+mute.getDate().get(Calendar.DAY_OF_MONTH)+","+mute.getPlayer().getName()+","+mute.getReason()+ip+","+mute.isActive();
		Main.getSavedBanList().add(muteToAdd);
		Main.getPlugin().getConfig().set("punishments.mutes", Main.getSavedBanList());
		Main.getPlugin().saveConfig();
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
		if(!isActive){
			if(Mute.getMutedPlayers().contains(this.getPlayer())){
				Mute.getMutedPlayers().remove(this.getPlayer());
			}
		}
	}
}
