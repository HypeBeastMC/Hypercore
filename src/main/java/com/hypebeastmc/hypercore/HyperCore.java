/*
 * MIT License
 *
 * Copyright (c) 2019 HypeBeastMC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.hypebeastmc.hypercore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.hypebeastmc.hypercore.commands.HyperCoreCommand;
import com.hypebeastmc.hypercore.listeners.SoupListener;
import com.hypebeastmc.hypercore.listeners.TrenchPickListener;
import com.massivecraft.factions.P;

/**
 * @author JoeZwet
 */

public class HyperCore extends JavaPlugin {

    /* Plugin Instance */
    public static HyperCore instance;
	public P factions;

    /* On Enable Event */
    @Override
    public void onEnable() {
        instance = this; /* Set Plugin Instance */

        /* Set Command Executors */
        this.getCommand("hypercore").setExecutor(new HyperCoreCommand()); /* HyperCore Command Executor */

        /* Set Command Tab Completer */
        this.getCommand("hypercore").setTabCompleter(new HyperCoreCommand()); /* HyperCore Tab Completer */

        /* Register Event Listeners */
        this.getServer().getPluginManager().registerEvents(new SoupListener(), this); /* Soup Listener */
        this.getServer().getPluginManager().registerEvents(new TrenchPickListener(), this); /* Trench Pick Listener */
        this.factions = (P)Bukkit.getPluginManager().getPlugin("Factions");
            }
    }

