package ru.n08i40k.npluginapi.command;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginlocale.LocaleRequestBuilder;
import ru.n08i40k.npluginapi.util.PluginPermissionBuilder;
import ru.n08i40k.npluginapi.util.PluginUse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public abstract class SubCommand extends PluginUse {
    protected final SubCommandMap subcommands;

    protected final PluginPermissionBuilder permissionBuilder;
    protected final LocaleRequestBuilder localeRequestBuilder;

    public SubCommand(@Nullable SubCommand parentCommand) {
        PluginPermissionBuilder parentPermissionBuilder = parentCommand != null ?
                parentCommand.getPermissionBuilder()
                :
                new PluginPermissionBuilder(null, NPluginApi.PLUGIN_NAME_LOWER)
                        .extend("command");

        LocaleRequestBuilder localeRequestBuilder = parentCommand != null ?
                parentCommand.getLocaleResultBuilder()
                :
                new LocaleRequestBuilder(null, "command");


        this.permissionBuilder = parentPermissionBuilder.extend(getName());
        this.localeRequestBuilder = localeRequestBuilder.extend(getName());

        subcommands = new SubCommandMap();
    }

    @NotNull
    public abstract String getName();

    @NotNull
    public PluginPermissionBuilder getPermissionBuilder() {
        return permissionBuilder;
    }

    @NotNull
    public LocaleRequestBuilder getLocaleResultBuilder() {
        return localeRequestBuilder;
    }

    public abstract boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args);

    public boolean tryExecute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (args.length >= 1 && subcommands.containsKey(args[0]))
            return subcommands.get(args[0]).tryExecute(sender, label, Arrays.copyOfRange(args, 1, args.length));

        if (!permissionBuilder.has(sender)) {
            locale.get("dont-have-permission").getSingle().sendMessage(sender);
            return false;
        }

        return execute(sender, label, args);
    }

    @NotNull List<String> tryTabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        if (!permissionBuilder.has(sender))
            return List.of();

        return tabComplete(sender, alias, args);
    }

    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 0)
            return ImmutableList.of();

        if (args.length == 1)
            return getAutocompletion(args[0], subcommands.keySet());

        if (subcommands.containsKey(args[0]))
            return subcommands.get(args[0]).tryTabComplete(sender, alias, Arrays.copyOfRange(args, 1, args.length));

        return ImmutableList.of();
    }

    @NotNull
    protected static List<String> getAutocompletion(@NotNull String arg, @NotNull Set<String> variants) {
        if (arg.isEmpty()) {
            return new ArrayList<>(variants);
        }
        List<String> matched = new ArrayList<>();

        for (String variant : variants) {
            if (StringUtil.startsWithIgnoreCase(variant, arg)) {
                matched.add(variant);
            }
        }

        return matched;
    }
}
