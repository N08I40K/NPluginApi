package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityTameEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityTameEvent extends NEntityEvent<EntityTameEvent> {

    public NEntityTameEvent(EntityTameEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onTame(this);
    }
}
