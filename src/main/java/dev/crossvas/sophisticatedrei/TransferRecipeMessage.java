package dev.crossvas.sophisticatedrei;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.p3pp3rf1y.sophisticatedcore.compat.jei.CraftingContainerRecipeTransferHandlerServer;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Supplier;

public class TransferRecipeMessage {
    private final Map<Integer, Integer> matchingItems;
    private final List<Integer> craftingSlotIndexes;
    private final List<Integer> inventorySlotIndexes;
    private final boolean maxTransfer;

    public TransferRecipeMessage(Map<Integer, Integer> matchingItems, List<Integer> craftingSlotIndexes, List<Integer> inventorySlotIndexes, boolean maxTransfer) {
        this.matchingItems = matchingItems;
        this.craftingSlotIndexes = craftingSlotIndexes;
        this.inventorySlotIndexes = inventorySlotIndexes;
        this.maxTransfer = maxTransfer;
    }

    public static void encode(TransferRecipeMessage msg, FriendlyByteBuf packetBuffer) {
        writeMap(packetBuffer, msg.matchingItems);
        writeList(packetBuffer, msg.craftingSlotIndexes);
        writeList(packetBuffer, msg.inventorySlotIndexes);
        packetBuffer.writeBoolean(msg.maxTransfer);
    }

    private static void writeMap(FriendlyByteBuf packetBuffer, Map<Integer, Integer> map) {
        packetBuffer.writeInt(map.size());
        map.forEach((key, value) -> {
            packetBuffer.writeInt(key);
            packetBuffer.writeInt(value);
        });
    }

    private static void writeList(FriendlyByteBuf packetBuffer, List<Integer> list) {
        packetBuffer.writeInt(list.size());
        Objects.requireNonNull(packetBuffer);
        list.forEach(packetBuffer::writeInt);
    }

    public static TransferRecipeMessage decode(FriendlyByteBuf packetBuffer) {
        return new TransferRecipeMessage(readMap(packetBuffer), readList(packetBuffer), readList(packetBuffer), packetBuffer.readBoolean());
    }

    private static Map<Integer, Integer> readMap(FriendlyByteBuf packetBuffer) {
        Map<Integer, Integer> ret = new HashMap<>();
        int size = packetBuffer.readInt();

        for(int i = 0; i < size; ++i) {
            ret.put(packetBuffer.readInt(), packetBuffer.readInt());
        }

        return ret;
    }

    private static List<Integer> readList(FriendlyByteBuf packetBuffer) {
        List<Integer> ret = new ArrayList<>();
        int size = packetBuffer.readInt();

        for(int i = 0; i < size; ++i) {
            ret.add(packetBuffer.readInt());
        }

        return ret;
    }

    static void onMessage(TransferRecipeMessage msg, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = (NetworkEvent.Context)contextSupplier.get();
        context.enqueueWork(() -> {
            handleMessage(msg, context.getSender());
        });
        context.setPacketHandled(true);
    }

    private static void handleMessage(TransferRecipeMessage msg, @Nullable ServerPlayer sender) {
        if (sender != null) {
            CraftingContainerRecipeTransferHandlerServer.setItems(sender, msg.matchingItems, msg.craftingSlotIndexes, msg.inventorySlotIndexes, msg.maxTransfer);
        }
    }
}
