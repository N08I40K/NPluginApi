package ru.n08i40k.npluginapi.event.entity;

import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.entity.Entity;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityMoveEvent extends NEntityEvent<EntityMoveEvent> {
    public NEntityMoveEvent(EntityMoveEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onMove(this);
    }
}
