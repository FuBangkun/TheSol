package mod.sol.items;

import micdoodle8.mods.galacticraft.api.entity.IRocketType.EnumRocketType;
import micdoodle8.mods.galacticraft.api.item.IHoldableItem;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCFluids;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.tile.TileEntityLandingPad;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.TheSol;
import mod.sol.entities.EntityTierRocket;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRocket extends ItemMetadataBase implements IHoldableItem, ISortableItem {

    protected final int tier;
    protected final RocketEntityFactory entityFactory;

    @FunctionalInterface
    public interface RocketEntityFactory {
        EntityTierRocket create(World world, double x, double y, double z, int tier, EnumRocketType type);
    }

    public ItemRocket(int tier, RocketEntityFactory entityFactory) {
        super("rocket_t" + tier, "type0", "type1", "type2", "type3", "type4");
        this.setMaxStackSize(1);
        this.tier = tier;
        this.entityFactory = entityFactory;
    }

    // =========================
    // 🚀 放置火箭
    // =========================
    public boolean placeRocketOnPad(ItemStack stack, World worldIn, TileEntity tile, float x, float y, float z) {
        if (!(tile instanceof TileEntityLandingPad)) {
            return false;
        }

        TileEntityLandingPad pad = (TileEntityLandingPad) tile;
        if (pad.getDockedEntity() != null) {
            return false;
        }

        EnumRocketType type = EnumRocketType.values()[stack.getItemDamage()];

        EntityTierRocket tierRocket = entityFactory.create(worldIn, x, y, z, this.tier, type);

        tierRocket.rotationYaw += 45F;
        tierRocket.setPosition(tierRocket.posX, tierRocket.posY + 0.4F, tierRocket.posZ);

        worldIn.spawnEntity(tierRocket);

        if (type.getPreFueled()) {
            tierRocket.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, tierRocket.getFuelTankCapacity()), true);
        } else if (stack.hasTagCompound() && stack.getTagCompound().hasKey("RocketFuel")) {
            int fuel = stack.getTagCompound().getInteger("RocketFuel");
            tierRocket.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, fuel), true);
        }

        return true;
    }

    // =========================
    // 🚀 使用物品
    // =========================
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) return EnumActionResult.PASS;

        ItemStack stack = player.getHeldItem(hand);

        TileEntityLandingPad pad = findLandingPad(world, pos);
        if (pad == null) return EnumActionResult.PASS;

        BlockPos padPos = pad.getPos();

        float cx = padPos.getX() + 0.5F;
        float cy = padPos.getY() + 0.4F;
        float cz = padPos.getZ() + 0.5F;

        if (!placeRocketOnPad(stack, world, pad, cx, cy, cz)) {
            return EnumActionResult.FAIL;
        }

        if (!player.capabilities.isCreativeMode) {
            stack.shrink(1);
        }

        return EnumActionResult.SUCCESS;
    }

    // =========================
    // 🚀 查找发射台
    // =========================
    private TileEntityLandingPad findLandingPad(World world, BlockPos pos) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                BlockPos p = pos.add(i, 0, j);
                IBlockState state = world.getBlockState(p);

                Block block = state.getBlock();
                if (block == GCBlocks.landingPadFull && block.getMetaFromState(state) == 0) {
                    TileEntity tile = world.getTileEntity(p);
                    if (tile instanceof TileEntityLandingPad) {
                        return (TileEntityLandingPad) tile;
                    }
                }
            }
        }
        return null;
    }

    // =========================
    // 🚀 tooltip
    // =========================
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        EnumRocketType type = EnumRocketType.values()[stack.getItemDamage()];

        if (!type.getTooltip().isEmpty()) {
            tooltip.add(type.getTooltip());
        }

        if (type.getPreFueled()) {
            tooltip.add(EnumColor.RED + "§o" + I18n.format("gui.creative_only.desc"));
        }

        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("RocketFuel")) {
            int fuel = stack.getTagCompound().getInteger("RocketFuel");
            int cap = tier >= 4 ? 2000 + (tier - 4) * 500 : 2000;
            tooltip.add(I18n.format("gui.message.fuel.name") + ": " + fuel + " / " + cap);
        }
    }

    // =========================
    // 其他保持不变
    // =========================
    @Override public boolean shouldHoldLeftHandUp(EntityPlayer p) { return true; }
    @Override public boolean shouldHoldRightHandUp(EntityPlayer p) { return true; }
    @Override public boolean shouldCrouch(EntityPlayer p) { return true; }

    @Override
    public EnumSortCategoryItem getCategory(int meta) {
        return EnumSortCategoryItem.ROCKET;
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return TheSol.ITEM_TAB;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore.galacticraftItem;
    }

    @Override
    protected boolean useSubNamesInTranslation() {
        return false;
    }

    @Override
    public void registerModels() {
        for (int i = 0; i < 5; i++) {
            net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation(
                    this,
                    i,
                    new net.minecraft.client.renderer.block.model.ModelResourceLocation(
                            new ResourceLocation(mod.sol.Tags.MOD_ID, getRegistryName().getPath()),
                            "inventory"
                    )
            );
        }
    }
}