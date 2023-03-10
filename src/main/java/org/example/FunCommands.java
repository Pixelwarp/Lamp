/*
 * This file is part of lamp-examples, licensed under the MIT License.
 *
 *  Copyright (c) Revxrsal <reflxction.github@gmail.com>
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package org.example;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.*;
import revxrsal.commands.bukkit.EntitySelector;
import revxrsal.commands.bukkit.annotation.CommandPermission;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Command("fun")
public class FunCommands {

    @Subcommand("creepers")
    @CommandPermission("fun.creepers")
    @Description("Spawn a number of creepers above the player")
    public void spawnPigs(
            // We can require numbers to be in a specific range
            @Range(min = 1, max = 100) int amount,

            // If no player is specified, default to the sender
            @Optional(def = "me") Player target
    ) {
        Location location = target.getLocation();
        location.add(0, 3, 0);
        for (int i = 0; i < amount; i++) {
            target.getWorld().spawnEntity(location, EntityType.CREEPER);
        }
        target.sendMessage("Enjoy your creepers!");
    }

    @Subcommand("gibberish")
    @Description("Send a lot of obfuscated text to the specified players")
    // We can define a cooldown for the command
    @Cooldown(value = 1, unit = TimeUnit.MINUTES)
    public void gibberish(
            // Entity selectors are things like @a, @e[type=player], etc.
            EntitySelector<Player> players
    ) {
        String[] messages = new String[15];
        Arrays.fill(messages, "HELLO FRIEND");
        for (Player player : players) {
            player.sendMessage(messages);
        }
    }

    @Subcommand("smite")
    public void smite(
            EntitySelector<LivingEntity> entities,

            // A switch is an optional parameter. It can be set by
            // adding -fake to the command input
            @Switch boolean fake
    ) {
        for (LivingEntity entity : entities) {
            if (fake)
                entity.getWorld().strikeLightningEffect(entity.getLocation());
            else
                entity.getWorld().strikeLightning(entity.getLocation());
        }
    }
}
