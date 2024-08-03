package ru.n08i40k.npluginapi.registry;

import lombok.Getter;
import lombok.NonNull;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import org.slf4j.Logger;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.custom.enchantment.NEnchantment;
import ru.n08i40k.npluginapi.resource.NResourceKey;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;


@Getter
public class NEnchantmentRegistry extends NRegistry<NEnchantment> {
    private final @NonNull Registry<Enchantment> vanillaRegistry;

    public NEnchantmentRegistry() {
        Logger logger = NPluginApi.getInstance().getSLF4JLogger();

        Field[] declaredFields;
        try {
            declaredFields = MappedRegistry.class.getDeclaredFields();
        } catch (SecurityException e) {
            logger.error("Can't access to Enchantments Registry field list!");
            throw new RuntimeException(e);
        }

        Optional<Field> unregisteredIntrusiveHoldersField = Arrays.stream(declaredFields)
                .filter(declaredField -> declaredField.getType() == Map.class)
                .reduce((first, second) -> second);
        assert unregisteredIntrusiveHoldersField.isPresent();
        unregisteredIntrusiveHoldersField.get().setAccessible(true);

        Optional<Field> frozenField = Arrays.stream(declaredFields)
                .filter(declaredField -> declaredField.getType().isPrimitive())
                .reduce((first, second) -> first);
        assert frozenField.isPresent();
        frozenField.get().setAccessible(true);

        vanillaRegistry = ((CraftServer) Bukkit.getServer())
                .getServer()
                .registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT);

        try {
            unregisteredIntrusiveHoldersField
                    .get().set(vanillaRegistry, new IdentityHashMap<Enchantment, Holder.Reference<Enchantment>>());
            frozenField
                    .get().set(vanillaRegistry, false);
        } catch (IllegalAccessException e) {
            logger.error("Can't access to Enchantments Registry field!");
            throw new RuntimeException(e);
        }
    }

    public NEnchantment getNEnchantment(@NonNull NResourceKey nResourceKey) {
        return super.getElement(nResourceKey);
    }

    @Override
    public NEnchantment add(@NonNull NEnchantment enchantment) {
        super.add(enchantment);
        enchantment.register();

        return enchantment;
    }

    @Override
    public NEnchantment remove(@NonNull NResourceKey nResourceKey) {
        NEnchantment nEnchantment = super.remove(nResourceKey);
        nEnchantment.unregister();

        return nEnchantment;
    }
}
