package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.SignChangeEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockSignChangeEvent extends NBlockEvent<SignChangeEvent> {

    public NBlockSignChangeEvent(SignChangeEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onSignChange(this);
    }
}
