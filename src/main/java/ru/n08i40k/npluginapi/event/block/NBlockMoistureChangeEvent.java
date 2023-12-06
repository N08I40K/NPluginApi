package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.MoistureChangeEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockMoistureChangeEvent extends NBlockEvent<MoistureChangeEvent> {

    public NBlockMoistureChangeEvent(MoistureChangeEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onMoistureChange(this);
    }
}
