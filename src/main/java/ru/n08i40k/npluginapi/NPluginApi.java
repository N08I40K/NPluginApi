package ru.n08i40k.npluginapi;

import lombok.Getter;
import meteordevelopment.orbit.IEventBus;
import org.bukkit.plugin.java.JavaPlugin;
import ru.n08i40k.npluginapi.event.NPluginBusManager;
import ru.n08i40k.npluginapi.listener.NBlockEventListener;
import ru.n08i40k.npluginapi.listener.NEntityEventListener;
import ru.n08i40k.npluginapi.listener.NItemStackEventListener;
import ru.n08i40k.npluginapi.listener.NSharedListener;
import ru.n08i40k.npluginapi.plugin.NPluginManager;

public final class NPluginApi extends JavaPlugin {

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
        getServer().getPluginManager().registerEvents(new NBlockEventListener(), this);
        getServer().getPluginManager().registerEvents(new NItemStackEventListener(), this);
        getServer().getPluginManager().registerEvents(new NEntityEventListener(), this);
        getServer().getPluginManager().registerEvents(new NSharedListener(), this);
    }

    @Override
    public void onDisable() {}
}
