package ru.n08i40k.npluginapi.gui.list.select.itemstack;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTCompound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.craft.NCraftRecipe;
import ru.n08i40k.npluginapi.gui.list.select.registry.SelectRegistryEventListener;
import ru.n08i40k.npluginapi.gui.list.view.ViewCraftRecipeGuiHolder;
import ru.n08i40k.npluginapi.itemStack.NItemStack;
import ru.n08i40k.npluginapi.itemStack.NItemStackNBT;
import ru.n08i40k.npluginapi.resource.NResourceGroup;
import ru.n08i40k.npluginapi.resource.NResourceKey;

public class SelectItemStackEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDrag(InventoryDragEvent event) {
        event.setCancelled(event.getInventory().getHolder() instanceof SelectItemStackGuiHolder);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof SelectItemStackGuiHolder))
            return;

        NBTCompound nbtCompound = SelectRegistryEventListener.clickCheck(event);
        if (nbtCompound == null) return;

        NItemStack nItemStack = NItemStackNBT.getNItemStack(nbtCompound);

        Preconditions.checkNotNull(nItemStack.getNResourceKey());
        NResourceKey nResourceKey =
                new NResourceKey(nItemStack.getNPlugin(), NResourceGroup.CRAFT, nItemStack.getNResourceKey().getObjectId());

        NCraftRecipe nCraftRecipe = NPluginApi.getInstance().getNPluginManager().getNCraftRecipeRegistry().getData().get(nResourceKey);
        if (nCraftRecipe == null)
            return;

        event.getWhoClicked().openInventory(
                new ViewCraftRecipeGuiHolder(nCraftRecipe).getInventory());
    }
}