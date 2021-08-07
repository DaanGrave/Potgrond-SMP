package nl.potgrondyt.smp;

import nl.potgrondyt.smp.listeners.PlayerDeathListener;
import nl.potgrondyt.smp.listeners.PlayerJoinListener;
import nl.potgrondyt.smp.listeners.PreJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Arrays;
import java.util.UUID;

public class SmpPlugin extends JavaPlugin {
    public Scoreboard scoreboard;

    @Override
    public void onEnable() {
        this.registerListeners(
                new PreJoinListener(this),
                new PlayerDeathListener(this),
                new PlayerJoinListener(this)
        );

        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Team team3 = this.scoreboard.registerNewTeam("lives-3");
        team3.setColor(ChatColor.GREEN);
        Team team2 = this.scoreboard.registerNewTeam("lives-2");
        team2.setColor(ChatColor.YELLOW);
        Team team1 = this.scoreboard.registerNewTeam("lives-1");
        team1.setColor(ChatColor.RED);

        for (Player player : Bukkit.getOnlinePlayers()){
            player.setScoreboard(this.scoreboard);

            ConfigurationSection smpPlayer = this.getPlayer(player);

            switch (smpPlayer.getInt("lives", 3)){
                case 3:
                    team3.addEntry(player.getName());
                    break;
                case 2:
                    team2.addEntry(player.getName());
                    break;
                case 1:
                    team1.addEntry(player.getName());
                    break;
            }
        }
    }

    private void registerListeners(Listener... listeners){
        Arrays.asList(listeners).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    public ConfigurationSection getPlayer(UUID uuid){
        return this.getConfig().getConfigurationSection("player." + uuid);
    }

    public ConfigurationSection getPlayer(Player player){
        return this.getPlayer(player.getUniqueId());
    }
}
