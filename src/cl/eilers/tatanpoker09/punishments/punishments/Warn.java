package cl.eilers.tatanpoker09.punishments.punishments;

import org.bukkit.entity.Player;

import cl.eilers.tatanpoker09.punishments.Main;

public class Warn extends Punishment{
	
	public Warn(Player player, String reason){
		super(reason, PunishmentType.WARN, player);
		player.sendMessage("You've been warned");
	}
	
	@SuppressWarnings("deprecation")
	public static void warnToString(Warn warn){
		String warnToAdd = warn.getDate().getDay()+","+warn.getPlayer().getName()+","+warn.getReason()+","+warn.getType().toString();
		Main.getSavedWarnList().add(warnToAdd);
		Main.getPlugin().getConfig().set("punishments.warns", Main.getSavedWarnList());
		Main.getPlugin().saveConfig();
	}

}
