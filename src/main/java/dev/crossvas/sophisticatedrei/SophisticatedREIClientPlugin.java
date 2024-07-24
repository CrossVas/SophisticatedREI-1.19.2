package dev.crossvas.sophisticatedrei;

import dev.crossvas.sophisticatedrei.handlers.CraftingRecipeTransferHandler;
import dev.crossvas.sophisticatedrei.handlers.SettingsScreenDraggableHandler;
import dev.crossvas.sophisticatedrei.handlers.StorageScreenDraggableHandler;
import dev.crossvas.sophisticatedrei.mods.backpacks.REIBackpacksClientPlugin;
import dev.crossvas.sophisticatedrei.mods.storage.REIStorageClientPlugin;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ExclusionZones;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.forge.REIPluginClient;

@REIPluginClient
public class SophisticatedREIClientPlugin implements REIClientPlugin {

    @Override
    public void registerTransferHandlers(TransferHandlerRegistry registry) {
        registry.register(new CraftingRecipeTransferHandler());
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        if (SophisticatedREI.STORAGE) {
            REIStorageClientPlugin.registerDisplays(registry);
        }
        if (SophisticatedREI.BACKPACKS) {
            REIBackpacksClientPlugin.registerDisplays(registry);
        }
    }

    @Override
    public void registerExclusionZones(ExclusionZones zones) {
        if (SophisticatedREI.STORAGE) {
            REIStorageClientPlugin.registerExclusionZones(zones);
        }
        if (SophisticatedREI.BACKPACKS) {
            REIBackpacksClientPlugin.registerExclusionZones(zones);
        }

    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerDraggableStackVisitor(new SettingsScreenDraggableHandler());
        registry.registerDraggableStackVisitor(new StorageScreenDraggableHandler());
    }
}
