package ru.n08i40k.npluginapi.event.itemStack;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import ru.n08i40k.npluginapi.itemStack.NItemStack;

@Getter
@NonNull
public class NItemStackBreakEvent extends NItemStackEvent<PlayerItemBreakEvent> {

    public NItemStackBreakEvent(PlayerItemBreakEvent bukkitEvent, ItemStack itemStack, NItemStack nItemStack) {
        super(bukkitEvent, itemStack, nItemStack);
    }

    @Override
    public void execute() {
        nItemStack.onBreak(this);
    }
}
