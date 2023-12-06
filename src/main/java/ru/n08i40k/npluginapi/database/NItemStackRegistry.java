package ru.n08i40k.npluginapi.database;

import lombok.NonNull;
import ru.n08i40k.npluginapi.entity.NEntity;
import ru.n08i40k.npluginapi.itemStack.NItemStack;
import ru.n08i40k.npluginapi.resource.NResourceKey;

public class NItemStackRegistry extends NRegistry<NItemStack> {
    public NItemStackRegistry() {}

    public NItemStack getNItemStack(@NonNull NResourceKey nResourceKey) {
        return super.getElement(nResourceKey);
    }
}
