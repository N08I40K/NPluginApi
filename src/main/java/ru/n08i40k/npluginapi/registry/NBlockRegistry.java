package ru.n08i40k.npluginapi.registry;

import lombok.NonNull;
import ru.n08i40k.npluginapi.custom.block.NBlock;
import ru.n08i40k.npluginapi.resource.NResourceKey;

public class NBlockRegistry extends NRegistry<NBlock> {
    public NBlockRegistry() {}

    public NBlock getNBlock(@NonNull NResourceKey nResourceKey) {
        return super.getElement(nResourceKey);
    }
}
