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

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Description;
import revxrsal.commands.annotation.Range;
import revxrsal.commands.annotation.Switch;
import revxrsal.commands.bukkit.BukkitCommandActor;
import revxrsal.commands.bukkit.annotation.CommandPermission;
import revxrsal.commands.exception.CommandErrorException;

public class EnchantmentCommands {

    @Command({"ench", "enchant"})
    @Description("Enchant an item")
    @CommandPermission("example.enchant")
    public void enchant(
            BukkitCommandActor actor,
            Enchantment enchantment,
            @Range(min = 1, max = 1000) int level,
            @Switch("bypass-restrictions") boolean bypassRestrictions
    ) {
        ItemStack mainHand = actor.requirePlayer().getInventory().getItemInMainHand();
        if (mainHand == null || mainHand.getType().isAir())
            throw new CommandErrorException("You must hold an item!");
        if (bypassRestrictions)
            mainHand.addUnsafeEnchantment(enchantment, level);
        else {
            try {
                mainHand.addEnchantment(enchantment, level);
            } catch (IllegalArgumentException e) {
                // Enchant is inapplicable to the item, level is out of bounds, etc.
                actor.error(e.getMessage());
            }
        }
        actor.reply("&aSucessfully enchanted.");
    }
}
