package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityPoseChangeEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityPoseChangeEvent extends NEntityEvent<EntityPoseChangeEvent> {

    public NEntityPoseChangeEvent(EntityPoseChangeEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onPoseChange(this);
    }
}
