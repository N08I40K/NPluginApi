package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityResurrectEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityResurrectEvent extends NEntityEvent<EntityResurrectEvent> {

    public NEntityResurrectEvent(EntityResurrectEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onResurrect(this);
    }
}
