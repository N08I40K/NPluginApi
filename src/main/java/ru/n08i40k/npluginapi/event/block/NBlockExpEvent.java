package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExpEvent;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockExpEvent extends NBlockEvent<BlockExpEvent> {

    public NBlockExpEvent(BlockExpEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }
    public NBlockExpEvent(BlockBreakEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onExp(this);
    }
}
