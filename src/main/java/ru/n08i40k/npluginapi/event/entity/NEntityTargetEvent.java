package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityTargetEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityTargetEvent extends NEntityEvent<EntityTargetEvent> {

    public NEntityTargetEvent(EntityTargetEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onTarget(this);
    }
}
