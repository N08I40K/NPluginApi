package ru.n08i40k.npluginapi.block;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTBlock;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.event.block.*;
import ru.n08i40k.npluginapi.database.NItemStackRegistry;
import ru.n08i40k.npluginapi.plugin.NPlugin;
import ru.n08i40k.npluginapi.resource.INResourceKeyHolder;
import ru.n08i40k.npluginapi.resource.NResourceGroup;
import ru.n08i40k.npluginapi.resource.NResourceKey;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@NonNull
public abstract class NBlock implements INResourceKeyHolder {
    private final NPlugin nPlugin;
    private final NResourceKey nResourceKey;
    private final Material material;

    @Setter
    @Nullable
    private String drop;

    public NBlock(@NonNull NPlugin nPlugin, @NonNull String id, Material material, @Nullable String drop) {
        this.nPlugin = nPlugin;
        this.nResourceKey = new NResourceKey(nPlugin, NResourceGroup.BLOCK, id);
        this.material = material;

        this.drop = drop;
    }

    public boolean isSameType(@NonNull Block block) {
        return block.getType() == material;
    }

    public void applyNBTCompound(@NonNull Block block) {
        NBlockNBT.applyNBTCompound(block, this);
    }

    @Nullable
    public ItemStack getDrop() {
        if (drop == null)
            return null;

        Pattern pattern = Pattern.compile("[a-z0-9]+:" + NResourceGroup.ITEM + "/[a-z0-9_-]+");
        Matcher matcher = pattern.matcher(drop);

        try {
            if (!matcher.find())
                return new ItemStack(Material.valueOf(drop));
        } catch (IllegalArgumentException exception) {
            NPluginApi.getInstance().getSLF4JLogger().error("Cannot find drop material of {} for NBlock {}!",
                    drop, nResourceKey);
            throw exception;
        }

        NItemStackRegistry itemStackDatabase = NPluginApi.getInstance().getNPluginManager().getNItemStackRegistry();

        Preconditions.checkState(itemStackDatabase.contains(NResourceKey.parse(drop)),
                "Can't find NItemStack with id %s for NBlock with id %s!",
                matcher.group(0),
                nResourceKey);

        return itemStackDatabase.getNItemStack(NResourceKey.parse(drop)).getItemStack();
    }

    public void onBellRing                  (NBlockBellRingEvent event)                 {}
    public void onBurn                      (NBlockBurnEvent event)                     {}
    public void onCook                      (NBlockCookEvent event)                     {}
    public void onDamage                    (NBlockDamageEvent event)                   {}
    public void onDispense                  (NBlockDispenseEvent event)                 {}
    public void onDropItem                  (NBlockDropItemEvent event)                 {}
    public void onExp                       (NBlockExpEvent event)                      {}
    public void onExplode                   (NBlockExplodeEvent event)                  {}
    public void onFade                      (NBlockFadeEvent event)                     {}
    public void onFertilize                 (NBlockFertilizeEvent event)                {}
    public void onFromTo                    (NBlockFromToEvent event)                   {}
    public void onGrow                      (NBlockGrowEvent event)                     {}
    public void onIgnite                    (NBlockIgniteEvent event)                   {}
    public void onPlace                     (NBlockPlaceEvent event)                    {}
    public void onRedstone                  (NBlockRedstoneEvent event)                 {}
    public void onShearEntity               (NBlockShearEntityEvent event)              {}
    public void onBreak                     (NBlockBreakEvent event)                    {
        if (event.getBukkitEvent().isCancelled())
            return;

        event.getBukkitEvent().setCancelled(true);

        new NBTBlock(event.getBlock()).getData().clearNBT();
        event.getBlock().setType(Material.AIR);

        ItemStack drop = getDrop();

        if (drop == null)
            return;

        Location blockLocation = event.getBlock().getLocation();

        blockLocation.getWorld().dropItemNaturally(blockLocation, drop);
    }
    public void onBrew                      (NBlockBrewEvent event)                     {}
    public void onBrewingStandFuel          (NBlockBrewingStandFuelEvent event)         {}
    public void onCauldronLevelChange       (NBlockCauldronLevelChangeEvent event)      {}
    public void onFluidLevelChange          (NBlockFluidLevelChangeEvent event)         {}
    public void onFurnaceBurn               (NBlockFurnaceBurnEvent event)              {}
    public void onLeavesDecay               (NBlockLeavesDecayEvent event)              {}
    public void onMoistureChange            (NBlockMoistureChangeEvent event)           {}
    public void onNotePlay                  (NBlockNotePlayEvent event)                 {}
    public void onSignChange                (NBlockSignChangeEvent event)               {}
    public void onSpongeAbsorb              (NBlockSpongeAbsorbEvent event)             {}
    public void onTNTPrime                  (NBlockTNTPrimeEvent event)                 {}
    public void onPistonRetract             (NBlockPistonRetractEvent event)            {
        if (!event.getBukkitEvent().isCancelled())
            NBlockNBT.moveNBTCompound(event.getBukkitEvent().getDirection(), event.getBlock());
    }
    public void onPistonExtend              (NBlockPistonExtendEvent event)             {
        if (!event.getBukkitEvent().isCancelled())
            NBlockNBT.moveNBTCompound(event.getBukkitEvent().getDirection(), event.getBlock());
    }
    public void onChunkLoad                 (NBlockChunkLoadEvent event)                {}
    public void onChunkUnload               (NBlockChunkUnloadEvent event)              {}
}
