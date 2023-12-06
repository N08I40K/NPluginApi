package ru.n08i40k.npluginapi.event.itemStack;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.inventory.ItemStack;
import ru.n08i40k.npluginapi.itemStack.NItemStack;

@Getter
@NonNull
public class NItemStackDropEvent extends NItemStackEvent<EntityDropItemEvent> {

    public NItemStackDropEvent(EntityDropItemEvent bukkitEvent, ItemStack itemStack, NItemStack nItemStack) {
        super(bukkitEvent, itemStack, nItemStack);
    }

    @Override
    public void execute() {
        nItemStack.onDrop(this);
    }
}
