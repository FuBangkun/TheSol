package mod.sol.recipe;

import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import mod.sol.util.RecipeUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;

public class BaseRocketRecipeManager {

    /**
     * 通用火箭配方注册方法
     *
     * @param tier           当前火箭阶级 (如 4, 5)
     * @param noseCone       火箭鼻锥
     * @param plate          火箭外壳板
     * @param booster        助推器
     * @param fins           火箭尾翼
     * @param engine         火箭核心发动机
     * @param previousRocket 上一级火箭的基础 ItemStack（用于前置合成）
     * @param outputRocket   当前阶级火箭的基础 ItemStack（输出结果）
     */
    public static void registerRocketRecipes(int tier, ItemStack noseCone, ItemStack plate, ItemStack booster,
                                             ItemStack fins, ItemStack engine, ItemStack previousRocket, ItemStack outputRocket) {

        // 1. 组装基础配方模板（不含箱子和前置火箭）
        HashMap<Integer, ItemStack> baseInput = new HashMap<>();
        baseInput.put(1, noseCone);
        for (int i = 2; i <= 11; i++) {
            baseInput.put(i, plate);
        }
        baseInput.put(12, booster);
        baseInput.put(13, fins);
        baseInput.put(14, fins);
        baseInput.put(15, engine);
        baseInput.put(16, booster);
        baseInput.put(17, fins);
        baseInput.put(18, fins);

        // 初始化箱子槽位为 EMPTY
        baseInput.put(19, ItemStack.EMPTY);
        baseInput.put(20, ItemStack.EMPTY);
        baseInput.put(21, ItemStack.EMPTY);
        baseInput.put(22, ItemStack.EMPTY);

        NonNullList<ItemStack> woodChests = OreDictionary.getOres("chestWood");

        // 2. 槽位组合配置：定义每种箱子数量对应的插槽分布以及对应的火箭 metadata (0, 1, 2, 3 个箱子)
        // 这里的每一个数组代表：[箱子数量/火箭meta, 槽位1是否放箱子, 槽位2是否放箱子, 槽位3是否放箱子]
        int[][] chestLayouts = {
                {0, 0, 0, 0}, // 0个箱子 (Meta 0)
                {1, 1, 0, 0}, // 1个箱子 (Meta 1) - 槽位19
                {1, 0, 1, 0}, // 1个箱子 (Meta 1) - 槽位20
                {1, 0, 0, 1}, // 1个箱子 (Meta 1) - 槽位21
                {2, 1, 1, 0}, // 2个箱子 (Meta 2) - 槽位19, 20
                {2, 1, 0, 1}, // 2个箱子 (Meta 2) - 槽位19, 21
                {2, 0, 1, 1}, // 2个箱子 (Meta 2) - 槽位20, 21
                {3, 1, 1, 1}  // 3个箱子 (Meta 3) - 槽位19, 20, 21
        };

        // 3. 嵌套循环注册所有变种
        for (ItemStack woodChest : woodChests) {
            for (int[] layout : chestLayouts) {
                int rocketMeta = layout[0]; // 箱子数量对应的火箭元数据
                boolean slot19 = layout[1] == 1;
                boolean slot20 = layout[2] == 1;
                boolean slot21 = layout[3] == 1;

                // 遍历上一级火箭的 4 种不同箱子容量 (i 即旧配方里的前置火箭 meta 0~3)
                for (int i = 0; i < 4; ++i) {
                    HashMap<Integer, ItemStack> recipeInput = new HashMap<>(baseInput);

                    // 放置箱子
                    recipeInput.put(19, slot19 ? woodChest : ItemStack.EMPTY);
                    recipeInput.put(20, slot20 ? woodChest : ItemStack.EMPTY);
                    recipeInput.put(21, slot21 ? woodChest : ItemStack.EMPTY);

                    // 放置前置火箭 (槽位 22)
                    ItemStack prevRocketCopy = previousRocket.copy();
                    prevRocketCopy.setItemDamage(i);
                    recipeInput.put(22, prevRocketCopy);

                    // 设定当前输出火箭的箱子 Meta
                    ItemStack outputCopy = outputRocket.copy();
                    outputCopy.setItemDamage(rocketMeta);

                    // 注册配方
                    RecipeUtil.addRocketRecipe(tier, new NasaWorkbenchRecipe(outputCopy, recipeInput));
                }
            }
        }
    }
}