package ru.n08i40k.npluginapi.event.block;

import io.papermc.paper.event.block.BellRingEvent;
import org.bukkit.block.Block;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockBellRingEvent extends NBlockEvent<BellRingEvent> {

    public NBlockBellRingEvent(BellRingEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onBellRing(this);
    }
}
