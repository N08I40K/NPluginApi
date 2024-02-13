package ru.n08i40k.npluginapi.custom.craft;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class NCraftRecipeEntry {
    private List<String> shape;

    private Map<Character, String> ingredients;

    public NCraftRecipeEntry() {
        shape = Arrays.asList(
                "BBB",
                "BBB",
                "BBB");

        ingredients = Map.of('B', Material.NETHERITE_BLOCK.name());
    }
}
