package ru.n08i40k.npluginapi.event.itemStack;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import ru.n08i40k.npluginapi.custom.itemStack.NItemStack;

@Getter
@NonNull
public class NItemStackDamageEntityEvent extends NItemStackEvent<EntityDamageByEntityEvent> {

    public NItemStackDamageEntityEvent(EntityDamageByEntityEvent bukkitEvent, ItemStack itemStack, NItemStack nItemStack) {
        super(bukkitEvent, itemStack, nItemStack);
    }

    @Override
    public void execute() {
        nItemStack.onDamageEntity(this);
    }
}
