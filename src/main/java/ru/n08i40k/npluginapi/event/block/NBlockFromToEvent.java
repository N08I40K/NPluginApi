package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockFromToEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockFromToEvent extends NBlockEvent<BlockFromToEvent> {

    public NBlockFromToEvent(BlockFromToEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onFromTo(this);
    }
}
