package ru.n08i40k.npluginapi.gui.list.view;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.*;
import ru.n08i40k.npluginapi.craft.NCraftRecipe;

import java.util.Map;

@Getter
public class ViewCraftRecipeGuiHolder implements InventoryHolder {
    private final Inventory inventory;
    private final String pluginId;

    public ViewCraftRecipeGuiHolder(@NonNull NCraftRecipe nCraftRecipe) {
        pluginId = nCraftRecipe.getNPlugin().getId();
        inventory = Bukkit.createInventory(this, InventoryType.WORKBENCH);

        Recipe recipe = Bukkit.getRecipe(nCraftRecipe.getNResourceKey().getNamespacedKey());
        Preconditions.checkNotNull(recipe, "Can't get recipe of %s!", nCraftRecipe.getNResourceKey().toString());
        Preconditions.checkState(recipe instanceof ShapedRecipe, "Recipe isn't shaped! %s", nCraftRecipe.getNResourceKey().toString());
        ShapedRecipe shapedRecipe = (ShapedRecipe) recipe;

        String[] shape = shapedRecipe.getShape();
        Map<Character, ItemStack> ingredients = shapedRecipe.getIngredientMap();

        inventory.setItem(0, nCraftRecipe.getNItemStack().getItemStack());

        for (int i = 0; i < 3; ++i) {
            //noinspection ConstantValue
            if (i > shape.length) {
                inventory.setItem(1 + i * 3, new ItemStack(Material.AIR));
                inventory.setItem(1 + i * 3 + 1, new ItemStack(Material.AIR));
                inventory.setItem(1 + i * 3 + 2, new ItemStack(Material.AIR));

                continue;
            }
            // ROW

            for (int j = 0; j < 3; ++j) {
                if (j > shape[i].length())
                    inventory.setItem(1 + i * 3 + j, new ItemStack(Material.AIR));
                else
                    inventory.setItem(1 + i * 3 + j, ingredients.get(shape[i].toCharArray()[j]));
            }
        }
    }
}
