package cl.eilers.tatanpoker09.punishments.commands;

import java.net.InetSocketAddress;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cl.eilers.tatanpoker09.punishments.TatanUtils;
import cl.eilers.tatanpoker09.punishments.punishments.Ban;

public class PBanCommand implements CommandExecutor{
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(args.length>3){
			if(Bukkit.getOfflinePlayer(args[0])!=null){
				int time = Integer.parseInt(args[1]);
				if(time==0){
					time = 1000;
				}
				String reason = TatanUtils.concatenateArgs(args, 2);
				InetSocketAddress ip = null;
				if(Bukkit.getOfflinePlayer(args[0]).getPlayer()!=null){
					ip = Bukkit.getOfflinePlayer(args[0]).getPlayer().getAddress();
				}
				if(ip!=null){
				new Ban(Bukkit.getOfflinePlayer(args[0]), reason, Calendar.getInstance(), time, ip.getAddress().toString()).add();
				} else {
					new Ban(Bukkit.getOfflinePlayer(args[0]), reason, Calendar.getInstance(), time);
				}
				return true;
			} else {
				sender.sendMessage(ChatColor.DARK_RED+"[Punishments] "+ChatColor.RED+"No se encontró un jugador por ese nombre.");
				return false;
			}
		} else {
			sender.sendMessage(ChatColor.DARK_RED+"[Punishments] "+ChatColor.RED+"Debes especificar jugador, tiempo(en días) y razón.");
			return false;
		}
	}
}
