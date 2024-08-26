package fr.neocfr.endmc;

import fr.neocfr.endmc.commands.PlayerCommand;
import fr.neocfr.endmc.listeners.PlayerListener;
import fr.neocfr.endmc.web.Web;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class EndMc extends JavaPlugin {

    private Web webAccess;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        webAccess = new Web();
        webAccess.load(getConfig());

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        registerCommand("player", new PlayerCommand(this));
    }

    @Override
    public void onDisable() {
        webAccess.close();
    }

    public Web getWebAccess() {
        return webAccess;
    }

    public void logError(String message, Throwable error) {
        if (webAccess.isDebug())
            getLogger().log(Level.WARNING, message, error);
    }

    @SuppressWarnings("SameParameterValue")
    private void registerCommand(String command, TabExecutor executor) {
        PluginCommand pluginCommand = getCommand(command);

        if (pluginCommand == null) {
            getLogger().log(Level.WARNING, "Command {} not found", command);
            return;
        }
        pluginCommand.setExecutor(executor);
        pluginCommand.setTabCompleter(executor);
    }
}
