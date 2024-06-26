package dev.crossvas.sophisticatedrei;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.settings.itemdisplay.ItemDisplaySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class SetMemorySlotMessage {
    private final ItemStack stack;
    private final int slotNumber;

    public SetMemorySlotMessage(ItemStack stack, int slotNumber) {
        this.stack = stack;
        this.slotNumber = slotNumber;
    }

    public static void encode(SetMemorySlotMessage msg, FriendlyByteBuf packetBuffer) {
        packetBuffer.writeItem(msg.stack);
        packetBuffer.writeShort(msg.slotNumber);
    }

    public static SetMemorySlotMessage decode(FriendlyByteBuf packetBuffer) {
        return new SetMemorySlotMessage(packetBuffer.readItem(), packetBuffer.readShort());
    }

    static void onMessage(SetMemorySlotMessage msg, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = (NetworkEvent.Context)contextSupplier.get();
        context.enqueueWork(() -> {
            handleMessage(msg, context.getSender());
        });
        context.setPacketHandled(true);
    }

    private static void handleMessage(SetMemorySlotMessage msg, @Nullable ServerPlayer sender) {
        if (sender != null) {
            AbstractContainerMenu var3 = sender.containerMenu;
            if (var3 instanceof SettingsContainerMenu<?> settingsContainerMenu) {
                IStorageWrapper storageWrapper = settingsContainerMenu.getStorageWrapper();
                ((MemorySettingsCategory)storageWrapper.getSettingsHandler().getTypeCategory(MemorySettingsCategory.class)).setFilter(msg.slotNumber, msg.stack);
                ((ItemDisplaySettingsCategory)storageWrapper.getSettingsHandler().getTypeCategory(ItemDisplaySettingsCategory.class)).itemChanged(msg.slotNumber);
                storageWrapper.getInventoryHandler().onSlotFilterChanged(msg.slotNumber);
                settingsContainerMenu.sendAdditionalSlotInfo();
            }
        }

    }
}
