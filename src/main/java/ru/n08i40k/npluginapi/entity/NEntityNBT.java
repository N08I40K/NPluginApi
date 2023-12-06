package ru.n08i40k.npluginapi.entity;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import lombok.NonNull;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.database.NEntityRegistry;
import ru.n08i40k.npluginapi.plugin.NPluginManager;
import ru.n08i40k.npluginapi.resource.NResourceKey;

public class NEntityNBT {
    @Nullable
    public static NBTCompound getNBTCompound(@NonNull Entity entity) {
        NBTEntity nbtEntity = new NBTEntity(entity);

        NBTCompound persistent = nbtEntity.getPersistentDataContainer();

        if (!persistent.hasTag("nplugin-api"))
            return null;

        return persistent.getCompound("nplugin-api");
    }

    @NonNull
    public static NResourceKey getId(@NonNull NBTCompound nbtCompound) {
        Preconditions.checkState(nbtCompound.hasTag("resourceKey"), "Corrupted NBTCompound!");

        return NResourceKey.parse(nbtCompound.getString("resourceKey"));
    }

    @NonNull
    public static NEntity<?> getNEntity(@NonNull NBTCompound nbtCompound) {
        return getNEntity(getId(nbtCompound));
    }

    @NonNull
    public static NEntity<?> getNEntity(@NonNull NResourceKey nResourceKey) {
        NPluginManager nPluginManager = NPluginApi.getInstance().getNPluginManager();
        NEntityRegistry nEntityRegistry = nPluginManager.getNEntityRegistry();
        Preconditions.checkState(nEntityRegistry.contains(nResourceKey),
                "Can't find NEntity with id %s!", nResourceKey);

        return nEntityRegistry.getNEntity(nResourceKey);
    }

    public static <EntityType extends Entity> void applyNBTCompound(@NonNull EntityType entity, @NonNull NEntity<?> nEntity) {
        Preconditions.checkState(nEntity.isSameType(entity));

        NBTEntity nbtEntity = new NBTEntity(entity);

        NBTCompound nbtCompound = nbtEntity.getPersistentDataContainer().getOrCreateCompound("nplugin-api");
        nbtCompound.setString("resourceKey", nEntity.getNResourceKey().toString());
    }
}
