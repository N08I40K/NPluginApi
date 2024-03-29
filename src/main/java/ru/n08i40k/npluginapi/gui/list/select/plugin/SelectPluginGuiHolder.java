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
import ru.n08i40k.npluginapi.registry.NRegistry;
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
        ENTITY("entity"),
        BLOCK("block"),
        ITEMSTACK("itemStack"),
        CRAFT_RECIPE("craftRecipe"),
        ENCHANTMENT("enchantment");

        public final String localeTag;
        Registry(String localeTag) {
            this.localeTag = localeTag;
        }
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

                    /*
                     * Количество предметов: %itemstack_count%
                     * Количество крафтов: %craft_recipe_count%
                     * Количество зачарований: %enchantment_count%
                     * Количество сущностей: %entity_count%
                     * Количество блоков: %block_count%
                     * */
                    List<Component> lore = localeRoot.get("item.description")
                            .format(Map.of(
                                    "entity_count", getCount(nPluginManager.getNEntityRegistry(), nPlugin),
                                    "block_count", getCount(nPluginManager.getNBlockRegistry(), nPlugin),
                                    "itemstack_count", getCount(nPluginManager.getNItemStackRegistry(), nPlugin),
                                    "craft_recipe_count", getCount(nPluginManager.getNCraftRecipeRegistry(), nPlugin),
                                    "enchantment_count", getCount(nPluginManager.getNEnchantmentRegistry(), nPlugin)
                            )).getMultiple().getC();
                    iconMeta.lore(lore);
                    icon.setItemMeta(iconMeta);

                    NBTItem nbtIcon = new NBTItem(icon);
                    nbtIcon.addCompound("nplugin-api").setString("nPluginId", nPlugin.getId());

                    inventory.setItem(counter.getAndIncrement(), nbtIcon.getItem());
                });
    }
}
