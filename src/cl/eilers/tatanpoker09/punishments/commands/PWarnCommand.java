package cl.eilers.tatanpoker09.punishments.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cl.eilers.tatanpoker09.punishments.Main;
import cl.eilers.tatanpoker09.punishments.punishments.Warn;

public class PWarnCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length==2){
			if(Bukkit.matchPlayer(args[0]).get(0)!=null){
				System.out.println("hi");
				Main.getPunishments().add(new Warn(Bukkit.matchPlayer(args[0]).get(0), args[1]));
				return true;
			} else {
				sender.sendMessage(ChatColor.DARK_RED+"[Punishments] "+ChatColor.RED+"No se encontró un jugador por ese nombre.");
				return false;
			}
		} else {
			sender.sendMessage(ChatColor.DARK_RED+"[Punishments] "+ChatColor.RED+"Debes especificar razón y jugador.");
			return false;
		}
	}
}
