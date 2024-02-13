package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityEnterBlockEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityEnterBlockEvent extends NEntityEvent<EntityEnterBlockEvent> {

    public NEntityEnterBlockEvent(EntityEnterBlockEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onEnterBlock(this);
    }
}
