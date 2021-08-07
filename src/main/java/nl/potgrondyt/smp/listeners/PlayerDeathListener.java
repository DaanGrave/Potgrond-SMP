package nl.potgrondyt.smp.listeners;

import nl.potgrondyt.smp.SmpPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.Team;

public class PlayerDeathListener implements Listener {
    private final SmpPlugin smpPlugin;

    public PlayerDeathListener(SmpPlugin smpPlugin) {
        this.smpPlugin = smpPlugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();

        ConfigurationSection smpPlayer = this.smpPlugin.getPlayer(player);
        int lives = smpPlayer.getInt("lives", 3);

        smpPlayer.set("lives", lives - 1);
        lives--;

        Team team = this.smpPlugin.scoreboard.getTeam("lives-" + lives);
        if (team != null){
            team.addEntry(player.getName());
        }

        if (lives == 0){
            smpPlayer.set("banned_till", System.currentTimeMillis() + this.getMillis());
            smpPlayer.set("lives", 3);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + player.getName() + " 1w &cJe hebt een BAN voor te NOOB zijn op de Potgrond SMP!\nMaar je bent nog steeds beter dan Stefan.");
        }
        this.smpPlugin.saveConfig();
    }

    private long getMillis(){
        return 7 * 24 * 60 * 60 * 1000L;
    }
}
