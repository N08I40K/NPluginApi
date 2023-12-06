package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityBreedEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityBreedEvent extends NEntityEvent<EntityBreedEvent> {

    public NEntityBreedEvent(EntityBreedEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onBreed(this);
    }
}
