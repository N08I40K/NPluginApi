package ru.n08i40k.npluginapi.database;

import lombok.NonNull;
import ru.n08i40k.npluginapi.block.NBlock;
import ru.n08i40k.npluginapi.entity.NEntity;
import ru.n08i40k.npluginapi.resource.NResourceKey;

public class NEntityRegistry extends NRegistry<NEntity<?>> {
    public NEntityRegistry() {}

    public NEntity<?> getNEntity(@NonNull NResourceKey nResourceKey) {
        return super.getElement(nResourceKey);
    }
}
