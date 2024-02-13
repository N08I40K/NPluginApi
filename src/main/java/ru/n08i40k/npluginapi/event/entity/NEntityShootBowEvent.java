package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityShootBowEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityShootBowEvent extends NEntityEvent<EntityShootBowEvent> {

    public NEntityShootBowEvent(EntityShootBowEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onShootBow(this);
    }
}
