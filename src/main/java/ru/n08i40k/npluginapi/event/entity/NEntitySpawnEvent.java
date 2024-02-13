package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntitySpawnEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntitySpawnEvent extends NEntityEvent<EntitySpawnEvent> {

    public NEntitySpawnEvent(EntitySpawnEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onSpawn(this);
    }
}
