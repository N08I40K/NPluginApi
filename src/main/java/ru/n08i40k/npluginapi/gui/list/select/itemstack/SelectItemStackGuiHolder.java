package ru.n08i40k.npluginapi.gui.list.select.itemstack;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.registry.NItemStackRegistry;
import ru.n08i40k.npluginapi.gui.list.select.plugin.SelectPluginGuiHolder;
import ru.n08i40k.npluginapi.plugin.NPlugin;
import ru.n08i40k.npluginapi.plugin.NPluginManager;
import ru.n08i40k.npluginlocale.LocaleRequestBuilder;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class SelectItemStackGuiHolder implements InventoryHolder {
    LocaleRequestBuilder localeRoot =
            new LocaleRequestBuilder(null, "gui.list.select_registry.itemstack");

    private final Inventory inventory;
    private final String pluginId;

    public SelectItemStackGuiHolder(@NonNull String pluginId) {
        this.pluginId = pluginId;

        NPluginManager nPluginManager = NPluginApi.getInstance().getNPluginManager();
        NPlugin nPlugin = nPluginManager.getNPlugin(pluginId);
        Preconditions.checkNotNull(nPlugin);

        NItemStackRegistry nItemStackRegistry = nPluginManager.getNItemStackRegistry();
        int count = SelectPluginGuiHolder.getCount(nItemStackRegistry, nPlugin);

        inventory = Bukkit.createInventory(this,
                (Math.floorDiv(count, 9) + Math.min(Math.floorMod(count, 9), 1)) * 9,
                localeRoot.get("title", nPlugin.getViewName()).getSingle().getC());

        AtomicInteger counter = new AtomicInteger();
        nItemStackRegistry.getData().forEach((nResourceKey, nItemStack) -> {
            if (!nResourceKey.getNPlugin().equals(nPlugin))
                return;

            inventory.setItem(counter.getAndIncrement(), nItemStack.getItemStack());
        });
    }
}
