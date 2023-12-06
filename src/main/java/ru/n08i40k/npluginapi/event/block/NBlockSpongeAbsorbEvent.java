package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.SpongeAbsorbEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockSpongeAbsorbEvent extends NBlockEvent<SpongeAbsorbEvent> {

    public NBlockSpongeAbsorbEvent(SpongeAbsorbEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onSpongeAbsorb(this);
    }
}
