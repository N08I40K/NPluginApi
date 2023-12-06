package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockPlaceEvent extends NBlockEvent<BlockPlaceEvent> {

    public NBlockPlaceEvent(BlockPlaceEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onPlace(this);
    }
}
