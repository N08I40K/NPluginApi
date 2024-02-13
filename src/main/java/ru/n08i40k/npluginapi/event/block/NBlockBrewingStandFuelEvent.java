package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.inventory.BrewingStandFuelEvent;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockBrewingStandFuelEvent extends NBlockEvent<BrewingStandFuelEvent> {

    public NBlockBrewingStandFuelEvent(BrewingStandFuelEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onBrewingStandFuel(this);
    }
}
