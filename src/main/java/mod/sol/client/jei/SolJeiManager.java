package mod.sol.client.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@JEIPlugin
public class SolJeiManager extends BlankModPlugin {
    private static final Map<Integer, String> ROCKET_TIERS = new HashMap<>();

    static {
        for (int i = 4; i <= 9; i++) {
            ROCKET_TIERS.put(i, "sol.rocketT" + i);
        }
    }

    @Override
    public void register(@Nonnull IModRegistry registry) {
        ItemStack nasaWorkbench = new ItemStack(GCBlocks.nasaWorkbench);

        ROCKET_TIERS.forEach((tier, uid) -> {
            registry.handleRecipes(INasaWorkbenchRecipe.class, RocketRecipeWrapper::new, uid);
            registry.addRecipes(RocketRecipeMaker.getRecipesList(tier), uid);
            registry.addRecipeCatalyst(nasaWorkbench, uid);
        });
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();

        ROCKET_TIERS.forEach((tier, uid) -> registry.addRecipeCategories(new RocketRecipeCategory(guiHelper, uid)));
    }
}