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
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.BackpackScreen;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.BackpackSettingsScreen;

import java.util.List;

@REIPluginClient
public class SophisticatedREIPlugin implements REIClientPlugin {

    @Override
    public void registerTransferHandlers(TransferHandlerRegistry registry) {

    }

    @Override
    public void registerExclusionZones(ExclusionZones zones) {
        zones.register(BackpackScreen.class, gui -> {
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

        zones.register(BackpackSettingsScreen.class, gui -> {
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
