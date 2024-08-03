package ru.n08i40k.npluginapi.custom.enchantment;

import lombok.Getter;

@SuppressWarnings("unused")
@Getter
public enum NEnchantmentRarity {
    COMMON(1, 0x888888),
    UNCOMMON(2, 0xAAAAAA),
    RARE(5, 0xCCCCCC),
    VERY_RARE(10, 0xFFFFFF);

    private final int weight;
    private final int color;

    NEnchantmentRarity(int weight, int color) {
        this.weight = weight;
        this.color = color;
    }
}
