package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityExplodeEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityExplodeEvent extends NEntityEvent<EntityExplodeEvent> {

    public NEntityExplodeEvent(EntityExplodeEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onExplode(this);
    }
}
