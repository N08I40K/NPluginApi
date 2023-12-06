package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockGrowEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockGrowEvent extends NBlockEvent<BlockGrowEvent> {

    public NBlockGrowEvent(BlockGrowEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onGrow(this);
    }
}
