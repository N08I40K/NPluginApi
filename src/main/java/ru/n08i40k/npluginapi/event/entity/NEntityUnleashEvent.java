package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityUnleashEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityUnleashEvent extends NEntityEvent<EntityUnleashEvent> {

    public NEntityUnleashEvent(EntityUnleashEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onUnleash(this);
    }
}