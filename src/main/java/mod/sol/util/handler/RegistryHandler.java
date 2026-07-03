package mod.sol.util.handler;

import mod.sol.Tags;
import mod.sol.blocks.BlockBaseOre;
import mod.sol.blocks.BlockFluidBase;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import mod.sol.items.ItemMetadataBase;
import mod.sol.items.ItemRocket;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

@EventBusSubscriber(modid = Tags.MOD_ID)
public class RegistryHandler {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(SolItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(SolBlocks.Blocks.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onBlockMeshRegister(RegistryEvent.Register<Block> event) {
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(SolBlocks.METHANE_FLUID_BLOCK), stack -> new ModelResourceLocation("sol:liquid_methane", "fluid"));

        ModelLoader.setCustomStateMapper(SolBlocks.METHANE_FLUID_BLOCK, new StateMapperBase() {
            @Nonnull
            @Override
            protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
                return new ModelResourceLocation("sol:liquid_methane", "fluid");
            }
        });
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : SolItems.ITEMS) {
            if (item instanceof ItemBlock) continue;

            ResourceLocation loc = item.getRegistryName();
            if (loc == null) continue;

            if (item instanceof ItemRocket) {
                ItemRocket rocket = (ItemRocket) item;
                for (int i = 0; i <= rocket.getMaxMeta(); i++) {
                    ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(loc, "inventory"));
                }
            } else if (item instanceof ItemMetadataBase) {
                ItemMetadataBase metaItem = (ItemMetadataBase) item;
                for (int i = 0; i <= metaItem.getMaxMeta(); i++) {
                    String modelPath = loc.getPath() + "_" + metaItem.getModelSuffix(i);
                    ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(new ResourceLocation(Tags.MOD_ID, modelPath), "inventory"));
                }
            } else {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(loc, "inventory"));
            }
        }

        for (Block block : SolBlocks.Blocks) {
            if (block instanceof BlockFluidBase) continue;

            Item item = Item.getItemFromBlock(block);
            if (item == net.minecraft.init.Items.AIR) continue;

            ResourceLocation blockLoc = block.getRegistryName();
            if (blockLoc == null) continue;

            if (block instanceof BlockBaseOre) {
                BlockBaseOre ore = (BlockBaseOre) block;
                for (int i = 0; i < ore.getVariantCount(); i++) {
                    ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(blockLoc, "type=" + i));
                }
            } else {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(blockLoc, "inventory"));
            }
        }

        Item fluidItem = Item.getItemFromBlock(SolBlocks.METHANE_FLUID_BLOCK);
        if (fluidItem != net.minecraft.init.Items.AIR) {
            ModelLoader.setCustomMeshDefinition(fluidItem, stack -> new ModelResourceLocation("sol:liquid_methane", "inventory")
            );

            ModelBakery.registerItemVariants(fluidItem, new ModelResourceLocation("sol:liquid_methane", "inventory"));
        }

        ModelLoader.setCustomStateMapper(SolBlocks.METHANE_FLUID_BLOCK, new StateMapperBase() {
            @Nonnull
            @Override
            protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
                return new ModelResourceLocation("sol:liquid_methane", "fluid");
            }
        });
    }
}
