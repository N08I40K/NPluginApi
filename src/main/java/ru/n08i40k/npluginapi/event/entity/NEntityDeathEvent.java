package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDeathEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityDeathEvent extends NEntityEvent<EntityDeathEvent> {

    public NEntityDeathEvent(EntityDeathEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onDeath(this);
    }
}
