package mod.sol.proxy;

import mod.sol.Tags;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class SolClientProxy extends SolCommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation("inventory".equals(id) ? item.getRegistryName() : new ResourceLocation(Tags.MOD_ID, id), "inventory"));
    }
}
