package ru.n08i40k.npluginapi.registry;

import lombok.Getter;
import lombok.NonNull;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import ru.n08i40k.npluginapi.custom.itemStack.NItemStack;
import ru.n08i40k.npluginapi.resource.NResourceKey;

@Getter
public class NItemStackRegistry extends NRegistry<NItemStack> {
    private final @NonNull Registry<Item> vanillaRegistry;

    public NItemStackRegistry() {
        vanillaRegistry = ((CraftServer) Bukkit.getServer())
                .getServer()
                .registryAccess()
                .registryOrThrow(Registries.ITEM);
    }

    public NItemStack getNItemStack(@NonNull NResourceKey nResourceKey) {
        return super.getElement(nResourceKey);
    }
}
