package ru.n08i40k.npluginapi.gui.list.select.plugin;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTCompound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.InventoryHolder;
import ru.n08i40k.npluginapi.gui.list.select.itemstack.SelectItemStackGuiHolder;
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

        String pluginId = nbtCompound.getString("getPlugin-getId");
        Preconditions.checkNotNull(pluginId);

        InventoryHolder inventoryHolder = null;

        if (holder.getPredefinedRegistry() == null)
            inventoryHolder = new SelectRegistryGuiHolder(pluginId);
        else {
            switch (holder.getPredefinedRegistry()) {
                case ITEMSTACK -> {
                    inventoryHolder = new SelectItemStackGuiHolder(pluginId);
                }
                default -> {
                    // unimplemented
                }
            }
        }

        if (inventoryHolder != null)
            event.getWhoClicked().openInventory(inventoryHolder.getInventory());
    }

}
