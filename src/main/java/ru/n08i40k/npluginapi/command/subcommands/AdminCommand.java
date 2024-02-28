package ru.n08i40k.npluginapi.command.subcommands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.command.SubCommand;
import ru.n08i40k.npluginapi.command.subcommands.admin.EnchantCommand;
import ru.n08i40k.npluginapi.command.subcommands.admin.LocaleCommand;

public class AdminCommand extends SubCommand {
    public AdminCommand(@Nullable SubCommand parent) {
        super(parent);

        subcommands.put(new LocaleCommand(this));
        subcommands.put(new EnchantCommand(this));
    }

    @Override
    public @NotNull String getName() {
        return "admin";
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        localeRequestBuilder.get("select-action", String.join(", ", subcommands.keySet()))
                .getSingle().sendMessage(sender);
        return false;
    }
}
