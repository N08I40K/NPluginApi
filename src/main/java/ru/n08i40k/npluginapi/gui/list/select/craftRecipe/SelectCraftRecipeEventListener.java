package ru.n08i40k.npluginapi.gui.list.select.craftRecipe;

import de.tr7zw.nbtapi.NBTCompound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.custom.craft.NCraftRecipe;
import ru.n08i40k.npluginapi.gui.list.select.registry.SelectRegistryEventListener;
import ru.n08i40k.npluginapi.gui.list.view.ViewCraftRecipeGuiHolder;
import ru.n08i40k.npluginapi.resource.NResourceKey;

public class SelectCraftRecipeEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDrag(InventoryDragEvent event) {
        event.setCancelled(event.getInventory().getHolder() instanceof SelectCraftRecipeGuiHolder);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof SelectCraftRecipeGuiHolder))
            return;

        NBTCompound nbtCompound = SelectRegistryEventListener.clickCheck(event);
        if (nbtCompound == null) return;

        String craftRecipeId = nbtCompound.getString("craftRecipeId");
        NResourceKey nResourceKey = NResourceKey.parse(craftRecipeId);

        NCraftRecipe nCraftRecipe =
                NPluginApi.getInstance().getNPluginManager().getNCraftRecipeRegistry().getNCraftRecipe(nResourceKey);

        event.getWhoClicked().openInventory(
                new ViewCraftRecipeGuiHolder(nCraftRecipe).getInventory());
    }
}
