package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockIgniteEvent;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockIgniteEvent extends NBlockEvent<BlockIgniteEvent> {

    public NBlockIgniteEvent(BlockIgniteEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onIgnite(this);
    }
}
