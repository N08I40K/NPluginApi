package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityPlaceEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityPlaceEvent extends NEntityEvent<EntityPlaceEvent> {

    public NEntityPlaceEvent(EntityPlaceEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onPlace(this);
    }
}
