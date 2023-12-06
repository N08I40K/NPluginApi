package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityExhaustionEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityExhaustionEvent extends NEntityEvent<EntityExhaustionEvent> {

    public NEntityExhaustionEvent(EntityExhaustionEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onExhaustion(this);
    }
}
