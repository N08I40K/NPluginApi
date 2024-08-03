package ru.n08i40k.npluginapi.resource;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import net.minecraft.resources.ResourceLocation;
import org.bukkit.NamespacedKey;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginapi.plugin.NPlugin;
import ru.n08i40k.npluginapi.plugin.NPluginManager;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class NResourceKey {
    @NonNull
    private final NPlugin nPlugin;

    @NonNull
    private final String objectId;

    @NonNull
    private final NamespacedKey namespacedKey;

    @NonNull
    private final ResourceLocation resourceLocation;

    public NResourceKey(@NonNull NPlugin nPlugin, @NonNull String objectId) {
        this.nPlugin = nPlugin;
        this.objectId = objectId;

        namespacedKey = new NamespacedKey(nPlugin.getId(), objectId);
        resourceLocation = ResourceLocation.fromNamespaceAndPath(namespacedKey.getNamespace(), namespacedKey.getKey());
    }

    public String toString() {
        return namespacedKey.toString();
    }

    public static NResourceKey parse(@NonNull String resourceKey) {
        Pattern pattern = Pattern.compile("([a-z0-9_\\-]+):([a-z0-9_\\-]+)");
        Matcher matcher = pattern.matcher(resourceKey);

        Preconditions.checkState(matcher.find(),
                "Invalid resourceKey: %s", resourceKey);

        String nPluginId = matcher.group(1);

        NPluginManager nPluginManager = NPluginApi.getInstance().getNPluginManager();
        Preconditions.checkState(nPluginManager.isNPluginRegistered(nPluginId),
                "Can't find NPlugin with id %s!", nPluginId);

        NPlugin nPlugin1 = nPluginManager.getNPlugin(nPluginId);

        return new NResourceKey(nPlugin1, matcher.group(2));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        return ((NResourceKey) obj).namespacedKey.equals(namespacedKey);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(namespacedKey.toString());
    }
}
