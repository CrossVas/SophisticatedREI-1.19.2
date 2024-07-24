package dev.crossvas.sophisticatedrei.mods.backpacks;

import me.shedaniel.rei.api.common.entry.comparison.ItemComparatorRegistry;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;

public class REIBackpacksPlugin {

    public static void registerItemComparators(ItemComparatorRegistry registry) {
        registry.registerNbt(
                ModItems.BACKPACK.get(),
                ModItems.COPPER_BACKPACK.get(),
                ModItems.IRON_BACKPACK.get(),
                ModItems.GOLD_BACKPACK.get(),
                ModItems.DIAMOND_BACKPACK.get(),
                ModItems.NETHERITE_BACKPACK.get()
        );
    }
}
