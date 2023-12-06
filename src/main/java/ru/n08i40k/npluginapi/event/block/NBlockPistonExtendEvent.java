package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockPistonExtendEvent extends NBlockEvent<BlockPistonExtendEvent> {

    public NBlockPistonExtendEvent(BlockPistonExtendEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onPistonExtend(this);
    }
}
