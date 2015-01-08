package cl.eilers.tatanpoker09.punishments.punishments;

import java.util.Calendar;

import org.bukkit.entity.Player;

import cl.eilers.tatanpoker09.punishments.Main;

public class Punishment {
	private PunishmentType type;
	private String reason;
	private Player player;
	private java.util.Date date;

	public Punishment(String reason, PunishmentType type, Player player){
		this.setType(type);
		this.setReason(reason);
		this.setPlayer(player);
		setDate(Calendar.getInstance().getTime());
		Main.addPunishments(this);
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}
}
