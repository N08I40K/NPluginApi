package ru.n08i40k.npluginapi.event.block;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTCompound;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import meteordevelopment.orbit.ICancellable;
import org.bukkit.block.Block;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.block.NBlock;
import ru.n08i40k.npluginapi.block.NBlockNBT;
import ru.n08i40k.npluginapi.event.NPluginBusManager;

import java.lang.reflect.InvocationTargetException;

@Getter
@NonNull
public abstract class NBlockEvent<T extends org.bukkit.event.Event> implements ICancellable {
    @Setter
    protected boolean cancelled;

    protected final T bukkitEvent;
    protected final Block block;
    protected final NBlock nBlock;


    public NBlockEvent(T bukkitEvent, Block block, NBlock nBlock) {
        this.bukkitEvent = bukkitEvent;
        this.nBlock = nBlock;
        this.block = block;
    }

    public abstract void execute();

    public static <NEventType extends NBlockEvent<EventType>, EventType extends org.bukkit.event.Event>
    void post(Class<NEventType> eventKlass,
              @Nullable Block block,
              @NonNull EventType bukkitEvent) {
        post(eventKlass, block, bukkitEvent, null, false);
    }

    public static <NEventType extends NBlockEvent<EventType>, EventType extends org.bukkit.event.Event>
    void post(Class<NEventType> eventKlass,
              @Nullable Block block,
              @NonNull EventType bukkitEvent,
              @Nullable NBTCompound nbtCompound,
              boolean bypassTypeCheck) {
        if (block == null)
            return;

        NBTCompound nPluginNBTCompound = nbtCompound == null ?
                NBlockNBT.getNPluginNBTCompound(NBlockNBT.getPersistentData(block))
                :
                NBlockNBT.getNPluginNBTCompound(nbtCompound);

        if (nPluginNBTCompound == null)
            return;

        NBlock nBlock1 = NBlockNBT.getNBlock(nPluginNBTCompound);

        if (!bypassTypeCheck)
        {
            if (!nBlock1.isSameType(block)) {
                NPluginApi.getInstance().getSLF4JLogger().warn(
                        "Block at [{}] has NBlock {} id, but it material isn't same! (Block {} - NBlock {})\nClearing NBTCompound...",
                        block.getLocation(), nBlock1.getNResourceKey(),
                        block.getType(), nBlock1.getMaterial());

                NBlockNBT.removeNBT(block);
                return;
            }
        }

        NEventType event;
        try {
            event = eventKlass
                    .getDeclaredConstructor(bukkitEvent.getClass(), Block.class, NBlock.class)
                    .newInstance(bukkitEvent, block, nBlock1);
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
