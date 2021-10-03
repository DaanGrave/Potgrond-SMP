package nl.potgrondyt.smp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Set;

import static nl.potgrondyt.smp.SmpPlugin.color;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        Scoreboard scoreboard = player.getScoreboard();

        Team team = getTeam(player, scoreboard);
        if (team != null) {
            String colorCode = getColorCode(team.getColor());
            event.setFormat(color(colorCode + player.getName() + ": &f") + event.getMessage());
        } else {
            event.setFormat(color("&7" + player.getName() + ": &f") + event.getMessage());
        }
    }

    public static Team getTeam(Player player, Scoreboard scoreboard) {
        Set<Team> teams = scoreboard.getTeams();

        for (Team team : teams) {
            if (team.getEntries().contains(player.getName())) {
                return team;
            }
        }

        return null;
    }

    public static String getColorCode(ChatColor chatColor) {
        return "&" + chatColor.getChar();
    }
}
