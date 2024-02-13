package ru.n08i40k.npluginapi.resource;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
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
    private final String groupId;

    @NonNull
    private final String objectId;

    @NonNull
    private final NamespacedKey namespacedKey;

    public NResourceKey(@NonNull NPlugin nPlugin, @NonNull String groupId, @NonNull String objectId) {
        this.nPlugin = nPlugin;
        this.groupId = groupId;
        this.objectId = objectId;

        namespacedKey = new NamespacedKey(nPlugin.getPlugin(), groupId + "/" + objectId);
    }

    public String toString() {
        return namespacedKey.toString();
    }

    public static NResourceKey parse(@NonNull String resourceKey) {
        Pattern pattern = Pattern.compile("([a-z0-9]+):([a-z]+)/([a-z0-9_\\-]+)");
        Matcher matcher = pattern.matcher(resourceKey);

        Preconditions.checkState(matcher.find(),
                "Invalid resourceKey: %s", resourceKey);

        NPluginManager nPluginManager = NPluginApi.getInstance().getNPluginManager();
        Preconditions.checkState(nPluginManager.isNPluginRegistered(matcher.group(1)),
                "Can't find NPlugin with getId %s!", matcher.group(1));

        Preconditions.checkState(NResourceGroup.ALL_GROUPS.contains(matcher.group(2)),
                "Broken NResourceGroup: %s", matcher.group(2));

        NPlugin nPlugin1 = nPluginManager.getNPlugin(matcher.group(1));

        return new NResourceKey(nPlugin1, matcher.group(2), matcher.group(3));
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
