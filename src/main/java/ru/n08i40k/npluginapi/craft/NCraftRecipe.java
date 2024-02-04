package ru.n08i40k.npluginapi.craft;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.database.NItemStackRegistry;
import ru.n08i40k.npluginapi.itemStack.NItemStack;
import ru.n08i40k.npluginapi.plugin.NPlugin;
import ru.n08i40k.npluginapi.resource.INResourceKeyHolder;
import ru.n08i40k.npluginapi.resource.NResourceGroup;
import ru.n08i40k.npluginapi.resource.NResourceKey;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class NCraftRecipe implements INResourceKeyHolder {
    @NonNull
    private final NCraftRecipeEntry entry;

    @NonNull
    private final NPlugin nPlugin;

    @NonNull
    private final String id;

    @NonNull
    private final NItemStack nItemStack;

    @NonNull
    private final NResourceKey nResourceKey;

    private boolean registered;

    public NCraftRecipe(@NonNull NPlugin nPlugin,
                        @NonNull String id,
                        @NonNull NCraftRecipeEntry entry,
                        @NonNull NItemStack nItemStack) {
        this.nPlugin = nPlugin;
        this.id = id;
        this.entry = entry;
        this.nItemStack = nItemStack;

        nResourceKey = new NResourceKey(nPlugin, NResourceGroup.CRAFT, id);
    }

    private Map<Character, ItemStack> getIngredients() {
        Map<Character, String> source = entry.getIngredients();
        Map<Character, ItemStack> result = new HashMap<>();

        Pattern pattern = Pattern.compile("[a-z0-9]+:" + NResourceGroup.ITEM + "/[a-z0-9_\\-]+");

        source.forEach((key, value) -> {
            Matcher matcher = pattern.matcher(value);

            if (!matcher.find()) {
                result.put(key, new ItemStack(Material.valueOf(value)));
                return;
            }

            NItemStackRegistry itemStackDatabase = NPluginApi.getInstance().getNPluginManager().getNItemStackRegistry();

            result.put(key,
                    itemStackDatabase.getNItemStack(NResourceKey.parse(matcher.group(0)))
                            .getItemStack());
        });

        return result;
    }

    public void register() {
        Preconditions.checkState(!registered, "NCraftRecipe %s from %s already registered!", id, nPlugin.getId());

        ShapedRecipe recipe = new ShapedRecipe(nResourceKey.getNamespacedKey(), nItemStack.getItemStack());

        recipe.shape(entry.getShape().toArray(new String[0]));

        Map<Character, ItemStack> ingredients = getIngredients(); // only after all NCraftRecipe added!!!

        Character[] ingredientKeys = ingredients.keySet().toArray(new Character[0]);
        ItemStack[] ingredientItems = ingredients.values().toArray(new ItemStack[0]);

        for (int i = 0; i < ingredients.size(); ++i)
            recipe.setIngredient(ingredientKeys[i], ingredientItems[i]);

        Bukkit.addRecipe(recipe);

        registered = true;
    }

    public void unregister() {
        if (!registered)
            return;

        Bukkit.removeRecipe(nResourceKey.getNamespacedKey());

        registered = false;
    }
}
