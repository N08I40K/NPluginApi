package ru.n08i40k.npluginapi.entity;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.entity.Entity;
import ru.n08i40k.npluginapi.event.entity.*;
import ru.n08i40k.npluginapi.plugin.NPlugin;
import ru.n08i40k.npluginapi.resource.INResourceKeyHolder;
import ru.n08i40k.npluginapi.resource.NResourceGroup;
import ru.n08i40k.npluginapi.resource.NResourceKey;

@Getter
@NonNull
public abstract class NEntity<T extends Entity> implements INResourceKeyHolder {
    private final NPlugin nPlugin;
    private final NResourceKey nResourceKey;
    private final Class<T> klass;

    public NEntity(@NonNull NPlugin nPlugin, @NonNull String id, Class<T> klass) {
        this.nPlugin = nPlugin;
        this.nResourceKey = new NResourceKey(nPlugin, NResourceGroup.ENTITY, id);
        this.klass = klass;
    }

    public boolean isSameType(@NonNull Entity entity) {
        return klass.isInstance(entity);
    }

    public void applyNBTCompound(@NonNull Entity entity) {
        NEntityNBT.applyNBTCompound(entity, this);
    }

    public void onAirChange         (NEntityAirChangeEvent event)       {}
    public void onBreed             (NEntityBreedEvent event)           {}
    public void onChangeBlock       (NEntityChangeBlockEvent event)     {}
    public void onCombust           (NEntityCombustEvent event)         {}
    public void onDamage            (NEntityDamageEvent event)          {}
    public void onDeath             (NEntityDeathEvent event)           {}
    public void onDismount          (NEntityDismountEvent event)        {}
    public void onDropItem          (NEntityDropItemEvent event)        {}
    public void onEnterBlock        (NEntityEnterBlockEvent event)      {}
    public void onEnterLoveMode     (NEntityEnterLoveModeEvent event)   {}
    public void onExhaustion        (NEntityExhaustionEvent event)      {}
    public void onExplode           (NEntityExplodeEvent event)         {}
    public void onInteract          (NEntityInteractEvent event)        {}
    public void onMount             (NEntityMountEvent event)           {}
    public void onPickupItem        (NEntityPickupItemEvent event)      {}
    public void onPlace             (NEntityPlaceEvent event)           {}
    public void onPortalEnter       (NEntityPortalEnterEvent event)     {}
    public void onPoseChange        (NEntityPoseChangeEvent event)      {}
    public void onPotionEffect      (NEntityPotionEffectEvent event)    {}
    public void onRegainHealth      (NEntityRegainHealthEvent event)    {}
    public void onResurrect         (NEntityResurrectEvent event)       {}
    public void onShootBow          (NEntityShootBowEvent event)        {}
    public void onSpawn             (NEntitySpawnEvent event)           {}
    public void onSpellCast         (NEntitySpellCastEvent event)       {}
    public void onTame              (NEntityTameEvent event)            {}
    public void onTarget            (NEntityTargetEvent event)          {}
    public void onTeleport          (NEntityTeleportEvent event)        {}
    public void onToggleGlide       (NEntityToggleGlideEvent event)     {}
    public void onToggleSwim        (NEntityToggleSwimEvent event)      {}
    public void onTransform         (NEntityTransformEvent event)       {}
    public void onUnleash           (NEntityUnleashEvent event)         {}
    public void onExplosionPrime    (NEntityExplosionPrimeEvent event)  {}
    public void onFoodLevelChange   (NEntityFoodLevelChangeEvent event) {}
    public void onChunkLoad         (NEntityChunkLoadEvent event)       {}
    public void onChunkUnload       (NEntityChunkUnloadEvent event)     {}
    public void onRemoveFromWorld   (NEntityRemoveFromWorldEvent event) {}
    public void onAddToWorld        (NEntityAddToWorldEvent event)      {}
    public void onMove              (NEntityMoveEvent event)            {}
    public void onTick              (NEntityTickEvent event)            {}
}
