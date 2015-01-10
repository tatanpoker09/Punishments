package cl.eilers.tatanpoker09.punishments.punishments;

import java.util.Calendar;

import org.bukkit.OfflinePlayer;

import cl.eilers.tatanpoker09.punishments.Main;

public class Ban extends Punishment{
	private int dayLength;

	@SuppressWarnings("deprecation")
	public Ban(OfflinePlayer player, String reason, Calendar date, int dayLength, String ip){
		super(reason, PunishmentType.BAN, player, date, ip);
		if(player.getPlayer()!=null){
			player.getPlayer().kickPlayer(reason);
			player.setBanned(true);
		}
		this.setDayLength(dayLength);
	}
	@SuppressWarnings("deprecation")
	public Ban(OfflinePlayer player, String reason, Calendar date, int dayLength){
		super(reason, PunishmentType.BAN, player, date);
		if(player.getPlayer()!=null){
			player.getPlayer().kickPlayer(reason);
			player.setBanned(true);
		}
		this.setDayLength(dayLength);
	}

	public int getDayLength() {
		return dayLength;
	}

	public void setDayLength(int dayLength) {
		this.dayLength = dayLength;
	}

	public static void banToString(Ban ban) {
		String ip = "";
		if(ban.getIp()!=null){
			ip = ","+ban.getIp();
		}
		String banToAdd = ban.getDate().get(Calendar.YEAR)+","+ban.getDate().get(Calendar.MONTH)+","+ban.getDate().get(Calendar.DAY_OF_MONTH)+","+ban.getPlayer().getName()+","+ban.getReason()+","+ban.getDayLength()+ip;
		Main.getSavedBanList().add(banToAdd);
		Main.getPlugin().getConfig().set("punishments.bans", Main.getSavedBanList());
		Main.getPlugin().saveConfig();
	}

}
