package cl.eilers.tatanpoker09.punishments.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cl.eilers.tatanpoker09.punishments.Main;
import cl.eilers.tatanpoker09.punishments.punishments.Ban;
import cl.eilers.tatanpoker09.punishments.punishments.Mute;
import cl.eilers.tatanpoker09.punishments.punishments.Punishment;
import cl.eilers.tatanpoker09.punishments.punishments.PunishmentType;

public class PGetCommand implements CommandExecutor{
	private static Map<UUID, ArrayList<Punishment>> playerPunishments = new HashMap<UUID, ArrayList<Punishment>>();
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		return getPunishments((Player)sender, args);
	}
	public static Map<UUID, ArrayList<Punishment>> getPlayerPunishments() {
		return playerPunishments;
	}
	public static void setPlayerPunishments(Map<UUID, ArrayList<Punishment>> playerPunishments) {
		PGetCommand.playerPunishments = playerPunishments;
	}
	
	@SuppressWarnings("deprecation")
	public static boolean getPunishments(Player sender, String[] args){
		if(args.length>=1){
			boolean showText = true;
			if(args.length>1){
				showText = Boolean.parseBoolean(args[1]);
			}
			if(Bukkit.getOfflinePlayer(args[0])!=null){
				if(showText){
					sender.sendMessage(ChatColor.AQUA+"************************************************");
					sender.sendMessage(ChatColor.AQUA+"**"+ChatColor.GREEN+"Mostrando los punishments de "+ChatColor.GOLD+args[0]+ChatColor.AQUA+"**");
					sender.sendMessage(ChatColor.AQUA+"************************************************");
				}
				int x = 0;
				for(Punishment punishments : getPlayerPunishments(Bukkit.getOfflinePlayer(args[0]))){
						String type = punishments.getType().toString();
						String ip = "Ip Not found";
						String spaces = "           ";
						String ipSpaces = "    ";
						if(punishments.getIp()!=null){
							spaces = "           ";
							ipSpaces = "        ";
							ip = punishments.getIp();
						}
						if(punishments.getType().equals(PunishmentType.BAN)){
							type = ((Ban)punishments).getDayLength()+" Day Ban";
							spaces = "     ";
						} else if(punishments.getType().equals(PunishmentType.MUTE)){
							if(((Mute)punishments).isActive()){
								type = "Active Mute";
							} else {
								type = "Inactive Mute";
							}
						}
						if(showText){
							sender.sendMessage(ChatColor.BLUE+""+x+".-  Type: "+type+spaces+"Reason: "+punishments.getReason());
							sender.sendMessage(ChatColor.RED+"      Ip: "+ip+ipSpaces+"Date: "+punishments.getDate().getTime());
						}
						x++;
				}
				return true;
			}
		} else {
			sender.sendMessage(ChatColor.DARK_RED+"[Punishments] "+ChatColor.RED+"Debes especificar el jugador.");
		}
		return false;
	}
	
	public static List<Punishment> getPlayerPunishments(OfflinePlayer player){
		List<Punishment> punishments = new ArrayList<Punishment>();
		for(Punishment punishment : Main.getPunishments()){
			if(punishment.getPlayer().equals(player)){
				punishments.add(punishment);
			}
		}
		return punishments;
	}
}
