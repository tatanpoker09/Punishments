package cl.eilers.tatanpoker09.punishments.punishments;

import org.bukkit.entity.Player;

public class Ban extends Punishment{
	private int dayLength;

	@SuppressWarnings("deprecation")
	public Ban(int dayLength, Player player, String reason){
		super(reason, PunishmentType.BAN, player);
		player.kickPlayer(reason);
		player.setBanned(true);
		this.setDayLength(dayLength);
	}

	public int getDayLength() {
		return dayLength;
	}

	public void setDayLength(int dayLength) {
		this.dayLength = dayLength;
	}

}
