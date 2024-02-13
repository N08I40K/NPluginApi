package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockCauldronLevelChangeEvent extends NBlockEvent<CauldronLevelChangeEvent> {

    public NBlockCauldronLevelChangeEvent(CauldronLevelChangeEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onCauldronLevelChange(this);
    }
}
