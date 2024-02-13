package ru.n08i40k.npluginapi.event.entity;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import org.bukkit.entity.Entity;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityAddToWorldEvent extends NEntityEvent<EntityAddToWorldEvent> {

    public NEntityAddToWorldEvent(EntityAddToWorldEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onAddToWorld(this);
    }
}
