package ru.n08i40k.npluginapi.custom.enchantment;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import ru.n08i40k.npluginapi.NPluginApi;

import java.util.*;

@SuppressWarnings("unused")
public class NEnchantmentAccessor {
    @NonNull
    public static Map<Enchantment, Integer> getEnchantments(@NonNull ItemStack itemStack) {
        if (!itemStack.getEnchantments().isEmpty())
            return itemStack.getEnchantments();

        // TODO: Миграция на альтернативный метод получения NBT-тегов.
        @SuppressWarnings("deprecation")
        NBTItem nbtItem = new NBTItem(itemStack);

        if (!nbtItem.hasTag("StoredEnchantments"))
            return Map.of();

        Map<Enchantment, Integer> enchantments = new HashMap<>();
        for (ReadWriteNBT nbtEnchantment : nbtItem.getCompoundList("StoredEnchantments")) {
            String[] enchantmentId = nbtEnchantment.getString("id").split(":");
            Integer enchantmentLevel = nbtEnchantment.getInteger("lvl");

            @SuppressWarnings("deprecation")
            Enchantment enchantment = Enchantment.getByKey(new NamespacedKey(enchantmentId[0], enchantmentId[1]));

            enchantments.put(enchantment, enchantmentLevel);
        }

        return enchantments;
    }

    @NonNull
    public static Map<NEnchantment, Integer> getNEnchantments(@NonNull ItemStack itemStack) {
        Map<NEnchantment, Integer> enchantments = new HashMap<>();

        Collection<NEnchantment> nEnchantments =
                NPluginApi.getInstance().getNPluginManager().getNEnchantmentRegistry().getData().values();

        Map<Enchantment, Integer> itemStackEnchantments = getEnchantments(itemStack);

        for (NEnchantment nEnchantment : nEnchantments) {
            Enchantment enchantment = nEnchantment.getEnchantment();

            if (itemStackEnchantments.containsKey(enchantment))
                enchantments.put(nEnchantment, itemStackEnchantments.get(enchantment));
        }

        return enchantments;
    }

    @NonNull
    public static Map<NEnchantment, Integer> clearNEnchantments(@NonNull ItemStack itemStack) {
        Map<NEnchantment, Integer> enchantments = new HashMap<>();

        Collection<NEnchantment> nEnchantments =
                NPluginApi
                        .getInstance()
                        .getNPluginManager()
                        .getNEnchantmentRegistry()
                        .getData()
                        .values();
        HashMap<Enchantment, NEnchantment> nEnchantmentsTranslation = new HashMap<>();
        for (NEnchantment nEnchantment : nEnchantments)
            nEnchantmentsTranslation.put(nEnchantment.getEnchantment(), nEnchantment);

        Map<Enchantment, Integer> includedEnchantments = getEnchantments(itemStack);
        Map<NEnchantment, Integer> excludedNEnchantments = new HashMap<>();

        for (Map.Entry<Enchantment, Integer> enchantment : includedEnchantments.entrySet()) {
            if (nEnchantmentsTranslation.containsKey(enchantment.getKey()))
                excludedNEnchantments.put(nEnchantmentsTranslation.get(enchantment.getKey()), enchantment.getValue());
            else
                includedEnchantments.put(enchantment.getKey(), enchantment.getValue());
        }

        itemStack.getEnchantments().clear();
        itemStack.getEnchantments().putAll(includedEnchantments);

        return excludedNEnchantments;
    }

    public static void addEnchantment(@NonNull ItemStack itemStack, @NonNull NEnchantment nEnchantment, int level, boolean unsafe) {
        Preconditions.checkState(level > 0, "Level must be more than 0!", nEnchantment.getNResourceKey());
        Preconditions.checkState(level < 256, "Level must be less than 256!", nEnchantment.getNResourceKey());

        Enchantment enchantment = nEnchantment.getEnchantment();

        if (itemStack.getType() == Material.ENCHANTED_BOOK) {
            // TODO: Миграция на альтернативный метод модификации NBT-тегов.
            @SuppressWarnings("deprecation")
            NBTItem nbtItem = new NBTItem(itemStack);

            if (!nbtItem.hasTag("StoredEnchantments"))
                nbtItem.addCompound("StoredEnchantments");
            NBTCompoundList nbtCompoundList = nbtItem.getCompoundList("StoredEnchantments");

            NBTListCompound nbtListCompound = nbtCompoundList.addCompound();
            nbtListCompound.setString("id", nEnchantment.getNResourceKey().toString());
            nbtListCompound.setInteger("lvl", level);

            itemStack.setItemMeta(nbtItem.getItem().getItemMeta());

            return;
        }

        if (!enchantment.canEnchantItem(itemStack))
            return;

        if (itemStack
                .getEnchantments()
                .keySet()
                .stream()
                .anyMatch(enchantment1 ->
                        enchantment1.conflictsWith(enchantment) || enchantment.conflictsWith(enchantment1)))
            return;

        if (unsafe)
            itemStack.addUnsafeEnchantment(enchantment, level);
        else
            itemStack.addEnchantment(enchantment, level);
    }

    public static void removeEnchantment(@NonNull ItemStack itemStack, @NonNull NEnchantment nEnchantment) {
        itemStack.removeEnchantment(nEnchantment.getEnchantment());
    }
}
