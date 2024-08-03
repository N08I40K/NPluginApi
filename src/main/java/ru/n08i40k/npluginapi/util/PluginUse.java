package ru.n08i40k.npluginapi.util;

import org.slf4j.Logger;
import ru.n08i40k.npluginapi.NPluginApi;
import ru.n08i40k.npluginlocale.Locale;

@SuppressWarnings("unused")
public class PluginUse {
    protected final NPluginApi plugin;
    protected final Locale locale;
    protected final Logger logger;

    public PluginUse() {
        plugin = NPluginApi.getInstance();
        locale = Locale.getInstance();
        logger = plugin.getSLF4JLogger();
    }
}
