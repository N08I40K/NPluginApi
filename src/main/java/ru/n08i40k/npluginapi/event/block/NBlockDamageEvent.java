package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockDamageEvent;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockDamageEvent extends NBlockEvent<BlockDamageEvent> {

    public NBlockDamageEvent(BlockDamageEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onDamage(this);
    }
}
