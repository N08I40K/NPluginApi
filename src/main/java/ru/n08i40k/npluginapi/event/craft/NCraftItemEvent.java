package ru.n08i40k.npluginapi.event.craft;

import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import ru.n08i40k.npluginapi.custom.craft.NCraftRecipe;

public class NCraftItemEvent extends NCraftEvent<CraftItemEvent> {
    public NCraftItemEvent(CraftItemEvent bukkitEvent, ItemStack itemStack, NCraftRecipe nCraftRecipe) {
        super(bukkitEvent, itemStack, nCraftRecipe);
    }

    @Override
    public void execute() {
        nCraftRecipe.onCraftItem(this);
    }
}
