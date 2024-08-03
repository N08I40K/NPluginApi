package ru.n08i40k.npluginapi;

import lombok.Getter;
import meteordevelopment.orbit.IEventBus;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.n08i40k.npluginapi.command.MainCommand;
import ru.n08i40k.npluginapi.event.NPluginApiUnloadEvent;
import ru.n08i40k.npluginapi.event.NPluginBusManager;
import ru.n08i40k.npluginapi.gui.list.select.craftRecipe.SelectCraftRecipeEventListener;
import ru.n08i40k.npluginapi.gui.list.select.enchantment.SelectEnchantmentEventListener;
import ru.n08i40k.npluginapi.gui.list.select.itemStack.SelectItemStackEventListener;
import ru.n08i40k.npluginapi.gui.list.select.plugin.SelectPluginEventListener;
import ru.n08i40k.npluginapi.gui.list.select.registry.SelectRegistryEventListener;
import ru.n08i40k.npluginapi.gui.list.view.ViewCraftRecipeEventListener;
import ru.n08i40k.npluginapi.listener.NBlockEventListener;
import ru.n08i40k.npluginapi.listener.NEntityEventListener;
import ru.n08i40k.npluginapi.listener.NItemStackEventListener;
import ru.n08i40k.npluginapi.listener.NSharedEventListener;
import ru.n08i40k.npluginapi.plugin.NPluginManager;
import ru.n08i40k.npluginlocale.Locale;

import java.util.List;

public final class NPluginApi extends JavaPlugin {
    public static String PLUGIN_NAME = "NPluginApi";
    public static String PLUGIN_NAME_LOWER = "nplugin-api";
    public static String PLUGIN_NAME_SHORT = "npapi";

    @Getter
    private static NPluginApi instance;

    @Getter
    private NPluginManager nPluginManager;

    @Override
    public void onLoad() {
        instance = this;

        IEventBus bus = NPluginBusManager.initEventBus();

        nPluginManager = new NPluginManager();

        bus.subscribe(nPluginManager);
    }

    @Override
    public void onEnable() {
        new Locale(this, NPluginBusManager.getEventBus(), "ru-RU");

        // Register command
        getServer().getCommandMap().registerAll(PLUGIN_NAME_LOWER, List.of(new MainCommand()));

        PluginManager pluginManager = getServer().getPluginManager();

        // N<> events
        pluginManager.registerEvents(new NBlockEventListener(), this);
        pluginManager.registerEvents(new NItemStackEventListener(), this);
        pluginManager.registerEvents(new NEntityEventListener(), this);
        pluginManager.registerEvents(new NSharedEventListener(), this);

        // gui events
        pluginManager.registerEvents(new SelectPluginEventListener(), this);
        pluginManager.registerEvents(new SelectRegistryEventListener(), this);

        pluginManager.registerEvents(new SelectItemStackEventListener(), this);
        pluginManager.registerEvents(new SelectCraftRecipeEventListener(), this);
        pluginManager.registerEvents(new SelectEnchantmentEventListener(), this);

        pluginManager.registerEvents(new ViewCraftRecipeEventListener(), this);
    }

    @Override
    public void onDisable() {
        NPluginBusManager.getEventBus().post(new NPluginApiUnloadEvent());
    }
}
