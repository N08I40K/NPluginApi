package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockFertilizeEvent;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockFertilizeEvent extends NBlockEvent<BlockFertilizeEvent> {

    public NBlockFertilizeEvent(BlockFertilizeEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onFertilize(this);
    }
}
