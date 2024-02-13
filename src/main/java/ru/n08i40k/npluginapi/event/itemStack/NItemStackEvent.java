package ru.n08i40k.npluginapi.event.itemStack;

import de.tr7zw.nbtapi.NBTCompound;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import meteordevelopment.orbit.ICancellable;
import org.bukkit.Material;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.event.NPluginBusManager;
import ru.n08i40k.npluginapi.custom.itemStack.NItemStack;
import ru.n08i40k.npluginapi.custom.itemStack.NItemStackNBT;

import java.lang.reflect.InvocationTargetException;

@Getter
@NonNull
public abstract class NItemStackEvent<T extends org.bukkit.event.Event> implements ICancellable {
    @Setter
    protected boolean cancelled;

    protected final T bukkitEvent;
    protected final ItemStack itemStack;
    protected final NItemStack nItemStack;

    public NItemStackEvent(T bukkitEvent, ItemStack itemStack, NItemStack nItemStack) {
        this.bukkitEvent = bukkitEvent;
        this.itemStack = itemStack;
        this.nItemStack = nItemStack;
    }

    public abstract void execute();

    public static <K extends NItemStackEvent<T>, T extends org.bukkit.event.Event> void post(Class<K> klass, @Nullable ItemStack itemStack, T bukkitEvent) {
        if (itemStack == null || itemStack.getType() == Material.AIR)
            return;

        NBTCompound nbtCompound = NItemStackNBT.getNBTCompound(itemStack);

        if (nbtCompound == null)
            return;

        NItemStack nItemStack1 = NItemStackNBT.getNItemStack(nbtCompound);

        K event;
        try {
            event = klass
                    .getDeclaredConstructor(bukkitEvent.getClass(), ItemStack.class, NItemStack.class)
                    .newInstance(bukkitEvent, itemStack, nItemStack1);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            NPluginApi.getInstance().getSLF4JLogger().error(
                    "Cannot instance {} event!\n{}", klass.getName(), exception.toString()
            );

            return;
        }

        NPluginBusManager.getEventBus().post(event);

        if (!event.isCancelled())
            event.execute();

        if (bukkitEvent instanceof Cancellable cancellableBukkitEvent)
            cancellableBukkitEvent.setCancelled(event.isCancelled());
    }
}
