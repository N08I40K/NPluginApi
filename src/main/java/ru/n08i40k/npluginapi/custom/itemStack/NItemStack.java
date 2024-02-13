package ru.n08i40k.npluginapi.custom.itemStack;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.custom.block.NBlock;
import ru.n08i40k.npluginapi.registry.NBlockRegistry;
import ru.n08i40k.npluginapi.event.itemStack.*;
import ru.n08i40k.npluginapi.plugin.NPlugin;
import ru.n08i40k.npluginapi.plugin.NPluginManager;
import ru.n08i40k.npluginapi.resource.INResourceKeyHolder;
import ru.n08i40k.npluginapi.resource.NResourceGroup;
import ru.n08i40k.npluginapi.resource.NResourceKey;

public abstract class NItemStack implements INResourceKeyHolder {
    @NonNull
    private final ItemStack itemStack;

    @Getter
    @NonNull
    private final NPlugin nPlugin;

    @Getter
    @Nullable
    private final NResourceKey nResourceKey;

    @Getter
    private final boolean isPlaceable;

    public NItemStack(@NonNull NPlugin nPlugin, @NonNull ItemStack itemStack) {
        this.itemStack = itemStack;
        this.nPlugin = nPlugin;

        this.nResourceKey = null;
        this.isPlaceable = false;
    }

    public NItemStack(@NonNull NPlugin nPlugin, @NonNull ItemStack itemStack, @NonNull String id, boolean isPlaceable) {
        this.itemStack = itemStack;
        this.nPlugin = nPlugin;

        this.nResourceKey = new NResourceKey(nPlugin, NResourceGroup.ITEM, id);
        this.isPlaceable = isPlaceable && itemStack.getType().isBlock();
    }

    @NonNull
    public ItemStack getSourceItemStack() {
        return itemStack;
    }

    @NonNull
    public ItemStack getItemStack() {
        return NItemStackNBT.applyNBTCompound(this);
    }

    public void onInteract          (NItemStackInteractEvent event)         {}
    public void onInteractEntity    (NItemStackInteractEntityEvent event)   {}
    public void onInteractAtEntity  (NItemStackInteractAtEntityEvent event) {}
    public void onDrop              (NItemStackDropEvent event)             {}
    public void onPickup            (NItemStackPickupEvent event)           {}
    public void onBlockPlace        (NItemStackBlockPlaceEvent event)       {
        if (event.getBukkitEvent().isCancelled())
            return;

        Block newBlock = event.getBukkitEvent().getBlockPlaced();
        if (newBlock.getType() != getItemStack().getType())
            return;

        if (!isPlaceable) {
            event.getBukkitEvent().setCancelled(true);
            return;
        }

        assert nResourceKey != null;
        NResourceKey nResourceKey = new NResourceKey(nPlugin, NResourceGroup.BLOCK, this.nResourceKey.getObjectId());

        NPluginManager nPluginManager = NPluginApi.getInstance().getNPluginManager();
        NBlockRegistry nBlockRegistry = nPluginManager.getNBlockRegistry();

        NBlock nBlock = nBlockRegistry.getNBlock(nResourceKey);
        nBlock.applyNBTCompound(newBlock);
    }
    public void onBreak             (NItemStackBreakEvent event)            {}
    public void onDamage            (NItemStackDamageEvent event)           {}
    public void onDamageEntity      (NItemStackDamageEntityEvent event)     {}
    public void onMend              (NItemStackMendEvent event)             {}
    public void onHeld              (NItemStackHeldEvent event)             {}
    public void onConsume           (NItemStackConsumeEvent event)          {}
    public void onCraft             (NItemStackCraftEvent event)            {}
    public void onRemoveFromWorld   (NItemStackRemoveFromWorldEvent event)  {}
}
