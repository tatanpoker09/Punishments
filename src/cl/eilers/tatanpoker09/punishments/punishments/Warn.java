package cl.eilers.tatanpoker09.punishments.punishments;

import java.util.Calendar;

import org.bukkit.OfflinePlayer;

import cl.eilers.tatanpoker09.punishments.Main;

public class Warn extends Punishment{
	
	public Warn(OfflinePlayer player, String reason, Calendar date){
		super(reason, PunishmentType.WARN, player, date);
	}
	public Warn(OfflinePlayer player, String reason, Calendar date, String ip){
		super(reason, PunishmentType.WARN, player, date, ip);
	}
	
	public static void warnToString(Warn warn){
		String ip = "";
		if(warn.getIp()!=null){
			ip = ","+warn.getIp();
		}
		String warnToAdd = warn.getDate().get(Calendar.YEAR)+","+warn.getDate().get(Calendar.MONTH)+","+warn.getDate().get(Calendar.DAY_OF_MONTH)+","+warn.getPlayer().getName()+","+warn.getReason()+ip;
		Main.getSavedWarnList().add(warnToAdd);
		Main.getPlugin().getConfig().set("punishments.warns", Main.getSavedWarnList());
		Main.getPlugin().saveConfig();
	}
}
