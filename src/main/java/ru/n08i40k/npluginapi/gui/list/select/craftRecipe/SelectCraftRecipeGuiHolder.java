package ru.n08i40k.npluginapi.gui.list.select.craftRecipe;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.gui.list.select.plugin.SelectPluginGuiHolder;
import ru.n08i40k.npluginapi.plugin.NPlugin;
import ru.n08i40k.npluginapi.plugin.NPluginManager;
import ru.n08i40k.npluginapi.registry.NCraftRecipeRegistry;
import ru.n08i40k.npluginlocale.LocaleRequestBuilder;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class SelectCraftRecipeGuiHolder implements InventoryHolder {
    LocaleRequestBuilder localeRoot =
            new LocaleRequestBuilder(null, "gui.list.select_registry.craftRecipe");

    private final Inventory inventory;
    private final String pluginId;

    public SelectCraftRecipeGuiHolder(@NonNull String pluginId) {
        this.pluginId = pluginId;

        NPluginManager nPluginManager = NPluginApi.getInstance().getNPluginManager();
        NPlugin nPlugin = nPluginManager.getNPlugin(pluginId);
        Preconditions.checkNotNull(nPlugin);

        NCraftRecipeRegistry nCraftRecipeRegistry = nPluginManager.getNCraftRecipeRegistry();
        int count = SelectPluginGuiHolder.getCount(nCraftRecipeRegistry, nPlugin);

        inventory = Bukkit.createInventory(this,
                (Math.floorDiv(count, 9) + Math.min(Math.floorMod(count, 9), 1)) * 9,
                localeRoot.get("title", nPlugin.getViewName()).getSingle().getC());

        AtomicInteger counter = new AtomicInteger();
        nCraftRecipeRegistry.getData().forEach((nResourceKey, nCraftRecipe) -> {
            if (!nResourceKey.getNPlugin().equals(nPlugin))
                return;

            NBTItem nbtItem = new NBTItem(nCraftRecipe.getNItemStack().getItemStack());
            nbtItem.getOrCreateCompound("nplugin-api").setString("craftRecipeId", nCraftRecipe.getNResourceKey().toString());

            inventory.setItem(counter.getAndIncrement(), nbtItem.getItem());
        });
    }
}
