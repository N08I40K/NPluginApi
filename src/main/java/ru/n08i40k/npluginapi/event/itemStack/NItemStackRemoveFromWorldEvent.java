package ru.n08i40k.npluginapi.event.itemStack;

import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;
import org.bukkit.inventory.ItemStack;
import ru.n08i40k.npluginapi.itemStack.NItemStack;

public class NItemStackRemoveFromWorldEvent extends NItemStackEvent<EntityRemoveFromWorldEvent> {

    public NItemStackRemoveFromWorldEvent(EntityRemoveFromWorldEvent bukkitEvent, ItemStack itemStack, NItemStack nItemStack) {
        super(bukkitEvent, itemStack, nItemStack);
    }

    @Override
    public void execute() {
        nItemStack.onRemoveFromWorld(this);
    }
}
