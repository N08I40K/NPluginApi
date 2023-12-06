package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockShearEntityEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockShearEntityEvent extends NBlockEvent<BlockShearEntityEvent> {

    public NBlockShearEntityEvent(BlockShearEntityEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onShearEntity(this);
    }
}
