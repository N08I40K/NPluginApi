package ru.n08i40k.npluginapi.event.itemStack;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import ru.n08i40k.npluginapi.custom.itemStack.NItemStack;

@Getter
@NonNull
public class NItemStackPickupEvent extends NItemStackEvent<EntityPickupItemEvent> {

    public NItemStackPickupEvent(EntityPickupItemEvent bukkitEvent, ItemStack itemStack, NItemStack nItemStack) {
        super(bukkitEvent, itemStack, nItemStack);
    }

    @Override
    public void execute() {
        nItemStack.onPickup(this);
    }
}
