package ru.n08i40k.npluginapi.custom.enchantment;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.enchantments.CraftEnchantment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.slf4j.helpers.MessageFormatter;
import ru.n08i40k.npluginapi.plugin.NPlugin;
import ru.n08i40k.npluginapi.resource.INResourceKeyHolder;
import ru.n08i40k.npluginapi.resource.NResourceKey;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import static ru.n08i40k.npluginapi.custom.enchantment.NEnchantmentAccessor.getEnchantments;

@Getter
@NonNull
@SuppressWarnings("unused")
public class NEnchantment implements INResourceKeyHolder {
    private final NPlugin nPlugin;
    private final NResourceKey nResourceKey;

    private final net.minecraft.world.item.enchantment.Enchantment nmsEnchantment;
    private Enchantment enchantment = null;

    private final String name;

    public NEnchantment(@NonNull NPlugin nPlugin, @NonNull net.minecraft.world.item.enchantment.Enchantment nmsEnchantment, @NonNull String id, @NonNull String name) {
        this.nPlugin = nPlugin;
        this.nResourceKey = new NResourceKey(nPlugin, id);
        this.name = name;

        this.nmsEnchantment = nmsEnchantment;
    }

    public boolean hasEnchantment(@NonNull ItemStack itemStack) {
        return itemStack.getEnchantments().containsKey(enchantment);
    }

    public Optional<Integer> getLevel(@NonNull ItemStack itemStack) {
        Integer level = getEnchantments(itemStack).get(enchantment);
        return level == null ? Optional.empty() : Optional.of(level);
    }

    @SuppressWarnings("unchecked")
    private <T> Map<T, Enchantment> getEnchantmentMap(String fieldName) {
        try {
            Field field;

            field = Enchantment.class.getDeclaredField(fieldName);
            field.setAccessible(true);

            Object map = field.get(null);
            Preconditions.checkNotNull(map);

            return (Map<T, Enchantment>) map;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void register() {
//        BuiltInRegistries.ENCHANTMENT.
        Registry.register(BuiltInRegistries.ENCHANTMENT, nResourceKey.toMinecraft(), this.nmsEnchantment);
        this.enchantment = new CraftEnchantment(nResourceKey.getNamespacedKey(), this.nmsEnchantment);
    }

    public void unregister() {
        if (Bukkit.isStopping())
            return;
        throw new RuntimeException(
                MessageFormatter.format(
                        "Can't unregister {} NEnchantment from {} NPlugin because it's NMS enchantment, and it can't be unregistered!",
                        nResourceKey.getObjectId(),
                        nPlugin.getId()
                ).getMessage());
    }
}
