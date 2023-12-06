package ru.n08i40k.npluginapi.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import ru.n08i40k.npluginapi.entity.NEntity;

public class NEntityFoodLevelChangeEvent extends NEntityEvent<FoodLevelChangeEvent> {

    public NEntityFoodLevelChangeEvent(FoodLevelChangeEvent bukkitEvent, Entity entity, NEntity<?> nEntity) {
        super(bukkitEvent, entity, nEntity);
    }

    @Override
    public void execute() {
        nEntity.onFoodLevelChange(this);
    }
}
