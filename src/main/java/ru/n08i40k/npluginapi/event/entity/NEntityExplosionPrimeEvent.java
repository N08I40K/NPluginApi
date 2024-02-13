package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import ru.n08i40k.npluginapi.custom.entity.NEntity;

public class NEntityExplosionPrimeEvent extends NEntityEvent<ExplosionPrimeEvent> {

    public NEntityExplosionPrimeEvent(ExplosionPrimeEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onExplosionPrime(this);
    }
}
