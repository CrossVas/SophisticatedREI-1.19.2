package dev.crossvas.sophisticatedrei.mods.storage;

import me.shedaniel.rei.api.common.entry.comparison.ItemComparatorRegistry;
import net.p3pp3rf1y.sophisticatedstorage.init.ModBlocks;

public class REIStoragePlugin {

    public static void registerItemComparators(ItemComparatorRegistry registry) {
        registry.registerNbt(
                ModBlocks.BARREL_ITEM.get(),
                ModBlocks.COPPER_BARREL_ITEM.get(),
                ModBlocks.IRON_BARREL_ITEM.get(),
                ModBlocks.GOLD_BARREL_ITEM.get(),
                ModBlocks.DIAMOND_BARREL_ITEM.get(),
                ModBlocks.NETHERITE_BARREL_ITEM.get(),
                ModBlocks.CHEST_ITEM.get(),
                ModBlocks.COPPER_CHEST_ITEM.get(),
                ModBlocks.IRON_CHEST_ITEM.get(),
                ModBlocks.GOLD_CHEST_ITEM.get(),
                ModBlocks.DIAMOND_CHEST_ITEM.get(),
                ModBlocks.NETHERITE_CHEST_ITEM.get(),
                ModBlocks.LIMITED_BARREL_1_ITEM.get(),
                ModBlocks.LIMITED_BARREL_2_ITEM.get(),
                ModBlocks.LIMITED_BARREL_3_ITEM.get(),
                ModBlocks.LIMITED_BARREL_4_ITEM.get(),
                ModBlocks.LIMITED_COPPER_BARREL_1_ITEM.get(),
                ModBlocks.LIMITED_COPPER_BARREL_2_ITEM.get(),
                ModBlocks.LIMITED_COPPER_BARREL_3_ITEM.get(),
                ModBlocks.LIMITED_COPPER_BARREL_4_ITEM.get(),
                ModBlocks.LIMITED_IRON_BARREL_1_ITEM.get(),
                ModBlocks.LIMITED_IRON_BARREL_2_ITEM.get(),
                ModBlocks.LIMITED_IRON_BARREL_3_ITEM.get(),
                ModBlocks.LIMITED_IRON_BARREL_4_ITEM.get(),
                ModBlocks.LIMITED_GOLD_BARREL_1_ITEM.get(),
                ModBlocks.LIMITED_GOLD_BARREL_2_ITEM.get(),
                ModBlocks.LIMITED_GOLD_BARREL_3_ITEM.get(),
                ModBlocks.LIMITED_GOLD_BARREL_4_ITEM.get(),
                ModBlocks.LIMITED_DIAMOND_BARREL_1_ITEM.get(),
                ModBlocks.LIMITED_DIAMOND_BARREL_2_ITEM.get(),
                ModBlocks.LIMITED_DIAMOND_BARREL_3_ITEM.get(),
                ModBlocks.LIMITED_DIAMOND_BARREL_4_ITEM.get(),
                ModBlocks.LIMITED_NETHERITE_BARREL_1_ITEM.get(),
                ModBlocks.LIMITED_NETHERITE_BARREL_2_ITEM.get(),
                ModBlocks.LIMITED_NETHERITE_BARREL_3_ITEM.get(),
                ModBlocks.LIMITED_NETHERITE_BARREL_4_ITEM.get(),

                ModBlocks.SHULKER_BOX_ITEM.get(),
                ModBlocks.COPPER_SHULKER_BOX_ITEM.get(),
                ModBlocks.IRON_SHULKER_BOX_ITEM.get(),
                ModBlocks.GOLD_SHULKER_BOX_ITEM.get(),
                ModBlocks.DIAMOND_SHULKER_BOX_ITEM.get(),
                ModBlocks.NETHERITE_SHULKER_BOX_ITEM.get()
        );
    }
}
