package cl.eilers.tatanpoker09.punishments.commands;

import java.net.InetSocketAddress;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cl.eilers.tatanpoker09.punishments.TatanUtils;
import cl.eilers.tatanpoker09.punishments.punishments.Warn;

public class PWarnCommand implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length==2){
			if(Bukkit.getOfflinePlayer(args[0])!=null){
				if(Bukkit.getOfflinePlayer(args[0]).isOnline()){
					Bukkit.getOfflinePlayer(args[0]).getPlayer().sendMessage(ChatColor.DARK_RED+"Has sido warneado.");
				}
				String reason = TatanUtils.concatenateArgs(args, 1);
				InetSocketAddress ip = null;
				if(Bukkit.getOfflinePlayer(args[0]).getPlayer()!=null){
					ip = Bukkit.getOfflinePlayer(args[0]).getPlayer().getAddress();
				}
				if(ip!=null){
					new Warn(Bukkit.getOfflinePlayer(args[0]), reason, Calendar.getInstance(), ip.getAddress().toString()).add();
				} else {
					new Warn(Bukkit.getOfflinePlayer(args[0]), reason, Calendar.getInstance()).add();
				}
				return true;
			} else {
				sender.sendMessage(ChatColor.DARK_RED+"[Punishments] "+ChatColor.RED+"No se encontr� un jugador por ese nombre.");
				return false;
			}
		} else {
			sender.sendMessage(ChatColor.DARK_RED+"[Punishments] "+ChatColor.RED+"Debes especificar jugador y raz�n.");
			return false;
		}
	}
}
