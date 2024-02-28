package ru.n08i40k.npluginapi.listener.protocollib;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.plugin.Plugin;
import ru.n08i40k.npluginapi.custom.enchantment.NEnchantmentAccessor;

import java.util.ArrayList;
import java.util.List;

public class TradeListPacketListener extends PacketAdapter {
    public TradeListPacketListener(@NonNull Plugin plugin) {
        super(plugin, PacketType.Play.Server.OPEN_WINDOW_MERCHANT);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        if (event.getPacketType() != PacketType.Play.Server.OPEN_WINDOW_MERCHANT)
            return;

        PacketContainer container = event.getPacket();

        List<MerchantRecipe> recipes = container.getMerchantRecipeLists().read(0);

        List<MerchantRecipe> newRecipes = new ArrayList<>();

        for (MerchantRecipe recipe : recipes) {
            ItemStack itemStack = recipe.getResult();

            if (itemStack.getType() == Material.AIR || itemStack.getItemMeta() == null) {
                newRecipes.add(recipe);
                continue;
            }

            NEnchantmentAccessor.NEnchantedLore.clearLore(itemStack);
            NEnchantmentAccessor.NEnchantedLore.setLore(itemStack);

            MerchantRecipe merchantRecipe = new MerchantRecipe(itemStack,
                    recipe.getUses(),
                    recipe.getMaxUses(),
                    recipe.hasExperienceReward(),
                    recipe.getVillagerExperience(),
                    recipe.getPriceMultiplier(),
                    recipe.shouldIgnoreDiscounts());
            merchantRecipe.setIngredients(recipe.getIngredients());

            newRecipes.add(merchantRecipe);
        }

        recipes.clear();
        recipes.addAll(newRecipes);

        container.getMerchantRecipeLists().write(0, recipes);
    }
}
