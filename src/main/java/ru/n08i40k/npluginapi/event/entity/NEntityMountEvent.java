package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityMountEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityMountEvent extends NEntityEvent<EntityMountEvent> {

    public NEntityMountEvent(EntityMountEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onMount(this);
    }
}
