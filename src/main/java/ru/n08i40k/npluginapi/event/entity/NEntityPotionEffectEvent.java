package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityPotionEffectEvent extends NEntityEvent<EntityPotionEffectEvent> {

    public NEntityPotionEffectEvent(EntityPotionEffectEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onPotionEffect(this);
    }
}
