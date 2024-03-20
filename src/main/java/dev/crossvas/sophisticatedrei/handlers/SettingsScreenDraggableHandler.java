package dev.crossvas.sophisticatedrei.handlers;

import dev.crossvas.sophisticatedrei.SetMemorySlotMessage;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.drag.DraggableStack;
import me.shedaniel.rei.api.client.gui.drag.DraggableStackVisitor;
import me.shedaniel.rei.api.client.gui.drag.DraggedAcceptorResult;
import me.shedaniel.rei.api.client.gui.drag.DraggingContext;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsScreen;
import net.p3pp3rf1y.sophisticatedcore.network.PacketHandler;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsTab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class SettingsScreenDraggableHandler implements DraggableStackVisitor<SettingsScreen> {

    @Override
    public <R extends Screen> boolean isHandingScreen(R r) {
        return r instanceof SettingsScreen;
    }

    @Override
    public Stream<BoundsProvider> getDraggableAcceptingBounds(DraggingContext<SettingsScreen> context, DraggableStack stack) {
        List<ItemDropTarget> targets = getTargets(context, stack);
        return targets.stream().map(target -> BoundsProvider.ofRectangle(target.getArea()));
    }

    @Override
    public DraggedAcceptorResult acceptDraggedStack(DraggingContext<SettingsScreen> context, DraggableStack stack) {
        List<ItemDropTarget> targets = getTargets(context, stack);
        Point pos = context.getCurrentPosition();
        for (ItemDropTarget target : targets) {
            if (target.getArea().contains(pos) && target.accept(stack)) {
                return DraggedAcceptorResult.ACCEPTED;
            }
        }
        return DraggedAcceptorResult.PASS;
    }

    private List<ItemDropTarget> getTargets(DraggingContext<SettingsScreen> context, DraggableStack stack) {
        var wrapped = stack.getStack().cast();
        if (wrapped == null) {
            return Collections.emptyList();
        }

        List<ItemDropTarget> targets = new ArrayList<>();
        addItemStackTargets(context.getScreen(), targets);
        return targets;
    }

    private static void addItemStackTargets(AbstractContainerScreen<?> gui, List<ItemDropTarget> targets) {
        if (gui instanceof SettingsScreen screen) {
            screen.getSettingsTabControl().getOpenTab().ifPresent(tab -> {
                if (tab instanceof MemorySettingsTab) {
                    screen.getMenu().getStorageInventorySlots().forEach(s -> {
                        if (s.getItem().isEmpty()) {
                            targets.add(new ItemDropTarget(gui, s));
                        }
                    });
                }
            });
        }
    }

    public static class ItemDropTarget implements IDropTarget {
        private final Slot slot;
        private final Rectangle area;

        public ItemDropTarget(AbstractContainerScreen<?> screen, Slot slot) {
            this.slot = slot;
            this.area = new Rectangle(screen.getGuiLeft() + slot.x, screen.getGuiTop() + slot.y, 16, 16);
        }

        @Override
        public Rectangle getArea() {
            return this.area;
        }

        @Override
        public boolean accept(DraggableStack stack) {
            EntryStack<?> entryStack = stack.getStack();
            if (entryStack.getType() == VanillaEntryTypes.ITEM) {
                ItemStack itemStack = entryStack.castValue();
                PacketHandler.INSTANCE.sendToServer(new SetMemorySlotMessage(itemStack, this.slot.index));
                return true;
            }
            return false;
        }
    }
}
