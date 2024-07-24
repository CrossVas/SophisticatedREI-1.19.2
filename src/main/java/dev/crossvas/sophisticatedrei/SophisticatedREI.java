package dev.crossvas.sophisticatedrei;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.p3pp3rf1y.sophisticatedcore.network.PacketHandler;

@Mod(SophisticatedREI.ID)
public class SophisticatedREI {

    public static final String ID = "sophisticatedrei";

    public static final boolean BACKPACKS = ModList.get().isLoaded("sophisticatedbackpacks");
    public static final boolean STORAGE = ModList.get().isLoaded("sophisticatedstorage");

    public SophisticatedREI() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(FMLCommonSetupEvent event) {
        PacketHandler.INSTANCE.registerMessage(SetGhostSlotMessage.class, SetGhostSlotMessage::encode, SetGhostSlotMessage::decode, SetGhostSlotMessage::onMessage);
        PacketHandler.INSTANCE.registerMessage(TransferRecipeMessage.class, TransferRecipeMessage::encode, TransferRecipeMessage::decode, TransferRecipeMessage::onMessage);
        PacketHandler.INSTANCE.registerMessage(SetMemorySlotMessage.class, SetMemorySlotMessage::encode, SetMemorySlotMessage::decode, SetMemorySlotMessage::onMessage);
    }
}
