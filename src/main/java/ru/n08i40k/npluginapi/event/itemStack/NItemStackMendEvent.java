package ru.n08i40k.npluginapi.event.itemStack;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemMendEvent;
import org.bukkit.inventory.ItemStack;
import ru.n08i40k.npluginapi.itemStack.NItemStack;

@Getter
@NonNull
public class NItemStackMendEvent extends NItemStackEvent<PlayerItemMendEvent> {

    public NItemStackMendEvent(PlayerItemMendEvent bukkitEvent, ItemStack itemStack, NItemStack nItemStack) {
        super(bukkitEvent, itemStack, nItemStack);
    }

    @Override
    public void execute() {
        nItemStack.onMend(this);
    }
}
