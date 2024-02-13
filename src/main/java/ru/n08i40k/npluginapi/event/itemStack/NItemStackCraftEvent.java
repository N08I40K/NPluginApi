package ru.n08i40k.npluginapi.event.itemStack;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import ru.n08i40k.npluginapi.custom.itemStack.NItemStack;

@Getter
@NonNull
public class NItemStackCraftEvent extends NItemStackEvent<CraftItemEvent> {
    public NItemStackCraftEvent(CraftItemEvent bukkitEvent, ItemStack itemStack, NItemStack nItemStack) {
        super(bukkitEvent, itemStack, nItemStack);
    }

    @Override
    public void execute() {
        nItemStack.onCraft(this);
    }
}
