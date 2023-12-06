package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockCookEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockCookEvent extends NBlockEvent<BlockCookEvent> {

    public NBlockCookEvent(BlockCookEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onCook(this);
    }
}
