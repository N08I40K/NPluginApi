package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.world.ChunkUnloadEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityChunkUnloadEvent extends NEntityEvent<ChunkUnloadEvent> {
    public NEntityChunkUnloadEvent(ChunkUnloadEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onChunkUnload(this);
    }
}
