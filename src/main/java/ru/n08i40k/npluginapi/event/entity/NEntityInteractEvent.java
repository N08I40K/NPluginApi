package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityInteractEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityInteractEvent extends NEntityEvent<EntityInteractEvent> {

    public NEntityInteractEvent(EntityInteractEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onInteract(this);
    }
}
