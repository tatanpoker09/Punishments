package cl.eilers.tatanpoker09.punishments.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cl.eilers.tatanpoker09.punishments.Main;
import cl.eilers.tatanpoker09.punishments.TatanUtils;
import cl.eilers.tatanpoker09.punishments.punishments.Mute;
import cl.eilers.tatanpoker09.punishments.punishments.Punishment;
import cl.eilers.tatanpoker09.punishments.punishments.PunishmentType;

public class PRemoveCommand implements CommandExecutor{
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(args.length==2){
			if(TatanUtils.isNumeric(args[1])){
				java.util.List<Punishment> playerPunishments = PGetCommand.getPlayerPunishments(Bukkit.getOfflinePlayer(args[0]));
				if(playerPunishments.size()>0){
					Punishment punishment = playerPunishments.get(Integer.parseInt(args[1]));
					Main.getPunishments().remove(punishment);
					if(punishment.getType().equals(PunishmentType.BAN)){
						punishment.getPlayer().setBanned(false);
					} else if(punishment.getType().equals(PunishmentType.MUTE)){
						if(((Mute)punishment).isActive()){
							Mute.getMutedPlayers().remove(punishment.getPlayer());
						}
					}
					sender.sendMessage(ChatColor.DARK_RED+"[Punishments] "+ChatColor.RED+"Punishment removido: ");
					sender.sendMessage(ChatColor.BLUE+"Type: "+punishment.getType()+" Reason: "+punishment.getReason());
					sender.sendMessage(ChatColor.RED+"Date: "+punishment.getDate().getTime());
					return true;
				} else {
					sender.sendMessage(ChatColor.DARK_RED+"[Punishments] "+ChatColor.RED+"No quedan más punishments para ese jugador.");
					return false;
				}
			} else {
				sender.sendMessage(ChatColor.RED+"Se esperaba un numero, se recibió texto.");
				return false;
			}
		} else {
			sender.sendMessage(ChatColor.RED+"Uso: /PRemove <Player> <index>");
			sender.sendMessage(ChatColor.RED+"Para revisar el index ocupa /pget <Player>");
			return false;
		}
	}
}
