package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityRegainHealthEvent extends NEntityEvent<EntityRegainHealthEvent> {

    public NEntityRegainHealthEvent(EntityRegainHealthEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onRegainHealth(this);
    }
}
