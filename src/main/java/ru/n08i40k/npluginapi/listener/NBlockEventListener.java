package ru.n08i40k.npluginapi.listener;

import com.destroystokyo.paper.event.block.TNTPrimeEvent;
import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTChunk;
import de.tr7zw.nbtapi.NBTCompound;
import io.papermc.paper.event.block.BellRingEvent;
import lombok.NonNull;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.BrewingStandFuelEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.world.ChunkEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import ru.n08i40k.npluginapi.event.block.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NBlockEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBellRing(BellRingEvent event) {
        NBlockEvent.post(NBlockBellRingEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBurn(BlockBurnEvent event) {
        NBlockEvent.post(NBlockBurnEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreak(BlockBreakEvent event) {
        NBlockEvent.post(NBlockBreakEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCook(BlockCookEvent event) {
        NBlockEvent.post(NBlockCookEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamage(BlockDamageEvent event) {
        NBlockEvent.post(NBlockDamageEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDispense(BlockDispenseEvent event) {
        NBlockEvent.post(NBlockDispenseEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDropItem(BlockDropItemEvent event) {
        NBlockEvent.post(NBlockDropItemEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onExp(BlockExpEvent event) {
        NBlockEvent.post(NBlockExpEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onExplode(BlockExplodeEvent event) {
        NBlockEvent.post(NBlockExplodeEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFade(BlockFadeEvent event) {
        NBlockEvent.post(NBlockFadeEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFertilize(BlockFertilizeEvent event) {
        NBlockEvent.post(NBlockFertilizeEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFromTo(BlockFromToEvent event) {
        NBlockEvent.post(NBlockFromToEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onGrow(BlockGrowEvent event) {
        NBlockEvent.post(NBlockGrowEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onIgnite(BlockIgniteEvent event) {
        NBlockEvent.post(NBlockIgniteEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onRedstone(BlockRedstoneEvent event) {
        NBlockEvent.post(NBlockRedstoneEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onShearEntity(BlockShearEntityEvent event) {
        NBlockEvent.post(NBlockShearEntityEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBrew(BrewEvent event) {
        NBlockEvent.post(NBlockBrewEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBrewingStandFuel(BrewingStandFuelEvent event) {
        NBlockEvent.post(NBlockBrewingStandFuelEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCauldronLevelChange(CauldronLevelChangeEvent event) {
        NBlockEvent.post(NBlockCauldronLevelChangeEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFluidLevelChange(FluidLevelChangeEvent event) {
        NBlockEvent.post(NBlockFluidLevelChangeEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFurnaceBurn(FurnaceBurnEvent event) {
        NBlockEvent.post(NBlockFurnaceBurnEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onLeavesDecay(LeavesDecayEvent event) {
        NBlockEvent.post(NBlockLeavesDecayEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onMoistureChange(MoistureChangeEvent event) {
        NBlockEvent.post(NBlockMoistureChangeEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onNotePlay(NotePlayEvent event) {
        NBlockEvent.post(NBlockNotePlayEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSignChange(SignChangeEvent event) {
        NBlockEvent.post(NBlockSignChangeEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSpongeAbsorb(SpongeAbsorbEvent event) {
        NBlockEvent.post(NBlockSpongeAbsorbEvent.class, event.getBlock(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTNTPrime(TNTPrimeEvent event) {
        NBlockEvent.post(NBlockTNTPrimeEvent.class, event.getBlock(), event, null, true);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPistonRetract(BlockPistonRetractEvent event) {
        event.getBlocks().forEach(block ->
                        NBlockEvent.post(NBlockPistonRetractEvent.class, block, event));
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPistonExtend(BlockPistonExtendEvent event) {
        event.getBlocks().forEach(block ->
                NBlockEvent.post(NBlockPistonExtendEvent.class, block, event));
    }

    private <K extends NBlockEvent<T>, T extends ChunkEvent> void parseChunk(@NonNull Class<K> klass, @NonNull Chunk chunk,
                                                                             @NonNull T event) {
        NBTCompound nbtChunk = new NBTChunk(chunk).getPersistentDataContainer();

        if (!nbtChunk.hasTag("blocks"))
            return;

        NBTCompound nbtBlocks = nbtChunk.getCompound("blocks");

        Pattern pattern = Pattern.compile("([0-9\\-]+)_([0-9\\-]+)_([0-9\\-]+)");

        assert nbtBlocks != null;
        if (nbtBlocks.getKeys().isEmpty())
            return;

        for (String blockPos : nbtBlocks.getKeys()) {
            NBTCompound nbtBlock = nbtBlocks.getCompound(blockPos);

            assert nbtBlock != null;
            if (nbtBlock.getKeys().isEmpty()) {
                nbtBlocks.removeKey(blockPos);

                continue;
            }
            Matcher matcher = pattern.matcher(blockPos);
            Preconditions.checkState(matcher.find(),
                    "Broken key!", blockPos);

            Location blockLocation = new Location(chunk.getWorld(),
                    Float.parseFloat(matcher.group(1)),
                    Float.parseFloat(matcher.group(2)),
                    Float.parseFloat(matcher.group(3)));

            NBlockEvent.post(klass, blockLocation.getBlock(), event);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChunkLoad(ChunkLoadEvent event) {
        parseChunk(NBlockChunkLoadEvent.class, event.getChunk(), event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChunkUnload(ChunkUnloadEvent event) {
        parseChunk(NBlockChunkUnloadEvent.class, event.getChunk(), event);
    }
}