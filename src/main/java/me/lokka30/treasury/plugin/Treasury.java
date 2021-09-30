/*
 * Copyright (c) 2021 lokka30.
 * This code is part of Treasury, an Economy API for Minecraft servers.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You have received a copy of the GNU Affero General Public License
 * with this program - please see the LICENSE.md file. Alternatively,
 * please visit the <https://www.gnu.org/licenses/> website.
 *
 * Please see <https://github.com/lokka30/Treasury> for more information on this resource.
 */

package me.lokka30.treasury.plugin;

import me.lokka30.microlib.maths.QuickTimer;
import me.lokka30.treasury.plugin.command.treasury.TreasuryCommand;
import me.lokka30.treasury.plugin.misc.Utils;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class Treasury extends JavaPlugin {

    /**
     * Any contributors to Treasury may add their name here! :)
     */
    public static final ArrayList<String> contributors = new ArrayList<>(Arrays.asList("lokka30 (Author, main developer)", "ExampleUser (Example description)"));

    /**
     * This is Treasury's API version. This is NOT api-version from plugin.yml.
     * Any major changes to the API should make this number increase.
     * This allows Treasury to warn server owners if their Provider
     * does not support the latest Treasury API version.
     */
    public static final short apiVersion = 1;

    @Override
    public void onEnable() {
        final QuickTimer startupTimer = new QuickTimer();

        Utils.logger.info("&fStart-up complete (took &b" + startupTimer.getTimer() + "ms&f).");
    }

    @Override
    public void onDisable() {
        final QuickTimer shutdownTimer = new QuickTimer();

        Utils.logger.info("&fShut-down complete (took &b" + shutdownTimer.getTimer() + "ms&f).");
    }

    private void registerCommands() {
        Utils.logger.info("Registering commands...");
        registerCommand("treasury", new TreasuryCommand(this));
    }

    private void registerCommand(@SuppressWarnings("SameParameterValue") @NotNull final String baseCommand, @NotNull final TabExecutor executor) {
        assert !baseCommand.isEmpty();

        final PluginCommand pluginCommand = getCommand(baseCommand);
        if(pluginCommand == null) {
            Utils.logger.error("Unable to register command '&b/" + baseCommand + "&7'. Please notify a Treasury developer.");
        } else {
            pluginCommand.setExecutor(executor);
            Utils.logger.info("Registered command '&b/" + baseCommand + "&7' successfully.");
        }
    }
}
