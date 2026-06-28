package mod.sol.items;

import mod.sol.TheSol;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemMetadataBase extends Item implements IHasModel {

    protected final int maxMeta;
    protected final String[] subNames;

    /**
     * 通用 Metadata 物品构造器
     * @param registryName 物品的注册名 (如 "material_ingot")
     * @param subNames 每个 meta 对应的子名称/后缀 (数组长度决定了生成多少个子物品)
     */
    public ItemMetadataBase(String registryName, String... subNames) {
        super();
        this.subNames = subNames;
        this.maxMeta = subNames.length - 1;

        this.setHasSubtypes(true); // 启用元数据支持
        this.setMaxDamage(0);      // 禁用耐久度
        this.setRegistryName(registryName);
        this.setTranslationKey(registryName);

        // 假设你的通用注册列表在 SolItems
        SolItems.ITEMS.add(this);
    }

    /**
     * 重写此方法以根据 meta 动态返回不同的未本地化名称
     * 最终语言文件里写：item.registry_name.sub_name.name
     */
    @Override
    public String getTranslationKey(ItemStack stack) {
        if (useSubNamesInTranslation()) {
            int meta = stack.getMetadata();
            if (meta >= 0 && meta < subNames.length) {
                return super.getTranslationKey() + "." + subNames[meta];
            }
        }
        return super.getTranslationKey();
    }

    protected boolean useSubNamesInTranslation() {
        return true;
    }

    /**
     * 将所有的子物品（不同 Meta 的 ItemStack）注入到创造模式物品栏
     */
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (int meta = 0; meta <= maxMeta; meta++) {
                items.add(new ItemStack(this, 1, meta));
            }
        }
    }

    /**
     * 自动迭代注册每个 meta 对应的客户端模型映射
     */
    @Override
    public void registerModels() {
        for (int meta = 0; meta <= maxMeta; meta++) {
            // 模型文件名为：物品注册名_子名称 (例如: material_ingot_copper)
            String modelName = this.getRegistryName().getPath() + "_" + subNames[meta];
            TheSol.proxy.registerItemRenderer(this, meta, modelName);
        }
    }
}