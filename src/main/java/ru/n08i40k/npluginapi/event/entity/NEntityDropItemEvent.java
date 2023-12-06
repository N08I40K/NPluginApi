package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDropItemEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityDropItemEvent extends NEntityEvent<EntityDropItemEvent> {

    public NEntityDropItemEvent(EntityDropItemEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onDropItem(this);
    }
}
