package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityPortalEnterEvent extends NEntityEvent<EntityPortalEnterEvent> {

    public NEntityPortalEnterEvent(EntityPortalEnterEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onPortalEnter(this);
    }
}
