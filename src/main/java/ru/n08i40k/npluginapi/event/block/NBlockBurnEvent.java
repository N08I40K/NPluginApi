package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBurnEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockBurnEvent extends NBlockEvent<BlockBurnEvent> {

    public NBlockBurnEvent(BlockBurnEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onBurn(this);
    }
}
