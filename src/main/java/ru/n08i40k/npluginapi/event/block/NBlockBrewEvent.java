package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.inventory.BrewEvent;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockBrewEvent extends NBlockEvent<BrewEvent> {

    public NBlockBrewEvent(BrewEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onBrew(this);
    }
}
