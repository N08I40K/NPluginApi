package ru.n08i40k.npluginapi.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import ru.n08i40k.npluginapi.event.block.NBlockEvent;
import ru.n08i40k.npluginapi.event.block.NBlockPlaceEvent;
import ru.n08i40k.npluginapi.event.itemStack.NItemStackBlockPlaceEvent;
import ru.n08i40k.npluginapi.event.itemStack.NItemStackEvent;

public class NSharedEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent event) {
        NItemStackEvent.post(NItemStackBlockPlaceEvent.class, event.getItemInHand(), event);
        NBlockEvent.post(NBlockPlaceEvent.class, event.getBlock(), event);
    }
}
