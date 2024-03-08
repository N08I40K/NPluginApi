package ru.n08i40k.npluginapi.registry;

import lombok.NonNull;
import ru.n08i40k.npluginapi.custom.enchantment.NEnchantment;
import ru.n08i40k.npluginapi.resource.NResourceKey;

public class NEnchantmentRegistry extends NRegistry<NEnchantment> {
    public NEnchantmentRegistry() {
    }

    public NEnchantment getNEnchantment(@NonNull NResourceKey nResourceKey) {
        return super.getElement(nResourceKey);
    }

    @Override
    public NEnchantment add(@NonNull NEnchantment enchantment) {


        super.add(enchantment);
        enchantment.register();

        return enchantment;
    }

    @Override
    public NEnchantment remove(@NonNull NResourceKey nResourceKey) {
        NEnchantment nEnchantment = super.remove(nResourceKey);
        nEnchantment.unregister();

        return nEnchantment;
    }
}
