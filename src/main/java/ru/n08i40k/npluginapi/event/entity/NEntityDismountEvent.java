package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.spigotmc.event.entity.EntityDismountEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityDismountEvent extends NEntityEvent<EntityDismountEvent> {

    public NEntityDismountEvent(EntityDismountEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onDismount(this);
    }
}
