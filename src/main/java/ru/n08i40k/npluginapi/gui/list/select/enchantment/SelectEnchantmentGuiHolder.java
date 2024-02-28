package ru.n08i40k.npluginapi.gui.list.select.enchantment;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.custom.enchantment.NEnchantmentAccessor;
import ru.n08i40k.npluginapi.gui.list.select.plugin.SelectPluginGuiHolder;
import ru.n08i40k.npluginapi.plugin.NPlugin;
import ru.n08i40k.npluginapi.plugin.NPluginManager;
import ru.n08i40k.npluginapi.registry.NEnchantmentRegistry;
import ru.n08i40k.npluginlocale.LocaleRequestBuilder;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class SelectEnchantmentGuiHolder implements InventoryHolder {
    LocaleRequestBuilder localeRoot =
            new LocaleRequestBuilder(null, "gui.list.select_registry.enchantment");

    private final Inventory inventory;
    private final String pluginId;

    public SelectEnchantmentGuiHolder(@NonNull String pluginId) {
        this.pluginId = pluginId;

        NPluginManager nPluginManager = NPluginApi.getInstance().getNPluginManager();
        NPlugin nPlugin = nPluginManager.getNPlugin(pluginId);
        Preconditions.checkNotNull(nPlugin);

        NEnchantmentRegistry nEnchantmentRegistry = nPluginManager.getNEnchantmentRegistry();
        int count = SelectPluginGuiHolder.getCount(nEnchantmentRegistry, nPlugin);

        inventory = Bukkit.createInventory(this,
                (Math.floorDiv(count, 9) + Math.min(Math.floorMod(count, 9), 1)) * 9,
                localeRoot.get("title", nPlugin.getViewName()).getSingle().getC());

        AtomicInteger counter = new AtomicInteger();
        nEnchantmentRegistry.getData().forEach((nResourceKey, nEnchantment) -> {
            if (!nResourceKey.getNPlugin().equals(nPlugin))
                return;

            ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
            NEnchantmentAccessor.addEnchantment(itemStack, nEnchantment, nEnchantment.getNmsEnchantment().getMaxLevel(), true);

            inventory.setItem(counter.getAndIncrement(), itemStack);
        });
    }
}
