package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockFurnaceBurnEvent extends NBlockEvent<FurnaceBurnEvent> {

    public NBlockFurnaceBurnEvent(FurnaceBurnEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onFurnaceBurn(this);
    }
}
