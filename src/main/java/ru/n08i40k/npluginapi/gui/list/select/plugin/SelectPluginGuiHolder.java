package ru.n08i40k.npluginapi.gui.list.select.plugin;

import com.google.common.base.Preconditions;
import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.database.NRegistry;
import ru.n08i40k.npluginapi.plugin.NPlugin;
import ru.n08i40k.npluginapi.plugin.NPluginManager;
import ru.n08i40k.npluginlocale.LocaleRequestBuilder;
import ru.n08i40k.npluginlocale.NColorUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class SelectPluginGuiHolder implements InventoryHolder {
    public enum Registry {
        ENTITY,
        BLOCK,
        ITEMSTACK,
        CRAFT_RECIPE
    }

    private static final LocaleRequestBuilder localeRoot =
            new LocaleRequestBuilder(null, "gui.list.select_plugin");

    private final Inventory inventory;

    @Nullable
    private final Registry predefinedRegistry;

    public static int getCount(NRegistry<?> nRegistry, NPlugin nPlugin) {
        return (int) nRegistry.getData().keySet().stream()
                .filter(nResourceKey -> nResourceKey.getNPlugin().equals(nPlugin)).count();
    }

    public SelectPluginGuiHolder(@Nullable Registry predefinedRegistry) {
        this.predefinedRegistry = predefinedRegistry;
//        inventory = Bukkit.createInventory(this, InventoryType.CRAFTING);
        Map<String, NPlugin> nPluginMap = NPluginApi.getInstance().getNPluginManager().getNPluginMap();
        int inventoryRows = Math.floorDiv(nPluginMap.size(), 9) + (Math.floorMod(nPluginMap.size(), 9) == 0 ? 0 : 1);

        Preconditions.checkState(inventoryRows > 0);
        Preconditions.checkState(inventoryRows < 6);

        inventory = Bukkit.createInventory(this, inventoryRows * 9,
                localeRoot.get("title").getSingle().getC());

        NPluginManager nPluginManager = NPluginApi.getInstance().getNPluginManager();

        AtomicInteger counter = new AtomicInteger();
        nPluginManager.getNPluginMap().forEach(
                (id, nPlugin) -> {
                    ItemStack icon = new ItemStack(nPlugin.getIcon());
                    ItemMeta iconMeta = icon.getItemMeta();
                    iconMeta.displayName(NColorUtils.translate(NColorUtils.parseGradientTags(nPlugin.getViewName()), false));

                    int entityCount = getCount(nPluginManager.getNEntityRegistry(), nPlugin);
                    int blockCount = getCount(nPluginManager.getNBlockRegistry(), nPlugin);
                    int itemStackCount = getCount(nPluginManager.getNItemStackRegistry(), nPlugin);
                    int craftRecipeCount = getCount(nPluginManager.getNCraftRecipeRegistry(), nPlugin);

                    /*
                     * Количество сущностей: %entity_count%
                     * Количество блоков: %block_count%
                     * Количество предметов: %itemstack_count%
                     * Количество крафтов: %craft_recipe_count%
                     * */
                    List<Component> lore = localeRoot.get("item.description")
                            .format(Map.of(
                                    "entity_count", entityCount,
                                    "block_count", blockCount,
                                    "itemstack_count", itemStackCount,
                                    "craft_recipe_count", craftRecipeCount
                            )).getMultiple().getC();
                    iconMeta.lore(lore);
                    icon.setItemMeta(iconMeta);

                    NBTItem nbtIcon = new NBTItem(icon);
                    nbtIcon.addCompound("nplugin-api").setString("plugin-id", nPlugin.getId());

                    inventory.setItem(counter.getAndIncrement(), nbtIcon.getItem());
                });
    }
}
