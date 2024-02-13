package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.LeavesDecayEvent;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockLeavesDecayEvent extends NBlockEvent<LeavesDecayEvent> {

    public NBlockLeavesDecayEvent(LeavesDecayEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onLeavesDecay(this);
    }
}
