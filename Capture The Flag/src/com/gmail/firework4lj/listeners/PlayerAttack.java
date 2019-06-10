package com.gmail.firework4lj.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.gmail.firework4lj.main.Main;

	/* How this class works:
	* 
	* This class listens for entities damaging another entity.
	* If the player is attacked by another player, the player is checked to first, be in the game. 
	* If so, they are then checked to be on the other team. If both conditions are met, the victim then takes damage.
	* If the players are on the same team, the damage is cancelled and the attacker is sent a message to stop attacking there own team.
	* If the players are not in the game, the event is allowed and players can be damaged like normal.
 	*/

public class PlayerAttack implements Listener{

	private Main main;
	public PlayerAttack(Main Main) {
		this.main = Main;
	}
	
	@EventHandler
	public void onPvp(EntityDamageByEntityEvent event){ //where event is a player attacking another player, this function negates team damage
		if ((event.getEntity() instanceof Player) && (event.getDamager() instanceof Player)) {
			Player attacker = (Player) event.getDamager();
			Player player = (Player) event.getEntity();
			if (Main.teamred.containsKey(attacker.getName()) && (Main.teamred.containsKey(player.getName()))  && Main.ctfingame.containsKey(player.getName())) {
				event.setCancelled(true);
				main.msg(attacker, ChatColor.RED+"Hey, no attacking your own team, red!");
				//if red team member tries to attack fellow red team member
			} else if (Main.teamblue.containsKey(attacker.getName()) && (Main.teamblue.containsKey(player.getName()))  && Main.ctfingame.containsKey(player.getName())) {
				event.setCancelled(true);
				main.msg(attacker, ChatColor.BLUE+"Hey, no attacking your own team, blue!");
			}//if blue team member tries to attack fellow blue team member 
			else {
				event.setCancelled(false);
			}
		}
	}	
}
