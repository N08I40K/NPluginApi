package ru.n08i40k.npluginapi.gui.list.select.registry;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.database.NRegistry;
import ru.n08i40k.npluginapi.plugin.NPlugin;
import ru.n08i40k.npluginapi.plugin.NPluginManager;
import ru.n08i40k.npluginlocale.LocaleRequestBuilder;

import javax.annotation.Nullable;
import java.util.List;

import static ru.n08i40k.npluginapi.gui.list.select.plugin.SelectPluginGuiHolder.Registry;

@Getter
public class SelectRegistryGuiHolder implements InventoryHolder {
    private final Inventory inventory;
    private final String pluginId;
    private static final LocaleRequestBuilder localeRoot =
            new LocaleRequestBuilder(null, "gui.list.select_registry");

    @Nullable ItemStack getIcon(NRegistry<?> nRegistry, NPlugin nPlugin, Registry registry, Material material) {
        long count = nRegistry.getData().keySet().stream().filter(
                nResourceKey -> nResourceKey.getNPlugin().equals(nPlugin)).count();

        if (count == 0)
            return null;

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        LocaleRequestBuilder registryRoot = localeRoot.extend(registry.name().toLowerCase());

        itemMeta.displayName(registryRoot.get("view_name").getSingle().getC());
        itemMeta.lore(List.of(registryRoot.get("description", count).getSingle().getC()));
        itemStack.setItemMeta(itemMeta);

        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.addCompound("nplugin-api").setString("registry", registry.name());

        return nbtItem.getItem();
    }

    public SelectRegistryGuiHolder(@NonNull String pluginId) {
        this.pluginId = pluginId;

        NPluginManager nPluginManager = NPluginApi.getInstance().getNPluginManager();

        NPlugin nPlugin = nPluginManager.getNPluginMap().get(pluginId);
        Preconditions.checkNotNull(nPlugin);

        inventory = Bukkit.createInventory(this, 9,
                localeRoot.get("title", nPlugin.getViewName()).getSingle().getC());

        ItemStack icon;

        icon = getIcon(nPluginManager.getNEntityRegistry(), nPlugin, Registry.ENTITY, Material.VILLAGER_SPAWN_EGG);
        if (icon != null) inventory.addItem(icon);

        icon = getIcon(nPluginManager.getNBlockRegistry(), nPlugin, Registry.BLOCK, Material.BEDROCK);
        if (icon != null) inventory.addItem(icon);

        icon = getIcon(nPluginManager.getNItemStackRegistry(), nPlugin, Registry.ITEMSTACK, Material.FEATHER);
        if (icon != null) inventory.addItem(icon);

        icon = getIcon(nPluginManager.getNCraftRecipeRegistry(), nPlugin, Registry.CRAFT_RECIPE, Material.BOOK);
        if (icon != null) inventory.addItem(icon);
    }
}
