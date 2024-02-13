package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityToggleGlideEvent extends NEntityEvent<EntityToggleGlideEvent> {

    public NEntityToggleGlideEvent(EntityToggleGlideEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onToggleGlide(this);
    }
}
