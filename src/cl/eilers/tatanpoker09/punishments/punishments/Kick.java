package cl.eilers.tatanpoker09.punishments.punishments;

import java.util.Calendar;

import org.bukkit.OfflinePlayer;

import cl.eilers.tatanpoker09.punishments.Main;

public class Kick extends Punishment{
	public Kick(OfflinePlayer player, String reason, Calendar date) {
		super(reason, PunishmentType.KICK, player, date);
	}
	public Kick(OfflinePlayer player, String reason, Calendar date, String ip) {
		super(reason, PunishmentType.KICK, player, date, ip);
	}

	public static void kickToString(Kick kick) {
		String ip = "";
		if(kick.getIp()!=null){
			ip = ","+kick.getIp();
		}
		String kickToAdd = kick.getDate().get(Calendar.YEAR)+","+kick.getDate().get(Calendar.MONTH)+","+kick.getDate().get(Calendar.DAY_OF_MONTH)+","+kick.getPlayer().getName()+","+kick.getReason()+ip;
		Main.getSavedKickList().add(kickToAdd);
		Main.getPlugin().getConfig().set("punishments.kicks", Main.getSavedKickList());
		Main.getPlugin().saveConfig();
	}
}
