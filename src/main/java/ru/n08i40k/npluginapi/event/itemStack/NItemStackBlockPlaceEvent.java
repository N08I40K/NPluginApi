package ru.n08i40k.npluginapi.event.itemStack;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import ru.n08i40k.npluginapi.custom.itemStack.NItemStack;

public class NItemStackBlockPlaceEvent extends NItemStackEvent<BlockPlaceEvent> {
    public NItemStackBlockPlaceEvent(BlockPlaceEvent bukkitEvent, ItemStack itemStack, NItemStack nItemStack) {
        super(bukkitEvent, itemStack, nItemStack);
    }

    @Override
    public void execute() {
        nItemStack.onBlockPlace(this);
    }
}
