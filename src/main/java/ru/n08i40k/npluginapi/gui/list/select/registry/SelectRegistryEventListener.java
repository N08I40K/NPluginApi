package ru.n08i40k.npluginapi.gui.list.select.registry;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import ru.n08i40k.npluginapi.gui.list.select.craftRecipe.SelectCraftRecipeGuiHolder;
import ru.n08i40k.npluginapi.gui.list.select.enchantment.SelectEnchantmentGuiHolder;
import ru.n08i40k.npluginapi.gui.list.select.itemStack.SelectItemStackGuiHolder;
import ru.n08i40k.npluginapi.gui.list.select.plugin.SelectPluginGuiHolder.Registry;

public class SelectRegistryEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDrag(InventoryDragEvent event) {
        event.setCancelled(event.getInventory().getHolder() instanceof SelectRegistryGuiHolder);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof SelectRegistryGuiHolder holder))
            return;

        NBTCompound nbtCompound = clickCheck(event);
        if (nbtCompound == null) return;

        String registryName = nbtCompound.getString("registry");
        Preconditions.checkNotNull(registryName);

        Registry registry = Registry.valueOf(registryName);

        event.getWhoClicked().openInventory(getInventory(holder, registry));
    }

    @NonNull
    private static Inventory getInventory(SelectRegistryGuiHolder holder, Registry registry) {
        InventoryHolder inventoryHolder;

        switch (registry) {
            case ITEMSTACK -> inventoryHolder = new SelectItemStackGuiHolder(holder.getPluginId());
            case CRAFT_RECIPE -> inventoryHolder = new SelectCraftRecipeGuiHolder(holder.getPluginId());
            case ENCHANTMENT -> inventoryHolder = new SelectEnchantmentGuiHolder(holder.getPluginId());
            default -> throw new RuntimeException("Behaviour for registry " + registry.name() + " not implemented!");
        }

        return inventoryHolder.getInventory();
    }

    @Nullable
    public static NBTCompound clickCheck(InventoryClickEvent event) {
        if (event.getClickedInventory() != event.getInventory()) {
            if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY)
                event.setCancelled(true);
            return null;
        }
        event.setCancelled(true);

        if (event.getAction() != InventoryAction.PICKUP_ALL)
            return null;

        ItemStack itemStack = event.getCurrentItem();

        if (itemStack == null || itemStack.getType() == Material.AIR)
            return null;

        NBTItem nbtItem = new NBTItem(itemStack);

        return nbtItem.getCompound("nplugin-api");
    }
}
