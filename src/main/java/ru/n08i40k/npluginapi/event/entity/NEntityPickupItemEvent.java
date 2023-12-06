package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityPickupItemEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityPickupItemEvent extends NEntityEvent<EntityPickupItemEvent> {

    public NEntityPickupItemEvent(EntityPickupItemEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onPickupItem(this);
    }
}
