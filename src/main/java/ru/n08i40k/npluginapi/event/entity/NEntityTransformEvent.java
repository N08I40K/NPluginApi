package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityTransformEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityTransformEvent extends NEntityEvent<EntityTransformEvent> {

    public NEntityTransformEvent(EntityTransformEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onTransform(this);
    }
}
