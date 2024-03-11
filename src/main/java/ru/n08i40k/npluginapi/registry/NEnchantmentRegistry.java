package ru.n08i40k.npluginapi.registry;

import lombok.NonNull;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.enchantment.Enchantment;
import ru.n08i40k.npluginapi.custom.enchantment.NEnchantment;
import ru.n08i40k.npluginapi.resource.NResourceKey;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Map;

public class NEnchantmentRegistry extends NRegistry<NEnchantment> {
    @SuppressWarnings("OptionalGetWithoutIsPresent")

    public NEnchantmentRegistry() {
        Field[] declaredFields = MappedRegistry.class.getDeclaredFields();

        Field unregisteredIntrusiveHoldersField = Arrays.stream(declaredFields)
                .filter(declaredField -> declaredField.getType() == Map.class)
                .reduce((first, second) -> second).get();
        unregisteredIntrusiveHoldersField.setAccessible(true);

        Field frozenField = Arrays.stream(declaredFields)
                .filter(declaredField -> declaredField.getType().isPrimitive())
                .reduce((first, second) -> first)
                .get();
        frozenField.setAccessible(true);

        try {
            unregisteredIntrusiveHoldersField.set(BuiltInRegistries.ENCHANTMENT, new IdentityHashMap<Enchantment, Holder.Reference<Enchantment>>());
            frozenField.set(BuiltInRegistries.ENCHANTMENT, false);
        } catch (IllegalAccessException e) {
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
