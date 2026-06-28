package mod.sol.items;

import micdoodle8.mods.galacticraft.api.entity.IRocketType.EnumRocketType;
import micdoodle8.mods.galacticraft.api.item.IHoldableItem;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCFluids;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.tile.TileEntityLandingPad;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import mod.sol.TheSol;
import mod.sol.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.prefab.entity.EntitySpaceshipBase;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRocketBase extends ItemMetadataBase implements IHoldableItem, ISortableItem {

    protected final int tier;
    protected final RocketEntityFactory entityFactory;

    // 函数式接口：用于动态创建不同等级的火箭实体
    @FunctionalInterface
    public interface RocketEntityFactory {
        EntitySpaceshipBase create(World world, double x, double y, double z, EnumRocketType type);
    }

    /**
     * 通用火箭物品构造器
     * @param tier 火箭等级 (如 4)
     * @param entityFactory 创建对应实体的 Lambda 表达式
     */
    public ItemRocketBase(int tier, RocketEntityFactory entityFactory) {
        super("rocket_t" + tier, "type0", "type1", "type2", "type3", "type4");
        this.setMaxStackSize(1);
        this.tier = tier;
        this.entityFactory = entityFactory;
    }

    public boolean placeRocketOnPad(ItemStack stack, World worldIn, TileEntity tile, float centerX, float centerY, float centerZ) {
        if (tile instanceof TileEntityLandingPad) {
            if (((TileEntityLandingPad) tile).getDockedEntity() != null) {
                return false;
            }
        } else {
            return false;
        }

        // 🛠️ 核心通用修改：使用传入的 factory 创建对应等级的火箭
        EntitySpaceshipBase rocket = entityFactory.create(worldIn, centerX, centerY, centerZ, EnumRocketType.values()[stack.getItemDamage()]);

        rocket.rotationYaw += 45;
        // 1.12.2 的一些星系基础类里可能需要使用特定方法或者直接通过位置加偏移
        rocket.setPosition(rocket.posX, rocket.posY + ((IHasModel)this == rocket ? 0 : 0.4F), rocket.posZ);

        // 如果你的火箭实体有特定的 getOnPadYOffset 或者是通用的，可以通过反射或硬编码。星系绝大部分扩展火箭默认偏移 0.4F

        worldIn.spawnEntity(rocket);

        try {
            // 1. 动态获取实体里的 getType() 方法并拿到 EnumRocketType
            java.lang.reflect.Method getTypeMethod = rocket.getClass().getMethod("getType");
            EnumRocketType rocketType = (EnumRocketType) getTypeMethod.invoke(rocket);

            // 2. 动态获取实体里的 fuelTank 字段
            java.lang.reflect.Field fuelTankField = rocket.getClass().getField("fuelTank");
            Object fuelTank = fuelTankField.get(rocket);
            java.lang.reflect.Method fillMethod = fuelTank.getClass().getMethod("fill", FluidStack.class, boolean.class);

            if (rocketType.getPreFueled()) {
                java.lang.reflect.Method getMaxFuelMethod = rocket.getClass().getMethod("getMaxFuel");
                int maxFuel = (int) getMaxFuelMethod.invoke(rocket);
                fillMethod.invoke(fuelTank, new FluidStack(GCFluids.fluidFuel, maxFuel), true);
            } else if (stack.hasTagCompound() && stack.getTagCompound().hasKey("RocketFuel")) {
                int fuelAmount = stack.getTagCompound().getInteger("RocketFuel");
                fillMethod.invoke(fuelTank, new FluidStack(GCFluids.fluidFuel, fuelAmount), true);
            }
        } catch (Exception e) {
            e.printStackTrace(); // 如果反射失败会在后台报错，方便调试
        }

        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return ClientProxyCore.galacticraftItem;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public CreativeTabs getCreativeTab() {
        return TheSol.ITEM_TAB;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        boolean padFound = false;
        TileEntity tile = null;
        ItemStack stack = playerIn.getHeldItem(hand);

        if (worldIn.isRemote) {
            return EnumActionResult.PASS;
        } else {
            float centerX = -1;
            float centerY = -1;
            float centerZ = -1;

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    BlockPos pos1 = pos.add(i, 0, j);
                    IBlockState state = worldIn.getBlockState(pos1);
                    final Block id = state.getBlock();
                    int meta = id.getMetaFromState(state);

                    if (id == GCBlocks.landingPadFull && meta == 0) {
                        padFound = true;
                        tile = worldIn.getTileEntity(pos1);

                        centerX = pos.getX() + i + 0.5F;
                        centerY = pos.getY() + 0.4F;
                        centerZ = pos.getZ() + j + 0.5F;

                        break;
                    }
                }
                if (padFound) break;
            }

            if (padFound) {
                if (!placeRocketOnPad(stack, worldIn, tile, centerX, centerY, centerZ)) {
                    return EnumActionResult.FAIL;
                }
                if (!playerIn.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }
                return EnumActionResult.SUCCESS;
            } else {
                return EnumActionResult.PASS;
            }
        }
    }

    @Override
    protected boolean useSubNamesInTranslation() {
        return false; // 火箭不需要拼接后缀名字
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        EnumRocketType type;
        if (par1ItemStack.getItemDamage() < 10) {
            type = EnumRocketType.values()[par1ItemStack.getItemDamage()];
        } else {
            type = EnumRocketType.values()[par1ItemStack.getItemDamage() - 10];
        }

        if (!type.getTooltip().isEmpty()) {
            tooltip.add(type.getTooltip());
        }

        if (type.getPreFueled()) {
            tooltip.add(EnumColor.RED + "§o" + GCCoreUtil.translate("gui.creative_only.desc"));
        }

        if (par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("RocketFuel")) {
            EntitySpaceshipBase rocket = entityFactory.create(FMLClientHandler.instance().getWorldClient(), 0, 0, 0, EnumRocketType.values()[par1ItemStack.getItemDamage()]);
            try {
                java.lang.reflect.Field fuelTankField = rocket.getClass().getField("fuelTank");
                Object fuelTank = fuelTankField.get(rocket);
                java.lang.reflect.Method getCapacityMethod = fuelTank.getClass().getMethod("getCapacity");
                int capacity = (int) getCapacityMethod.invoke(fuelTank);
                tooltip.add(GCCoreUtil.translate("gui.message.fuel.name") + ": " + par1ItemStack.getTagCompound().getInteger("RocketFuel") + " / " + capacity);
            } catch (Exception ignored) {}
        }
    }

    @Override
    public boolean shouldHoldLeftHandUp(EntityPlayer player) { return true; }

    @Override
    public boolean shouldHoldRightHandUp(EntityPlayer player) { return true; }

    @Override
    public boolean shouldCrouch(EntityPlayer player) { return true; }

    @Override
    public EnumSortCategoryItem getCategory(int meta) {
        return EnumSortCategoryItem.ROCKET;
    }

    @Override
    public void registerModels() {
        // 利用我们修复过后的独立多 Meta 模型注册规范方式
        for (int i = 0; i < 5; ++i) {
            net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation(
                    this,
                    i,
                    new net.minecraft.client.renderer.block.model.ModelResourceLocation(
                            new ResourceLocation(mod.sol.util.Reference.MOD_ID, this.getRegistryName().getPath()),
                            "inventory"
                    )
            );
        }
    }
}