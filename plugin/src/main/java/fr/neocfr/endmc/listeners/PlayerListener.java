package fr.neocfr.endmc.listeners;

import fr.neocfr.endmc.EndMc;
import fr.neocfr.endmc.dto.UserDTO;
import fr.neocfr.endmc.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;

public class PlayerListener implements Listener {

    private final EndMc plugin;

    public PlayerListener(EndMc plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        plugin.getWebAccess().getProfileByUniqueId(player.getUniqueId()).whenComplete((profile, error) -> {
            if (error != null) {
                plugin.logError(Messages.FAIL_TO_LOAD.formatted(player.getName()), error);
                return;
            }
            if (profile == null) createProfile(player);
        });
    }

    private void createProfile(Player player) {
        UserDTO profile = UserDTO.builder().uniqueId(player.getUniqueId()).name(player.getName())
                .build();

        plugin.getWebAccess().createProfile(profile).whenComplete((result, error) -> {
            if (error != null) {
                plugin.logError(Messages.FAIL_TO_CREATE.formatted(player.getName()), error);
                return;
            }
            if (plugin.getWebAccess().isDebug())
                plugin.getLogger().log(Level.INFO, "Profile created " + result);
        });
    }
}
