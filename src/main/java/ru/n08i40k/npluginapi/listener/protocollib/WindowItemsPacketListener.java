package ru.n08i40k.npluginapi.listener.protocollib;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import ru.n08i40k.npluginapi.custom.enchantment.NEnchantmentAccessor;

import java.util.List;

public class WindowItemsPacketListener extends PacketAdapter {
    public WindowItemsPacketListener(@NonNull Plugin plugin) {
        super(plugin, PacketType.Play.Server.WINDOW_ITEMS);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        if (event.getPacketType() != PacketType.Play.Server.WINDOW_ITEMS)
            return;

        PacketContainer container = event.getPacket();

        List<ItemStack> itemStacks = container.getItemListModifier().read(0);

        for (ItemStack itemStack : itemStacks) {
            if (itemStack.getType() == Material.AIR)
                continue;

            if (itemStack.getItemMeta() == null)
                continue;

            NEnchantmentAccessor.NEnchantedLore.setLore(itemStack);
        }

        container.getItemListModifier().write(0, itemStacks);
    }
}
