package ru.n08i40k.npluginapi.registry;

import lombok.NonNull;
import ru.n08i40k.npluginapi.custom.entity.NEntity;
import ru.n08i40k.npluginapi.resource.NResourceKey;

public class NEntityRegistry extends NRegistry<NEntity<?>> {
    public NEntityRegistry() {}

    public NEntity<?> getNEntity(@NonNull NResourceKey nResourceKey) {
        return super.getElement(nResourceKey);
    }
}
