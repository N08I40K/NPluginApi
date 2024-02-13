package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.world.ChunkLoadEvent;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockChunkLoadEvent extends NBlockEvent<ChunkLoadEvent> {

    public NBlockChunkLoadEvent(ChunkLoadEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onChunkLoad(this);
    }
}
