package ru.n08i40k.npluginapi.event.entity;

import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;
import org.bukkit.entity.Entity;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityRemoveFromWorldEvent extends NEntityEvent<EntityRemoveFromWorldEvent> {
    public NEntityRemoveFromWorldEvent(EntityRemoveFromWorldEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onRemoveFromWorld(this);
    }
}
