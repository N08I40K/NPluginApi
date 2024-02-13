package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.FluidLevelChangeEvent;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockFluidLevelChangeEvent extends NBlockEvent<FluidLevelChangeEvent> {

    public NBlockFluidLevelChangeEvent(FluidLevelChangeEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onFluidLevelChange(this);
    }
}
