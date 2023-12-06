package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockDropItemEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockDropItemEvent extends NBlockEvent<BlockDropItemEvent> {

    public NBlockDropItemEvent(BlockDropItemEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onDropItem(this);
    }
}
