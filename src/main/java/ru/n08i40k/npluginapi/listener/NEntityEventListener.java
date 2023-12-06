package ru.n08i40k.npluginapi.listener;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;
import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;
import ru.n08i40k.npluginapi.event.entity.*;

public class NEntityEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAirChangeEvent(EntityAirChangeEvent event) {
        NEntityEvent.post(NEntityAirChangeEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreedEvent(EntityBreedEvent event) {
        NEntityEvent.post(NEntityBreedEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChangeBlockEvent(EntityChangeBlockEvent event) {
        NEntityEvent.post(NEntityChangeBlockEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCombustEvent(EntityCombustEvent event) {
        NEntityEvent.post(NEntityCombustEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamageEvent(EntityDamageEvent event) {
        NEntityEvent.post(NEntityDamageEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDeathEvent(EntityDeathEvent event) {
        NEntityEvent.post(NEntityDeathEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDismountEvent(EntityDismountEvent event) {
        NEntityEvent.post(NEntityDismountEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDropItemEvent(EntityDropItemEvent event) {
        NEntityEvent.post(NEntityDropItemEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEnterBlockEvent(EntityEnterBlockEvent event) {
        NEntityEvent.post(NEntityEnterBlockEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEnterLoveModeEvent(EntityEnterLoveModeEvent event) {
        NEntityEvent.post(NEntityEnterLoveModeEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onExhaustionEvent(EntityExhaustionEvent event) {
        NEntityEvent.post(NEntityExhaustionEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onExplodeEvent(EntityExplodeEvent event) {
        NEntityEvent.post(NEntityExplodeEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInteractEvent(EntityInteractEvent event) {
        NEntityEvent.post(NEntityInteractEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onMountEvent(EntityMountEvent event) {
        NEntityEvent.post(NEntityMountEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPickupItemEvent(EntityPickupItemEvent event) {
        NEntityEvent.post(NEntityPickupItemEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlaceEvent(EntityPlaceEvent event) {
        NEntityEvent.post(NEntityPlaceEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPortalEnterEvent(EntityPortalEnterEvent event) {
        NEntityEvent.post(NEntityPortalEnterEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPoseChangeEvent(EntityPoseChangeEvent event) {
        NEntityEvent.post(NEntityPoseChangeEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPotionEffectEvent(EntityPotionEffectEvent event) {
        NEntityEvent.post(NEntityPotionEffectEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onRegainHealthEvent(EntityRegainHealthEvent event) {
        NEntityEvent.post(NEntityRegainHealthEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onResurrectEvent(EntityResurrectEvent event) {
        NEntityEvent.post(NEntityResurrectEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onShootBowEvent(EntityShootBowEvent event) {
        NEntityEvent.post(NEntityShootBowEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSpawnEvent(EntitySpawnEvent event) {
        NEntityEvent.post(NEntitySpawnEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSpellCastEvent(EntitySpellCastEvent event) {
        NEntityEvent.post(NEntitySpellCastEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTameEvent(EntityTameEvent event) {
        NEntityEvent.post(NEntityTameEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTargetEvent(EntityTargetEvent event) {
        NEntityEvent.post(NEntityTargetEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTeleportEvent(EntityTeleportEvent event) {
        NEntityEvent.post(NEntityTeleportEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onToggleGlideEvent(EntityToggleGlideEvent event) {
        NEntityEvent.post(NEntityToggleGlideEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onToggleSwimEvent(EntityToggleSwimEvent event) {
        NEntityEvent.post(NEntityToggleSwimEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTransformEvent(EntityTransformEvent event) {
        NEntityEvent.post(NEntityTransformEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onUnleashEvent(EntityUnleashEvent event) {
        NEntityEvent.post(NEntityUnleashEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onExplosionPrimeEvent(ExplosionPrimeEvent event) {
        NEntityEvent.post(NEntityExplosionPrimeEvent.class, event.getEntity(), event);
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) {
        NEntityEvent.post(NEntityFoodLevelChangeEvent.class, event.getEntity(), event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChunkLoad(ChunkLoadEvent event) {
        for (Entity entity : event.getChunk().getEntities())
            NEntityEvent.post(NEntityChunkLoadEvent.class, entity, event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChunkUnload(ChunkUnloadEvent event) {
        for (Entity entity : event.getChunk().getEntities())
            NEntityEvent.post(NEntityChunkUnloadEvent.class, entity, event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onRemoveFromWorld(EntityRemoveFromWorldEvent event) {
        NEntityEvent.post(NEntityRemoveFromWorldEvent.class, event.getEntity(), event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAddToWorld(EntityAddToWorldEvent event) {
        NEntityEvent.post(NEntityAddToWorldEvent.class, event.getEntity(), event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onMove(EntityMoveEvent event) {
        NEntityEvent.post(NEntityMoveEvent.class, event.getEntity(), event);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTickStart(ServerTickStartEvent event) {
        Bukkit.getServer().getWorlds().forEach(world ->
                world.getEntities().forEach(entity ->
                        NEntityEvent.post(NEntityTickEvent.class, entity, event)));
    }
}
