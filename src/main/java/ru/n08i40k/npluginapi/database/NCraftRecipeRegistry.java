package ru.n08i40k.npluginapi.database;

import lombok.Getter;
import lombok.NonNull;
import ru.n08i40k.npluginapi.craft.NCraftRecipe;
import ru.n08i40k.npluginapi.itemStack.NItemStack;
import ru.n08i40k.npluginapi.resource.NResourceKey;

import java.util.HashMap;
import java.util.Map;

@NonNull
@Getter
public class NCraftRecipeRegistry extends NRegistry<NCraftRecipe> {
    private final Map<String, NCraftRecipe> craftRecipeMap;

    public NCraftRecipeRegistry() {
        craftRecipeMap = new HashMap<>();
    }

    @Override
    public NCraftRecipe add(@NonNull NCraftRecipe craftRecipe) {
        super.add(craftRecipe);
        craftRecipe.register();

        return craftRecipe;
    }

    @Override
    public NCraftRecipe remove(@NonNull NResourceKey nResourceKey) {
        NCraftRecipe nCraftRecipe = super.remove(nResourceKey);
        nCraftRecipe.unregister();

        return nCraftRecipe;
    }

    public NCraftRecipe getNCraftRecipe(@NonNull NResourceKey nResourceKey) {
        return getElement(nResourceKey);
    }

    public NItemStack getResultItem(@NonNull NResourceKey nResourceKey) {
        return getNCraftRecipe(nResourceKey).getNItemStack();
    }
}
