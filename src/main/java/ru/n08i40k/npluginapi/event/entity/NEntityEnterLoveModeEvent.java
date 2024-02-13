package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityEnterLoveModeEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityEnterLoveModeEvent extends NEntityEvent<EntityEnterLoveModeEvent> {

    public NEntityEnterLoveModeEvent(EntityEnterLoveModeEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onEnterLoveMode(this);
    }
}
