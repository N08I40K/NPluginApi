package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityChangeBlockEvent extends NEntityEvent<EntityChangeBlockEvent> {

    public NEntityChangeBlockEvent(EntityChangeBlockEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onChangeBlock(this);
    }
}
