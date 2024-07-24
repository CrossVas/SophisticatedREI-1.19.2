package dev.crossvas.sophisticatedrei.mods.storage;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ExclusionZones;
import me.shedaniel.rei.plugin.common.displays.DefaultSmithingDisplay;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultCraftingDisplay;
import net.p3pp3rf1y.sophisticatedstorage.client.gui.StorageScreen;
import net.p3pp3rf1y.sophisticatedstorage.client.gui.StorageSettingsScreen;
import net.p3pp3rf1y.sophisticatedstorage.compat.jei.*;

import java.util.List;

public class REIStorageClientPlugin {

    public static void registerDisplays(DisplayRegistry registry) {
        DyeRecipesMaker.getRecipes().forEach(recipe -> registry.add(DefaultCraftingDisplay.of(recipe)));
        TierUpgradeRecipesMaker.getCraftingRecipes().forEach(recipe -> registry.add(DefaultCraftingDisplay.of(recipe)));
        TierUpgradeRecipesMaker.getSmithingRecipes().forEach(recipe -> registry.add(new DefaultSmithingDisplay(recipe)));
        ControllerRecipesMaker.getRecipes().forEach(recipe -> registry.add(DefaultCraftingDisplay.of(recipe)));
        ShulkerBoxFromChestRecipesMaker.getRecipes().forEach(recipe -> registry.add(DefaultCraftingDisplay.of(recipe)));
        FlatBarrelRecipesMaker.getRecipes().forEach(recipe -> registry.add(DefaultCraftingDisplay.of(recipe)));
    }

    public static void registerExclusionZones(ExclusionZones zones) {
        zones.register(StorageScreen.class, gui -> {
            List<Rectangle> ret = new ObjectArrayList<>();
            gui.getUpgradeSlotsRectangle().ifPresent(rect2i -> ret.add(new Rectangle(rect2i.getX(), rect2i.getY(), rect2i.getWidth(), rect2i.getHeight())));
            List<Rectangle> rects = new ObjectArrayList<>();
            gui.getUpgradeSettingsControl().getTabRectangles().forEach(rect2i -> {
                rects.add(new Rectangle(rect2i.getX(), rect2i.getY(), rect2i.getWidth(), rect2i.getHeight()));
            });
            ret.addAll(rects);
            gui.getSortButtonsRectangle().ifPresent(rect2i -> ret.add(new Rectangle(rect2i.getX(), rect2i.getY(), rect2i.getWidth(), rect2i.getHeight())));
            return ret;
        });

        zones.register(StorageSettingsScreen.class, gui -> {
            List<Rectangle> rects = new ObjectArrayList<>();
            gui.getSettingsTabControl().getTabRectangles().forEach(
                    rect2i -> rects.add(new Rectangle(rect2i.getX(), rect2i.getY(), rect2i.getWidth(), rect2i.getHeight()))
            );
            return rects;
        });
    }
}
