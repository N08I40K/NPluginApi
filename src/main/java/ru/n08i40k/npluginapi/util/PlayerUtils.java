package ru.n08i40k.npluginapi.util;

import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtils {
    public static void giveItemOrDrop(@NonNull Player player, @NonNull List<ItemStack> itemStacks) {
        List<ItemStack> remainingItems = new ArrayList<>();

        for (ItemStack itemStack : itemStacks)
            remainingItems.addAll(player.getInventory().addItem(itemStack).values());

        if (remainingItems.isEmpty())
            return;

        Location location = player.getLocation();
        World world = location.getWorld();

        for (ItemStack itemStack : remainingItems)
            world.dropItemNaturally(location, itemStack);
    }

    public static void giveItemOrDrop(@NonNull Player player, @NonNull ItemStack itemStack) {
        giveItemOrDrop(player, List.of(itemStack));
    }
}
