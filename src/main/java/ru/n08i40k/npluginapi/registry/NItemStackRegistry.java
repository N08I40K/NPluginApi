package ru.n08i40k.npluginapi.registry;

import lombok.NonNull;
import ru.n08i40k.npluginapi.custom.itemStack.NItemStack;
import ru.n08i40k.npluginapi.resource.NResourceKey;

public class NItemStackRegistry extends NRegistry<NItemStack> {
    public NItemStackRegistry() {}

    public NItemStack getNItemStack(@NonNull NResourceKey nResourceKey) {
        return super.getElement(nResourceKey);
    }
}
