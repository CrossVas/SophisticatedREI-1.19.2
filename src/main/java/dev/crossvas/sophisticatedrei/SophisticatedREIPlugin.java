package dev.crossvas.sophisticatedrei;

import dev.crossvas.sophisticatedrei.mods.backpacks.REIBackpacksPlugin;
import dev.crossvas.sophisticatedrei.mods.storage.REIStoragePlugin;
import me.shedaniel.rei.api.common.entry.comparison.ItemComparatorRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import me.shedaniel.rei.forge.REIPluginCommon;

@REIPluginCommon
public class SophisticatedREIPlugin implements REIServerPlugin {

    @Override
    public void registerItemComparators(ItemComparatorRegistry registry) {
        if (SophisticatedREI.STORAGE) {
            REIStoragePlugin.registerItemComparators(registry);
        }

        if (SophisticatedREI.BACKPACKS) {
            REIBackpacksPlugin.registerItemComparators(registry);
        }
    }
}
