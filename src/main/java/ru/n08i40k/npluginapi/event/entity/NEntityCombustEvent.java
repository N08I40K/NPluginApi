package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityCombustEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityCombustEvent extends NEntityEvent<EntityCombustEvent> {

    public NEntityCombustEvent(EntityCombustEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onCombust(this);
    }
}
