package mod.sol.client.jei;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.client.jei.tier1rocket.Tier1RocketRecipeMaker;
import mod.sol.util.RecipeUtil;

import java.util.ArrayList;
import java.util.List;

public class RocketRecipeMaker {
    public static List<INasaWorkbenchRecipe> getRecipesList(int tier) {
        List<INasaWorkbenchRecipe> recipes = new ArrayList<>();

        int chestCount = -1;
        for (INasaWorkbenchRecipe recipe : RecipeUtil.getRocketRecipes(tier)) {
            int chests = Tier1RocketRecipeMaker.countChests(recipe);
            if (chests == chestCount)
                continue;
            chestCount = chests;
            recipes.add(recipe);
        }

        return recipes;
    }
}