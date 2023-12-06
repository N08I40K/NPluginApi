package ru.n08i40k.npluginapi.block;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTChunk;
import de.tr7zw.nbtapi.NBTCompound;
import lombok.NonNull;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.database.NBlockRegistry;
import ru.n08i40k.npluginapi.plugin.NPluginManager;
import ru.n08i40k.npluginapi.resource.NResourceKey;

public class NBlockNBT {
    @Nullable
    public static NBTCompound getPersistentData(@NonNull Block block) {
        NBTChunk nbtChunk = new NBTChunk(block.getChunk());

        NBTCompound nbtChunkPersistent = nbtChunk.getPersistentDataContainer();
        if (!nbtChunkPersistent.hasTag("blocks"))
            return null;

        NBTCompound nbtBlocks = nbtChunkPersistent.getCompound("blocks");
        assert nbtBlocks != null;

        String blockPos = block.getX() + "_" + block.getY() + "_" + block.getZ();
        if (!nbtBlocks.hasTag(blockPos))
            return null;

       return nbtBlocks.getCompound(blockPos);
    }

    public static void removeNBT(@NonNull Block block) {
        NBTChunk nbtChunk = new NBTChunk(block.getChunk());

        NBTCompound nbtChunkPersistent = nbtChunk.getPersistentDataContainer();
        if (!nbtChunkPersistent.hasTag("blocks"))
            return;

        NBTCompound nbtBlocks = nbtChunkPersistent.getCompound("blocks");
        assert nbtBlocks != null;

        String blockPos = block.getX() + "_" + block.getY() + "_" + block.getZ();
        if (!nbtBlocks.hasTag(blockPos))
            return;

        nbtBlocks.removeKey(blockPos);
    }

    @Nullable
    public static NBTCompound getNPluginNBTCompound(@Nullable NBTCompound nbtCompound) {
        if (nbtCompound == null)
            return null;

        if (!nbtCompound.hasTag("nplugin-api"))
            return null;

        return nbtCompound.getCompound("nplugin-api");
    }

    @Nullable
    public static NBTCompound getNPluginNBTCompound(@NonNull Block block) {
        return getNPluginNBTCompound(getPersistentData(block));
    }

    @NonNull
    public static NResourceKey getId(@NonNull NBTCompound nbtCompound) {
        Preconditions.checkState(nbtCompound.hasTag("resourceKey"), "Corrupted NBTCompound!");

        return NResourceKey.parse(nbtCompound.getString("resourceKey"));
    }

    @NonNull
    public static NBlock getNBlock(@NonNull NBTCompound nbtCompound) {
        return getNBlock(getId(nbtCompound));
    }

    @NonNull
    public static NBlock getNBlock(@NonNull NResourceKey nResourceKey) {
        NPluginManager nPluginManager = NPluginApi.getInstance().getNPluginManager();
        NBlockRegistry nBlockRegistry = nPluginManager.getNBlockRegistry();
        Preconditions.checkState(nBlockRegistry.contains(nResourceKey),
                "Can't find NBlock with id %s!", nResourceKey);

        return nBlockRegistry.getNBlock(nResourceKey);
    }

    public static void applyNBTCompound(@NonNull Block block, @NonNull NBlock nBlock) {
        Preconditions.checkState(nBlock.isSameType(block));

        NBTBlock nbtBlock = new NBTBlock(block);

        NBTCompound nbtCompound = nbtBlock.getData().getOrCreateCompound("nplugin-api");
        nbtCompound.setString("resourceKey", nBlock.getNResourceKey().toString());
    }

    public static void moveNBTCompound(BlockFace blockFace, Block block) {
        Block newBlock = block.getLocation().add(blockFace.getDirection()).getBlock();

        NBTBlock nbtBlock = new NBTBlock(block);
        NBTBlock nbtNewBlock = new NBTBlock(newBlock);

        nbtNewBlock.getData().clearNBT();
        nbtNewBlock.getData().mergeCompound(nbtBlock.getData());

        nbtBlock.getData().clearNBT();
    }
}
