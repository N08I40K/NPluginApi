package ru.n08i40k.npluginapi.gui.list.select.plugin;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTCompound;
import lombok.NonNull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import ru.n08i40k.npluginapi.gui.list.select.craftRecipe.SelectCraftRecipeGuiHolder;
import ru.n08i40k.npluginapi.gui.list.select.enchantment.SelectEnchantmentGuiHolder;
import ru.n08i40k.npluginapi.gui.list.select.itemStack.SelectItemStackGuiHolder;
import ru.n08i40k.npluginapi.gui.list.select.registry.SelectRegistryEventListener;
import ru.n08i40k.npluginapi.gui.list.select.registry.SelectRegistryGuiHolder;

public class SelectPluginEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDrag(InventoryDragEvent event) {
        event.setCancelled(event.getInventory().getHolder() instanceof SelectPluginGuiHolder);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof SelectPluginGuiHolder holder))
            return;

        NBTCompound nbtCompound = SelectRegistryEventListener.clickCheck(event);
        if (nbtCompound == null) return;

        String pluginId = nbtCompound.getString("nPluginId");
        Preconditions.checkNotNull(pluginId);

        Inventory inventory = holder.getPredefinedRegistry() == null ?
                new SelectRegistryGuiHolder(pluginId).getInventory() :
                getInventory(pluginId, holder.getPredefinedRegistry());

        event.getWhoClicked().openInventory(inventory);
    }

    @NonNull
    private static Inventory getInventory(String pluginId, SelectPluginGuiHolder.Registry registry) {
        InventoryHolder inventoryHolder;

        switch (registry) {
            case ITEMSTACK -> inventoryHolder = new SelectItemStackGuiHolder(pluginId);
            case CRAFT_RECIPE -> inventoryHolder = new SelectCraftRecipeGuiHolder(pluginId);
            case ENCHANTMENT -> inventoryHolder = new SelectEnchantmentGuiHolder(pluginId);
            default -> throw new RuntimeException("Behaviour for registry " + registry.name() + " not implemented!");
        }

        return inventoryHolder.getInventory();
    }

}
