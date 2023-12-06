package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityAirChangeEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityAirChangeEvent extends NEntityEvent<EntityAirChangeEvent> {

    public NEntityAirChangeEvent(EntityAirChangeEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onAirChange(this);
    }
}
