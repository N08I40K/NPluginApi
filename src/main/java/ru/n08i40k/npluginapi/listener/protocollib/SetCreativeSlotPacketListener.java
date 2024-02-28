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

public class SetCreativeSlotPacketListener extends PacketAdapter {
    public SetCreativeSlotPacketListener(@NonNull Plugin plugin) {
        super(plugin, PacketType.Play.Client.SET_CREATIVE_SLOT);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        if (event.getPacketType() != PacketType.Play.Client.SET_CREATIVE_SLOT)
            return;

        PacketContainer container = event.getPacket();

        ItemStack itemStack = container.getItemModifier().read(0);

        if (itemStack.getType() == Material.AIR)
            return;

        if (itemStack.getItemMeta() == null)
            return;

        NEnchantmentAccessor.NEnchantedLore.clearLore(itemStack);

        container.getItemModifier().write(0, itemStack);
    }
}
