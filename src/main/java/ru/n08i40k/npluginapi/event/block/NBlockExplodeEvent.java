package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockExplodeEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockExplodeEvent extends NBlockEvent<BlockExplodeEvent> {

    public NBlockExplodeEvent(BlockExplodeEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onExplode(this);
    }
}
