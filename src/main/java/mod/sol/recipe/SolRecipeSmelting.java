package mod.sol.recipe;

import mod.sol.init.SolItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SolRecipeSmelting {
    public static void registryRecipe() {
        GameRegistry.addSmelting(SolItems.SULFUR_SHARD, new ItemStack(SolItems.SULFUR_INGOT, 1, 0), 1F);
    }
}
