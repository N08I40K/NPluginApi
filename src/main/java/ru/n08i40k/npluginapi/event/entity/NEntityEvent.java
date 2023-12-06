package ru.n08i40k.npluginapi.event.entity;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTCompound;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import meteordevelopment.orbit.ICancellable;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.entity.NEntity;
import ru.n08i40k.npluginapi.entity.NEntityNBT;
import ru.n08i40k.npluginapi.event.NPluginBusManager;

import java.lang.reflect.InvocationTargetException;

@Getter
@NonNull
public abstract class NEntityEvent<T extends org.bukkit.event.Event> implements ICancellable {
    @Setter
    protected boolean cancelled;

    protected final T bukkitEvent;
    protected final Entity entity;
    protected final NEntity<?> nEntity;


    public NEntityEvent(T bukkitEvent, Entity entity, NEntity<?> nEntity) {
        this.bukkitEvent = bukkitEvent;
        this.entity = entity;
        this.nEntity = nEntity;
    }

    public abstract void execute();

    public static <
            NEventType extends NEntityEvent<EventType>,
            EventType extends org.bukkit.event.Event,
            EntityType extends Entity>
    void post(Class<NEventType> eventKlass, @Nullable EntityType entity, EventType bukkitEvent) {
        if (entity == null)
            return;

        NBTCompound nbtCompound = NEntityNBT.getNBTCompound(entity);

        if (nbtCompound == null)
            return;

        NEntity<?> nEntity1 = NEntityNBT.getNEntity(nbtCompound);

        Preconditions.checkState(nEntity1.isSameType(entity));

        NEventType event;
        try {
            event = eventKlass
                    .getDeclaredConstructor(bukkitEvent.getClass(), Entity.class, NEntity.class)
                    .newInstance(bukkitEvent, entity, nEntity1);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            NPluginApi.getInstance().getSLF4JLogger().error(
                    "Cannot instance {} event!\n{}", eventKlass.getName(), exception.toString()
            );

            return;
        }

        NPluginBusManager.getEventBus().post(event);

        if (!event.isCancelled())
            event.execute();
    }
}
