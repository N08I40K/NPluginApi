package ru.n08i40k.npluginapi.event.entity;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import org.bukkit.entity.Entity;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityTickEvent extends NEntityEvent<ServerTickStartEvent> {

    public NEntityTickEvent(ServerTickStartEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onTick(this);
    }
}
