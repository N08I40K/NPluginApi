package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntitySpellCastEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntitySpellCastEvent extends NEntityEvent<EntitySpellCastEvent> {

    public NEntitySpellCastEvent(EntitySpellCastEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onSpellCast(this);
    }
}
