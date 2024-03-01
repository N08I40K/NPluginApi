package ru.n08i40k.npluginapi.command.subcommands;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.command.SubCommand;
import ru.n08i40k.npluginapi.gui.list.select.plugin.SelectPluginGuiHolder;
import ru.n08i40k.npluginapi.gui.list.select.plugin.SelectPluginGuiHolder.Registry;

import java.util.*;

public class ListCommand extends SubCommand {
    public ListCommand(@Nullable SubCommand parentCommand) {
        super(parentCommand);
    }

    @Override
    public @NotNull String getName() {
        return "list";
    }

    private Optional<Registry> checkRegistry(@NonNull CommandSender sender, @NotNull String input) {
        return Arrays.stream(Registry.values()).filter(registry ->
                        registry.name().equalsIgnoreCase(input) &&
                                (permissionBuilder.extend("*").has(sender) ||
                                        permissionBuilder.extend(registry.name().toLowerCase()).has(sender)))
                .findAny();
    }

    private Set<String> acceptableRegistries(@NonNull CommandSender sender) {
        Set<String> acceptableRegistries = new HashSet<>();

        for (Registry registry : Registry.values()) {
            String registryName = registry.name().toLowerCase();

            if (permissionBuilder.extend("*").has(sender) ||
                    permissionBuilder.extend(registryName).has(sender))
                acceptableRegistries.add(registryName);
        }

        return acceptableRegistries;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player))
            return false;

        if (args.length == 0) {
            player.openInventory(new SelectPluginGuiHolder(null).getInventory());
            return true;
        }

        Optional<Registry> registry = checkRegistry(sender, args[0]);

        if (registry.isEmpty()) {
            localeRequestBuilder.get("incorrect-registry", acceptableRegistries(sender))
                    .getMultiple().sendMessage(sender);
            return false;
        }

        player.openInventory(new SelectPluginGuiHolder(registry.get()).getInventory());

        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        if (args.length <= 1)
            return getAutoCompletion(args[0], acceptableRegistries(sender));
        return ImmutableList.of();
    }
}
