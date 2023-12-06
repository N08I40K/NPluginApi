package ru.n08i40k.npluginapi.resource;

import java.util.List;

public interface NResourceGroup {
    String CRAFT = "craft";
    String ITEM = "item";
    String ENTITY = "entity";
    String BLOCK = "block";

    List<String> ALL_GROUPS = List.of(
            CRAFT,
            ITEM,
            ENTITY,
            BLOCK
    );
}
