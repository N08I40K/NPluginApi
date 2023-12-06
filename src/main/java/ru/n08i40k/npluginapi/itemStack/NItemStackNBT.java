package ru.n08i40k.npluginapi.itemStack;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import lombok.NonNull;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.database.NItemStackRegistry;
import ru.n08i40k.npluginapi.plugin.NPluginManager;
import ru.n08i40k.npluginapi.resource.NResourceKey;

public class NItemStackNBT {
    @Nullable
    public static NBTCompound getNBTCompound(@NonNull ItemStack itemStack) {
        NBTItem nbtItem = new NBTItem(itemStack);

        if (!nbtItem.hasTag("nplugin-api"))
            return null;

        return nbtItem.getCompound("nplugin-api");
    }

    @NonNull
    public static NResourceKey getId(@NonNull NBTCompound nbtCompound) {
        Preconditions.checkState(nbtCompound.hasTag("resourceKey"), "Corrupted NBTCompound!");

        return NResourceKey.parse(nbtCompound.getString("resourceKey"));
    }

    @NonNull
    public static NItemStack getNItemStack(@NonNull NBTCompound nbtCompound) {
        return getNItemStack(getId(nbtCompound));
    }

    @NonNull
    public static NItemStack getNItemStack(@NonNull NResourceKey nResourceKey) {
        NPluginManager nPluginManager = NPluginApi.getInstance().getNPluginManager();
        NItemStackRegistry nItemStackRegistry = nPluginManager.getNItemStackRegistry();
        Preconditions.checkState(nItemStackRegistry.contains(nResourceKey),
                "Can't find NItemStack with id %s!", nResourceKey);

        return nItemStackRegistry.getNItemStack(nResourceKey);
    }

    @NonNull
    public static ItemStack applyNBTCompound(@NonNull NItemStack nItemStack) {
        ItemStack itemStack = nItemStack.getSourceItemStack();

        if (nItemStack.getNResourceKey() == null)
            return itemStack;

        NBTItem nbtItem = new NBTItem(itemStack);

        NBTCompound nbtCompound = nbtItem.addCompound("nplugin-api");
        nbtCompound.setString("resourceKey", nItemStack.getNResourceKey().toString());

        return nbtItem.getItem();
    }
}
