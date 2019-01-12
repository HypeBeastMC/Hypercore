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
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.hypebeastmc.hypercore.commands.sub;

import com.hypebeastmc.hypercore.commands.api.SubCommand;
import com.hypebeastmc.hypercore.util.TextUtils;
import net.kyori.text.TextComponent;
import net.kyori.text.adapter.bukkit.TextAdapter;
import net.kyori.text.event.ClickEvent;
import net.kyori.text.event.HoverEvent;
import net.kyori.text.format.TextColor;
import net.kyori.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EventStartSubCommand extends SubCommand {

    public boolean onSubCommand(CommandSender sender, Command command, String name, String[] args) {

        if(sender instanceof Player) {
            final TextComponent textComponent = TextComponent.of("Events \u258E ").decoration(TextDecoration.BOLD, true).color(TextColor.RED)
                    .append(TextComponent.of("A ").color(TextColor.GRAY))
                    .append(TextComponent.of("Last Man Standing ").color(TextColor.GRAY).decoration(TextDecoration.BOLD, true)
                            .hoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextUtils.fromLegacy("&7Fight to the death, last player alive wins."))))
                    .append(TextComponent.of("event hosted by ").color(TextColor.GRAY))
                    .append(TextComponent.of(((Player) sender).getDisplayName()))
                    .append(TextComponent.of(" will begin in 60 seconds.").color(TextColor.GRAY));
            // Events | A Last Man Standing event hosted by HOST will begin in 60 seconds

            final TextComponent joinMessage = TextComponent.of("Events \u258E ").decoration(TextDecoration.BOLD, true).color(TextColor.RED)
                    .append(TextComponent.of("Click ").color(TextColor.GRAY))
                    .append(TextComponent.of("[HERE] ").color(TextColor.RED).decoration(TextDecoration.BOLD, true).clickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "event join")))
                    .append(TextComponent.of("to join this event.").color(TextColor.GRAY));
            // Events | Click [HERE] to join this event.

            final TextComponent confirmMessage = TextComponent.of("Events \u258E ").decoration(TextDecoration.BOLD, true).color(TextColor.RED)
                    .append(TextComponent.of("WARNING: If you loose you will NOT keep your items. ").color(TextColor.GRAY))
                    .append(TextComponent.of("[ACCEPT]").color(TextColor.RED).clickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "event confirm")));
            // Events | WARNING: If you loose you will not keep your items. [ACCEPT]

            if(args[1].equalsIgnoreCase("start")) {

            } else if (args[1].equalsIgnoreCase("join")) {
                TextAdapter.sendComponent(sender, confirmMessage);
            } else if(args[1].equalsIgnoreCase("confirm")) {

            }
        } else {
            sender.sendMessage("Only players can run or join events.");
        }

        return false;
    }
}
