package ru.n08i40k.npluginapi.listener;

import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.*;

import ru.n08i40k.npluginapi.event.itemStack.*;

public class NItemStackEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInteractEntity(PlayerInteractEvent event) {
        NItemStackEvent.post(NItemStackInteractEvent.class, event.getItem(), event);
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        NItemStackEvent.post(NItemStackInteractEntityEvent.class, event.getPlayer().getInventory().getItem(event.getHand()), event);
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInteractEntity(PlayerInteractAtEntityEvent event) {
        NItemStackEvent.post(NItemStackInteractAtEntityEvent.class, event.getPlayer().getInventory().getItem(event.getHand()), event);
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onItemBreak(PlayerItemBreakEvent event) {
        NItemStackEvent.post(NItemStackBreakEvent.class, event.getBrokenItem(), event);
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onItemConsume(PlayerItemConsumeEvent event) {
        NItemStackEvent.post(NItemStackConsumeEvent.class, event.getItem(), event);
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onItemDamage(PlayerItemDamageEvent event) {
        NItemStackEvent.post(NItemStackDamageEvent.class, event.getItem(), event);
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onItemHeld(PlayerItemHeldEvent event) {
        NItemStackEvent.post(NItemStackHeldEvent.class, event.getPlayer().getInventory().getItem(event.getPreviousSlot()), event);
        NItemStackEvent.post(NItemStackHeldEvent.class, event.getPlayer().getInventory().getItem(event.getNewSlot()), event);
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onItemMend(PlayerItemMendEvent event) {
        NItemStackEvent.post(NItemStackMendEvent.class, event.getItem(), event);
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPickupItem(EntityPickupItemEvent event) {
        NItemStackEvent.post(NItemStackPickupEvent.class, event.getItem().getItemStack(), event);
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDropItem(EntityDropItemEvent event) {
        NItemStackEvent.post(NItemStackDropEvent.class, event.getItemDrop().getItemStack(), event);
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onRemoveFromWorld(EntityRemoveFromWorldEvent event) {
        if (!(event.getEntity() instanceof Item item))
            return;

        NItemStackEvent.post(NItemStackRemoveFromWorldEvent.class, item.getItemStack(), event);
    }
}
