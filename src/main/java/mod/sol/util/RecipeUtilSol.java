package mod.sol.util;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import mod.sol.inventory.InventorySchematicRocket;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeUtilSol {
    private static final Map<Integer, List<INasaWorkbenchRecipe>> rocketBenchRecipes = new HashMap<>();

    static {
        for (int tier = 4; tier <= 10; tier++) {
            rocketBenchRecipes.put(tier, new ArrayList<>());
        }
    }

    public static void addRocketRecipe(int tier, INasaWorkbenchRecipe recipe) {
        rocketBenchRecipes.get(tier).add(recipe);
    }

    public static void removeRocketRecipe(int tier, INasaWorkbenchRecipe recipe) {
        rocketBenchRecipes.get(tier).remove(recipe);
    }

    public static void removeAllRocketRecipes(int tier) {
        rocketBenchRecipes.get(tier).clear();
    }

    public static List<INasaWorkbenchRecipe> getRocketRecipes(int tier) {
        return rocketBenchRecipes.get(tier);
    }

    @Nonnull
    public static ItemStack findMatchingSpaceshipRecipe(IInventory inventoryRocketBench, int tier) {
        for (INasaWorkbenchRecipe recipe : getRocketRecipes(tier)) {
            if (recipe.matches(inventoryRocketBench)) {
                return recipe.getRecipeOutput();
            }
        }
        return ItemStack.EMPTY;
    }
}
