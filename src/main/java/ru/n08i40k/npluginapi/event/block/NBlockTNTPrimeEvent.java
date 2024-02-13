package ru.n08i40k.npluginapi.event.block;

import com.destroystokyo.paper.event.block.TNTPrimeEvent;
import org.bukkit.block.Block;
import ru.n08i40k.npluginapi.custom.block.NBlock;

public class NBlockTNTPrimeEvent extends NBlockEvent<TNTPrimeEvent> {

    public NBlockTNTPrimeEvent(TNTPrimeEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onTNTPrime(this);
    }
}
