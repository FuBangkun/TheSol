package mod.sol.util.handler;

import mod.sol.Tags;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
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
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        }

        for (Block block : SolBlocks.Blocks) {
            if (block instanceof IHasModel) {
                ((IHasModel) block).registerModels();
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
