package ru.n08i40k.npluginapi.plugin;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import meteordevelopment.orbit.EventHandler;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.database.NBlockRegistry;
import ru.n08i40k.npluginapi.database.NCraftRecipeRegistry;
import ru.n08i40k.npluginapi.database.NEntityRegistry;
import ru.n08i40k.npluginapi.event.NPluginUnloadEvent;
import ru.n08i40k.npluginapi.database.NItemStackRegistry;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@NonNull
public class NPluginManager {
    private final Map<String, NPlugin> nPluginMap;

    private final NCraftRecipeRegistry nCraftRecipeRegistry;
    private final NItemStackRegistry nItemStackRegistry;
    private final NEntityRegistry nEntityRegistry;
    private final NBlockRegistry nBlockRegistry;

    public NPluginManager() {
        nPluginMap = new HashMap<>();

        nCraftRecipeRegistry = new NCraftRecipeRegistry();
        nItemStackRegistry = new NItemStackRegistry();
        nEntityRegistry = new NEntityRegistry();
        nBlockRegistry = new NBlockRegistry();
    }


    // REGISTER
    public NPlugin registerNPlugin(@NonNull Plugin plugin, @NonNull String id, @Nullable String viewName, @Nullable Material icon) {
        id = id.toLowerCase();

        Preconditions.checkArgument(!nPluginMap.containsKey(id),
                String.format("%s plugin already registered!", id));

        NPluginApi.getInstance().getSLF4JLogger().info("NPlugin {} is registering...", id);

        NPlugin nPlugin = new NPlugin(plugin, id, viewName, icon);

        nPluginMap.put(id, nPlugin);

        return nPlugin;
    }

    public NPlugin registerNPlugin(@NonNull Plugin plugin, @NonNull String id) {
        return registerNPlugin(plugin, id, null, null);
    }

    public NPlugin registerNPlugin(@NonNull Plugin plugin) {
        return registerNPlugin(plugin, plugin.getName(), null, null);
    }

    // UNREGISTER
    public void unregisterNPlugin(@NonNull NPlugin nPlugin) {
        Preconditions.checkArgument(nPluginMap.containsKey(nPlugin.getId()),
                String.format("%s plugin doesn't registered!", nPlugin.getId()));

        nPlugin.unregister();

        nPluginMap.remove(nPlugin.getId());
    }


    // GET
    public NPlugin getNPlugin(@NonNull Plugin plugin) {
        Optional<NPlugin> optionalNPlugin = nPluginMap.values().stream()
                .filter(nPlugin -> nPlugin.getPlugin().equals(plugin))
                .findFirst();

        Preconditions.checkArgument(optionalNPlugin.isPresent(), "Cannot find NPlugin of %s plugin!", plugin.getName());

        return optionalNPlugin.get();
    }

    public NPlugin getNPlugin(@NonNull String id) {
        Preconditions.checkArgument(nPluginMap.containsKey(id), "Cannot find NPlugin of %s id!", id);

        return nPluginMap.get(id);
    }


    // CHECKS
    public boolean isNPluginRegistered(@NonNull String id) {
        return nPluginMap.containsKey(id);
    }


    // EVENTS

    @EventHandler
    public void onNPluginUnload(NPluginUnloadEvent event) {
        NPluginApi.getInstance().getSLF4JLogger().info("NPlugin {} is unregistering...", event.getNPlugin().getId());

        event.getNPlugin().unregister();
    }
}
