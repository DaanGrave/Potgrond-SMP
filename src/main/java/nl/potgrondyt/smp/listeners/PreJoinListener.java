package nl.potgrondyt.smp.listeners;

import nl.potgrondyt.smp.SmpPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public class PreJoinListener implements Listener {
    private final SmpPlugin smpPlugin;

    public PreJoinListener(SmpPlugin smpPlugin) {
        this.smpPlugin = smpPlugin;
    }

    @EventHandler
    public void onPreJoin(AsyncPlayerPreLoginEvent event){
        UUID uniqueId = event.getUniqueId();
//        if (this.smpPlugin.getConfig().contains("player." + uniqueId)){
//            ConfigurationSection player = this.smpPlugin.getPlayer(uniqueId);
//            if (player.getLong("banned_till", 0L) > System.currentTimeMillis()){
//                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_BANNED);
//                event.setKickMessage(ChatColor.translateAlternateColorCodes('&', "&CJe hebt een BAN voor te NOOB zijn op de Potgrond SMP!"));
//            }
//        }
    }
}
