package ru.n08i40k.npluginapi.command.subcommands.admin;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.command.SubCommand;
import ru.n08i40k.npluginapi.custom.enchantment.NEnchantment;
import ru.n08i40k.npluginapi.custom.enchantment.NEnchantmentAccessor;
import ru.n08i40k.npluginapi.registry.NEnchantmentRegistry;
import ru.n08i40k.npluginapi.resource.NResourceKey;
import ru.n08i40k.npluginapi.util.PlayerUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EnchantCommand extends SubCommand {
    public EnchantCommand(@Nullable SubCommand parent) {
        super(parent);
    }

    @Override
    public @NotNull String getName() {
        return "enchant";
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        NEnchantmentRegistry nEnchantmentRegistry = plugin.getNPluginManager().getNEnchantmentRegistry();

        Set<String> enchantments = nEnchantmentRegistry.getData().values().stream()
                .map(nEnchantment -> nEnchantment.getNResourceKey().toString())
                .collect(Collectors.toSet());

        if (args.length == 0) {
            localeRequestBuilder.get("select-enchantments", String.join(", ", enchantments))
                    .getSingle().sendMessage(sender);
            return false;
        }

        String enchantmentName = args[0];

        if (!enchantments.contains(enchantmentName)) {
            localeRequestBuilder.get("incorrect-enchantment", String.join(", ", enchantments))
                    .getSingle().sendMessage(sender);
            return false;
        }

        NEnchantment nEnchantment = nEnchantmentRegistry.getNEnchantment(NResourceKey.parse(enchantmentName));

        int level = args.length > 1 ? Integer.parseInt(args[1]) : 1;
        boolean unsafe = level < 0;

        if (unsafe)
            level = -level;

        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();
        ItemStack itemInMainHand = inventory.getItemInMainHand();

        if (itemInMainHand.getType() == Material.AIR) {
            localeRequestBuilder.get("incorrect-item")
                    .getSingle().sendMessage(sender);
            return false;
        }

        ItemStack resultItem = itemInMainHand.clone();

        if (itemInMainHand.getType() == Material.BOOK) {
            resultItem.setType(Material.ENCHANTED_BOOK);
            resultItem.setAmount(1);
            itemInMainHand.setAmount(itemInMainHand.getAmount() - 1);
        } else
            itemInMainHand.setAmount(0);

        NEnchantmentAccessor.addEnchantment(resultItem, nEnchantment, level, unsafe);

        if (itemInMainHand.getAmount() == 0)
            inventory.setItemInMainHand(resultItem);
        else
            PlayerUtils.giveItemOrDrop(player, resultItem);

        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            NEnchantmentRegistry nEnchantmentRegistry = plugin.getNPluginManager().getNEnchantmentRegistry();

            Set<String> enchantments = nEnchantmentRegistry.getData().values().stream()
                    .map(nEnchantment -> nEnchantment.getNResourceKey().toString())
                    .collect(Collectors.toSet());

            return getAutocompletion(args[0], enchantments);
        }
        return List.of();
    }
}
