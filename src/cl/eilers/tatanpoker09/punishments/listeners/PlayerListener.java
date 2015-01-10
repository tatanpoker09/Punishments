package cl.eilers.tatanpoker09.punishments.listeners;

import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;

import cl.eilers.tatanpoker09.punishments.Main;
import cl.eilers.tatanpoker09.punishments.punishments.Ban;
import cl.eilers.tatanpoker09.punishments.punishments.Mute;
import cl.eilers.tatanpoker09.punishments.punishments.Punishment;
import cl.eilers.tatanpoker09.punishments.punishments.PunishmentType;

@SuppressWarnings("deprecation")
public class PlayerListener implements Listener{
	@EventHandler
	public void onPlayerLogin(PlayerPreLoginEvent event){
		for(Punishment punishment : Main.getPunishments()){
			if(punishment.getPlayer().getUniqueId().equals(event.getUniqueId())){
				if(punishment.getType().equals(PunishmentType.BAN)){
					Ban ban = (Ban)punishment;
					Calendar banFinished = Calendar.getInstance();
					banFinished.set(ban.getDate().get(Calendar.YEAR), ban.getDate().get(Calendar.MONTH), ban.getDate().get(Calendar.DAY_OF_MONTH)-1);
					banFinished.add(Calendar.DATE, ban.getDayLength());
					Calendar today = Calendar.getInstance();
					if(banFinished.before(today)){
						punishment.getPlayer().setBanned(false);
						Bukkit.getLogger().info("Unbanned player: "+event.getName());
					} else {
						Bukkit.getLogger().info("Banned player tried to join: "+event.getName());
						event.disallow(org.bukkit.event.player.PlayerPreLoginEvent.Result.KICK_BANNED, punishment.getReason());
					}
				}
			}
		}
	}
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event){
		if(Mute.getMutedPlayers().contains(event.getPlayer())){
			event.getPlayer().sendMessage(ChatColor.DARK_RED+"[Punishments] "+ChatColor.RED+"Has sido muteado.");
			event.setCancelled(true);
		}
	}
}
