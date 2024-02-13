package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.world.ChunkLoadEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityChunkLoadEvent extends NEntityEvent<ChunkLoadEvent> {
    public NEntityChunkLoadEvent(ChunkLoadEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onChunkLoad(this);
    }
}
