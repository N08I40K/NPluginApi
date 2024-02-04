package ru.n08i40k.npluginapi.plugin;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import ru.n08i40k.npluginapi.NPluginApi;

import javax.annotation.Nullable;

@Getter
@NonNull
public class NPlugin {
    private final Plugin plugin;
    private final String id;

    private final String viewName;
    private final Material icon;

    public NPlugin(@NonNull Plugin plugin, @NonNull String id, @Nullable String viewName, @Nullable Material icon) {
        this.plugin = plugin;
        this.id = id;

        this.viewName = viewName == null ? id : viewName;
        this.icon = icon == null ? Material.PAPER : icon;
    }

    public void unregister() {
        NPluginManager nPluginManager = NPluginApi.getInstance().getNPluginManager();

        nPluginManager.getNItemStackRegistry().removeAll(this);
        nPluginManager.getNCraftRecipeRegistry().removeAll(this);
        nPluginManager.getNEntityRegistry().removeAll(this);
        nPluginManager.getNBlockRegistry().removeAll(this);
    }
}
