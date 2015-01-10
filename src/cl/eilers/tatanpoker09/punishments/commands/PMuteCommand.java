package cl.eilers.tatanpoker09.punishments.commands;

import java.net.InetSocketAddress;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cl.eilers.tatanpoker09.punishments.TatanUtils;
import cl.eilers.tatanpoker09.punishments.punishments.Mute;
import cl.eilers.tatanpoker09.punishments.punishments.Punishment;
import cl.eilers.tatanpoker09.punishments.punishments.PunishmentType;

public class PMuteCommand implements CommandExecutor{
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(args.length>=1){
			if(Bukkit.getOfflinePlayer(args[0])!=null){
				if(Mute.getMutedPlayers().contains(Bukkit.getOfflinePlayer(args[0]))){
					sender.sendMessage(ChatColor.DARK_GREEN+"[Punishments] "+ChatColor.GREEN+"Has desmuteado a: "+args[0]);
					Mute.getMutedPlayers().remove(Bukkit.getOfflinePlayer(args[0]));
					for(Punishment punish : PGetCommand.getPlayerPunishments(Bukkit.getOfflinePlayer(args[0]))){
						if(punish.getType().equals(PunishmentType.MUTE)){
							((Mute)punish).setActive(false);
						}
					}
					return true;
				}
				if(args.length==2){
					if(Bukkit.getOfflinePlayer(args[0]).isOnline()){
						Bukkit.getOfflinePlayer(args[0]).getPlayer().sendMessage(ChatColor.DARK_RED+"Has sido muteado.");
					}
					String reason = TatanUtils.concatenateArgs(args, 1);
					InetSocketAddress ip = null;
					if(Bukkit.getOfflinePlayer(args[0]).getPlayer()!=null){
						ip = Bukkit.getOfflinePlayer(args[0]).getPlayer().getAddress();
					}
					if(ip!=null){
						((Mute) new Mute(Bukkit.getOfflinePlayer(args[0]), reason, Calendar.getInstance(), ip.getAddress().toString()).add()).addPlayer();
					} else {
						((Mute) new Mute(Bukkit.getOfflinePlayer(args[0]), reason, Calendar.getInstance()).add()).addPlayer();
					}
					return true;
				} else {
					sender.sendMessage(ChatColor.DARK_RED+"[Punishments] "+ChatColor.RED+"Debes especificar jugador y razón.");
					return false;
				}
			}else {
				sender.sendMessage(ChatColor.DARK_RED+"[Punishments] "+ChatColor.RED+"No se encontró un jugador por ese nombre.");
				return false;
			}
		} else {
			sender.sendMessage(ChatColor.DARK_RED+"[Punishments] "+ChatColor.RED+"Debes especificar por lo menos el jugador.");
			return false;
		}
	}
}
