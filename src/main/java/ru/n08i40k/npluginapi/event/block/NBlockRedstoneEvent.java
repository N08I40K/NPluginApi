package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockRedstoneEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockRedstoneEvent extends NBlockEvent<BlockRedstoneEvent> {

    public NBlockRedstoneEvent(BlockRedstoneEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onRedstone(this);
    }
}
