package ru.n08i40k.npluginapi.custom.enchantment;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.util.RomanNumber;
import ru.n08i40k.npluginlocale.NColorUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@SuppressWarnings("unused")
public class NEnchantmentAccessor {
    public static Map<Enchantment, Integer> getEnchantments(@NonNull ItemStack itemStack) {
        if (!itemStack.getEnchantments().isEmpty())
            return itemStack.getEnchantments();

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

    public static void addEnchantment(@NonNull ItemStack itemStack, @NonNull NEnchantment nEnchantment, int level, boolean unsafe) {
        Preconditions.checkState(level > 0, "Level must be more than 0!", nEnchantment.getNResourceKey());
        Preconditions.checkState(level < 256, "Level must be less than 256!", nEnchantment.getNResourceKey());

        Enchantment enchantment = nEnchantment.getEnchantment();

        if (itemStack.getType() == Material.ENCHANTED_BOOK) {
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

        if (!enchantment.getItemTarget().includes(itemStack))
            return;

        if (!enchantment.canEnchantItem(itemStack))
            return;

        if (itemStack.getEnchantments().keySet().stream()
                .anyMatch(enchantment1 ->
                        enchantment1.conflictsWith(enchantment) || enchantment.conflictsWith(enchantment1)))
            return;

        if (unsafe)
            itemStack.addUnsafeEnchantment(enchantment, level);
        else
            itemStack.addEnchantment(enchantment, level);
    }

    public static void removeEnchantment(@NonNull ItemStack itemStack, @NonNull NEnchantment nEnchantment) {
        NEnchantedLore.clearLore(itemStack);

        itemStack.removeEnchantment(nEnchantment.getEnchantment());

        NEnchantedLore.setLore(itemStack);
    }

    public static IChatBaseComponent getFullNameNMS(NEnchantment nEnchantment, int level) {
        return getFullNameNMS(nEnchantment.getNmsEnchantment(), nEnchantment.getName(), level);
    }

    public static IChatBaseComponent getFullNameNMS(net.minecraft.server.v1_16_R3.Enchantment enchantment, String name, int level) {
        if (level > 1 || enchantment.getMaxLevel() > 1)
            name += " " + RomanNumber.toRoman(level);

        IChatMutableComponent message = new ChatMessage(name);
        message.a(enchantment.c() ? EnumChatFormat.RED : EnumChatFormat.GRAY);

        return message;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static class NEnchantedLore {
        private static String getNameHexColor(@NonNull ChatHexColor chatHexColor) {
            try {
                Method getColor = ChatHexColor.class.getDeclaredMethod("c");
                getColor.setAccessible(true);
                return "&" + getColor.invoke(chatHexColor);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        public static List<Component> getEnchantmentsLore(@NonNull ItemStack itemStack) {
            List<Component> lore = new ArrayList<>();

            Map<NEnchantment, Integer> enchantments = getNEnchantments(itemStack);

            enchantments.forEach((nEnchantment, level) -> {
                StringBuilder stringBuilder = new StringBuilder("&#");

                net.minecraft.server.v1_16_R3.Enchantment nmsEnchantment = nEnchantment.getNmsEnchantment();
                stringBuilder.append(nmsEnchantment.c() ? "ff5555" : "aaaaaa");
                stringBuilder.append(nEnchantment.getName());

                if (nmsEnchantment.getMaxLevel() > 1 || level > 1) {
                    stringBuilder.append(" ");
                    stringBuilder.append(RomanNumber.toRoman(level));
                }

                lore.add(NColorUtils.translate(
                        NColorUtils.parseGradientTags(stringBuilder.toString())
                        , false)
                );
            });

            return lore;
        }

        public static boolean clearLore(@NonNull ItemStack itemStack) {
            ItemMeta itemMeta = itemStack.getItemMeta();

            List<Component> lore = itemMeta.lore();
            if (lore == null)
                return false;

            List<Component> enchantmentLore = getEnchantmentsLore(itemStack);
            if (enchantmentLore.isEmpty())
                return false;

            List<Component> newLore = new ArrayList<>();
            boolean hasEnchantment = false;

            for (Component line : lore) {
                if (!enchantmentLore.contains(line))
                    newLore.add(line);
                else
                    hasEnchantment = true;
            }

            itemMeta.lore(newLore);
            itemStack.setItemMeta(itemMeta);

            return hasEnchantment;
        }

        public static boolean setLore(@NonNull ItemStack itemStack) {
            ItemMeta itemMeta = itemStack.getItemMeta();

            List<Component> lore = itemMeta.lore();
            if (lore == null)
                lore = new ArrayList<>();

            List<Component> enchantmentsLore = getEnchantmentsLore(itemStack);
            if (enchantmentsLore.isEmpty())
                return false;

            lore.addAll(enchantmentsLore);

            itemMeta.lore(lore);
            itemStack.setItemMeta(itemMeta);

            return !enchantmentsLore.isEmpty();
        }
    }
}
