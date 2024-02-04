package ru.n08i40k.npluginapi.command.subcommands.admin;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.n08i40k.npluginapi.command.SubCommand;
import ru.n08i40k.npluginapi.command.subcommands.admin.locale.LocaleReloadCommand;

public class LocaleCommand extends SubCommand {
    public LocaleCommand(SubCommand parentCommand) {
        super(parentCommand);

        subcommands.put(new LocaleReloadCommand(this));
    }

    @Override
    public @NotNull String getName() {
        return "locale";
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        localeRequestBuilder.get("select-action", String.join(", ", subcommands.keySet()))
                .getSingle().sendMessage(sender);
        return false;
    }
}
