package org.example;

import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitCommandHandler;

public final class ExamplePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        BukkitCommandHandler commandHandler = BukkitCommandHandler.create(this);

        // Example commands that describe the basics of command creation
        commandHandler.register(new GameModeCommands());

        // Example commands that describe the use of some annotations
        commandHandler.register(new FunCommands());

        // Register it to Minecraft's 1.13+ colored command system,
        // so we get colorful command completions!
        commandHandler.registerBrigadier();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
