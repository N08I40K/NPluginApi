package ru.n08i40k.npluginapi.plugin;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.plugin.Plugin;
import ru.n08i40k.npluginapi.NPluginApi;

@Getter
@NonNull
public class NPlugin {
    private final Plugin plugin;
    private final String id;

    public NPlugin(@NonNull Plugin plugin, @NonNull String id) {
        this.plugin = plugin;
        this.id = id;
    }

    public void unregister() {
        NPluginManager nPluginManager = NPluginApi.getInstance().getNPluginManager();

        nPluginManager.getNItemStackRegistry().removeAll(this);
        nPluginManager.getNCraftRecipeRegistry().removeAll(this);
        nPluginManager.getNEntityRegistry().removeAll(this);
        nPluginManager.getNBlockRegistry().removeAll(this);
    }
}
