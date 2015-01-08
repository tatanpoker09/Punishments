package cl.eilers.tatanpoker09.punishments.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cl.eilers.tatanpoker09.punishments.Main;
import cl.eilers.tatanpoker09.punishments.punishments.Punishment;

public class DebugPunishmentsCommand implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		sender.sendMessage(""+Main.getPunishments().size());
		for(Punishment punishment : Main.getPunishments()){
			sender.sendMessage("Player: "+punishment.getPlayer().getName());
			sender.sendMessage("Type: "+punishment.getType().toString());
		}
		return true;
	}
}
