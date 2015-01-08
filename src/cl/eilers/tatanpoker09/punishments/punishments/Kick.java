package cl.eilers.tatanpoker09.punishments.punishments;

import org.bukkit.entity.Player;

public class Kick extends Punishment{
	public Kick(Player player, String reason) {
		super(reason, PunishmentType.KICK, player);
	}
}
