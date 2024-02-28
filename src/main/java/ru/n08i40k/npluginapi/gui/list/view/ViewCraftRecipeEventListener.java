package ru.n08i40k.npluginapi.gui.list.view;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.Plugin;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.gui.list.select.itemStack.SelectItemStackGuiHolder;

public class ViewCraftRecipeEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDrag(InventoryDragEvent event) {
        event.setCancelled(event.getInventory().getHolder() instanceof ViewCraftRecipeGuiHolder);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof ViewCraftRecipeGuiHolder))
            return;

        if (event.getClickedInventory() != event.getInventory()) {
            if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY)
                event.setCancelled(true);
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof ViewCraftRecipeGuiHolder holder))
            return;

        Plugin plugin = NPluginApi.getInstance();
        plugin.getServer().getScheduler().runTaskLater(plugin, () ->
                event.getPlayer().openInventory(
                        new SelectItemStackGuiHolder(
                                holder.getPluginId()).getInventory()), 1);
    }
}
