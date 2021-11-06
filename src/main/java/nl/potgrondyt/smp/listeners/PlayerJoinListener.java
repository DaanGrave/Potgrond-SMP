package nl.potgrondyt.smp.listeners;

import nl.potgrondyt.smp.SmpPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Team;

public class PlayerJoinListener implements Listener {
    private final SmpPlugin smpPlugin;

    public PlayerJoinListener(SmpPlugin smpPlugin) {
        this.smpPlugin = smpPlugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        FileConfiguration config = this.smpPlugin.getConfig();
        if (!config.contains("player." + player.getUniqueId())){
            ConfigurationSection section = config.createSection("player." + player.getUniqueId());
            section.set("lives", 3);
            section.set("banned_till", 0L);
            this.smpPlugin.saveConfig();
        }

        ConfigurationSection player1 = this.smpPlugin.getPlayer(player);
        player.setScoreboard(this.smpPlugin.scoreboard);
        Team lives = this.smpPlugin.scoreboard.getTeam("lives-" + player1.getInt("lives"));
        if (lives == null && player1.getInt("lives") > 3) {
            lives = this.smpPlugin.scoreboard.getTeam("lives-lives_more");
        }
        if (lives != null){
            lives.addEntry(player.getName());
        }
    }
}
