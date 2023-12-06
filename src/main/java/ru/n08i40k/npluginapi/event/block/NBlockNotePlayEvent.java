package ru.n08i40k.npluginapi.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.NotePlayEvent;
import ru.n08i40k.npluginapi.block.NBlock;

public class NBlockNotePlayEvent extends NBlockEvent<NotePlayEvent> {

    public NBlockNotePlayEvent(NotePlayEvent bukkitEvent, Block block, NBlock nBlock) {
        super(bukkitEvent, block, nBlock);
    }

    @Override
    public void execute() {
        nBlock.onNotePlay(this);
    }
}
