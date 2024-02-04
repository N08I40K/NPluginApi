package ru.n08i40k.npluginapi.gui.list.select.registry;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.gui.list.select.itemstack.SelectItemStackGuiHolder;
import ru.n08i40k.npluginapi.gui.list.select.plugin.SelectPluginGuiHolder.Registry;

public class SelectRegistryEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDrag(InventoryDragEvent event) {
        event.setCancelled(event.getInventory().getHolder() instanceof SelectRegistryGuiHolder);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof SelectRegistryGuiHolder holder))
            return;

        NBTCompound nbtCompound = clickCheck(event);
        if (nbtCompound == null) return;

        String registryName = nbtCompound.getString("registry");
        Preconditions.checkNotNull(registryName);

        Registry registry = Registry.valueOf(registryName);

        InventoryHolder inventoryHolder = null;

        switch (registry) {
            case ITEMSTACK -> {
                inventoryHolder = new SelectItemStackGuiHolder(holder.getPluginId());
            }
            default -> {
                // unimplemented
            }
        }

        if (inventoryHolder != null)
            event.getWhoClicked().openInventory(inventoryHolder.getInventory());
    }

    @Nullable
    public static NBTCompound clickCheck(InventoryClickEvent event) {
        if (event.getClickedInventory() != event.getInventory()) {
            if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY)
                event.setCancelled(true);
            return null;
        }
        event.setCancelled(true);

        if (event.getAction() != InventoryAction.PICKUP_ALL)
            return null;

        ItemStack itemStack = event.getCurrentItem();

        if (itemStack == null || itemStack.getType() == Material.AIR)
            return null;

        NBTItem nbtItem = new NBTItem(itemStack);

        NBTCompound nbtCompound = nbtItem.getCompound("nplugin-api");
        Preconditions.checkNotNull(nbtCompound);
        return nbtCompound;
    }

}
