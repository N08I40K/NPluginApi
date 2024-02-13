package ru.n08i40k.npluginapi.event.craft;

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
import ru.n08i40k.npluginapi.custom.craft.NCraftRecipe;
import ru.n08i40k.npluginapi.custom.itemStack.NItemStack;
import ru.n08i40k.npluginapi.custom.itemStack.NItemStackNBT;
import ru.n08i40k.npluginapi.event.NPluginBusManager;
import ru.n08i40k.npluginapi.event.itemStack.NItemStackEvent;
import ru.n08i40k.npluginapi.registry.NCraftRecipeRegistry;

import java.lang.reflect.InvocationTargetException;

@Getter
@NonNull
public abstract class NCraftEvent<T extends org.bukkit.event.Event> implements ICancellable {
    @Setter
    protected boolean cancelled;

    protected final T bukkitEvent;
    protected final ItemStack itemStack;
    protected final NCraftRecipe nCraftRecipe;

    public NCraftEvent(T bukkitEvent, ItemStack itemStack, NCraftRecipe nCraftRecipe) {
        this.bukkitEvent = bukkitEvent;
        this.itemStack = itemStack;
        this.nCraftRecipe = nCraftRecipe;
    }

    public abstract void execute();

    public static <K extends NCraftEvent<T>, T extends org.bukkit.event.Event> void post(Class<K> klass, @Nullable ItemStack itemStack, T bukkitEvent) {
        if (itemStack == null || itemStack.getType() == Material.AIR)
            return;

        NBTCompound nbtCompound = NItemStackNBT.getNBTCompound(itemStack);

        if (nbtCompound == null)
            return;

        NItemStack nItemStack1 = NItemStackNBT.getNItemStack(nbtCompound);
        NCraftRecipeRegistry nCraftRecipeRegistry = NPluginApi.getInstance().getNPluginManager().getNCraftRecipeRegistry();
        for (NCraftRecipe nCraftRecipe1 : nCraftRecipeRegistry.getData().values()) {
            //                                    \| потому что можно добавлять крафты стандартных предметов
            if (!nCraftRecipe1.getNItemStack().getItemStack().equals(itemStack))
                continue;

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

            if (event.isCancelled())
                break;
        }
    }
}
