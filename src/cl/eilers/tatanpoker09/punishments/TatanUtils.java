package cl.eilers.tatanpoker09.punishments;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
/**
 * Quick and simple utils.
 * @author tatanpoker09
 *
 */
public class TatanUtils {
/**
 * Checks if a string is a number.
 * @param str
 * @return boolean
 */
	public static boolean isNumeric(String str){  
		try{  
			@SuppressWarnings("unused")
			double Number = Double.parseDouble(str); 
		}catch(NumberFormatException nfe){  
			System.err.println(nfe.getStackTrace());
			return false;  
		}  
		return true;  
	}
/**
 * Turns an array into a single string, separating each index in the array by a space(" ").
 * @param inputString
 * @return String
 */
	public static String concatenateArgs(String[] inputString, int iNumber){
		String mapName = null;
			int i = iNumber;
			while(i < inputString.length) {
				mapName += " " + inputString[i];
				i++;
			}
			mapName = mapName.substring(5);
		return mapName;
	}
	
	public static String concatenatePlayerNames(List<OfflinePlayer> inputString, int iNumber){
		String mapName = null;
			int i = iNumber;
			while(i < inputString.size()) {
				mapName += ", " + inputString.get(i).getName();
				i++;
			}
			mapName = mapName.substring(6);
		return mapName;
	}
	/**
	 * Converts seconds to long(Bukkit tick time configuration).
	 * @param seconds
	 * @return long
	 */
	public static long secondsToLong(int seconds){
		return seconds*20;
	}
	/**
	 * Converts a string into a Location.
	 * @param str
	 * @return Location
	 */
	public static Location getLocation(String str){
		String[] arg = str.split(",");
		double[] parsed = new double[4];
		for (int a = 0; a < 4; a++) {
			parsed[a] = Double.parseDouble(arg[a]);
		}
		Location returnedLocation = new Location (Bukkit.getWorld("mapavacio"), parsed[0]-0.5, parsed[1]+0.5, parsed[2]+0.5);
		returnedLocation.setYaw((float) parsed[3]);
		return returnedLocation;
	}
	/**
	 * Gives you a random number between min and max.
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}