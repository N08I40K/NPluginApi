package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityDamageEvent extends NEntityEvent<EntityDamageEvent> {

    public NEntityDamageEvent(EntityDamageEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onDamage(this);
    }
}
