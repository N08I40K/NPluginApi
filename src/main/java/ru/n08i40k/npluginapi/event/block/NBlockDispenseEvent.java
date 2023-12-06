package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockDispenseEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockDispenseEvent extends NBlockEvent<BlockDispenseEvent> {

    public NBlockDispenseEvent(BlockDispenseEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onDispense(this);
    }
}
