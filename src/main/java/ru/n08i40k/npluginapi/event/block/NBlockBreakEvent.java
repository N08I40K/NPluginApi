package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockBreakEvent extends NBlockEvent<BlockBreakEvent> {

    public NBlockBreakEvent(BlockBreakEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onBreak(this);
    }
}
