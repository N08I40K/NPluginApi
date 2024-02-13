package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.world.ChunkUnloadEvent;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockChunkUnloadEvent extends NBlockEvent<ChunkUnloadEvent> {

    public NBlockChunkUnloadEvent(ChunkUnloadEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onChunkUnload(this);
    }
}
