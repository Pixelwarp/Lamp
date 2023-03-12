package org.example;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.autocomplete.SuggestionProvider;
import revxrsal.commands.bukkit.BukkitCommandHandler;
import revxrsal.commands.bukkit.brigadier.MinecraftArgumentType;
import revxrsal.commands.exception.CommandErrorException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ExamplePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        BukkitCommandHandler commandHandler = BukkitCommandHandler.create(this);

        // Example commands that describe the basics of command creation
        commandHandler.register(new GameModeCommands());

        // Example commands that describe the use of some annotations
        commandHandler.register(new FunCommands());

        // Automatically parse parameters of type 'Enchantment'.
        commandHandler.registerValueResolver(Enchantment.class, context -> {
            String enchantment = context.popForParameter();

            // In case it was minecraft:<enchantment_name>
            NamespacedKey key = NamespacedKey.fromString(enchantment);

            Enchantment ench = Enchantment.getByKey(key);
            if (ench == null)
                throw new CommandErrorException("Invalid enchantment: &e" + enchantment + "&c.");
            return ench;
        });

        // It's important to register the command AFTER registering resolvers and
        // suggestions.
        commandHandler.register(new EnchantmentCommands());

        // Register it to Minecraft's 1.13+ colored command system,
        // so we get colorful command completions!
        //
        // This should be done after registering all commands
        commandHandler.getBrigadier().ifPresent(brigadier -> {
            brigadier.bind(Enchantment.class, MinecraftArgumentType.ENCHANTMENT);
            brigadier.register();
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
