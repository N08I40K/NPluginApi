package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityTeleportEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityTeleportEvent extends NEntityEvent<EntityTeleportEvent> {

    public NEntityTeleportEvent(EntityTeleportEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onTeleport(this);
    }
}
