package ru.n08i40k.npluginapi.event;

import lombok.Getter;
import lombok.NonNull;
import ru.n08i40k.npluginapi.plugin.NPlugin;

@SuppressWarnings("unused")
@Getter
public class NPluginUnloadEvent {
    private final NPlugin nPlugin;

    public NPluginUnloadEvent(@NonNull NPlugin nPlugin) {
        this.nPlugin = nPlugin;
    }
}
