package dev.crossvas.sophisticatedrei;

import dev.crossvas.sophisticatedrei.handlers.SettingsScreenDraggableHandler;
import dev.crossvas.sophisticatedrei.handlers.StorageScreenDraggableHandler;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.screen.ExclusionZones;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.forge.REIPluginClient;
import net.minecraftforge.fml.ModList;

import java.util.List;

@REIPluginClient
public class SophisticatedREIPlugin implements REIClientPlugin {

    public final boolean BACKPACKS = ModList.get().isLoaded("sophisticatedbackpacks");
    public static final boolean STORAGE = ModList.get().isLoaded("sophisticatedstorage");

    @Override
    public void registerTransferHandlers(TransferHandlerRegistry registry) {

    }

    @Override
    public void registerExclusionZones(ExclusionZones zones) {
        if (STORAGE) {
            registerStorageZones(zones);
        }
        if (BACKPACKS) {
            registerBackpackZones(zones);
        }

    }

    public void registerBackpackZones(ExclusionZones zones) {
        zones.register(net.p3pp3rf1y.sophisticatedbackpacks.client.gui.BackpackScreen.class, gui -> {
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

        zones.register(net.p3pp3rf1y.sophisticatedbackpacks.client.gui.BackpackSettingsScreen.class, gui -> {
            List<Rectangle> rects = new ObjectArrayList<>();
            gui.getSettingsTabControl().getTabRectangles().forEach(
                    rect2i -> rects.add(new Rectangle(rect2i.getX(), rect2i.getY(), rect2i.getWidth(), rect2i.getHeight()))
            );
            return rects;
        });
    }

    public void registerStorageZones(ExclusionZones zones) {
        zones.register(net.p3pp3rf1y.sophisticatedstorage.client.gui.StorageScreen.class, gui -> {
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

        zones.register(net.p3pp3rf1y.sophisticatedstorage.client.gui.StorageSettingsScreen.class, gui -> {
            List<Rectangle> rects = new ObjectArrayList<>();
            gui.getSettingsTabControl().getTabRectangles().forEach(
                    rect2i -> rects.add(new Rectangle(rect2i.getX(), rect2i.getY(), rect2i.getWidth(), rect2i.getHeight()))
            );
            return rects;
        });
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerDraggableStackVisitor(new SettingsScreenDraggableHandler());
        registry.registerDraggableStackVisitor(new StorageScreenDraggableHandler());
    }
}
