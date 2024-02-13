package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockFadeEvent;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockFadeEvent extends NBlockEvent<BlockFadeEvent> {

    public NBlockFadeEvent(BlockFadeEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onFade(this);
    }
}
