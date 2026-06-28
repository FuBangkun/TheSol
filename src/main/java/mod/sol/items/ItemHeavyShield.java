package mod.sol.items;

import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import mod.sol.TheSol;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemHeavyShield extends ItemShield implements IHasModel {

    public ItemHeavyShield(String name, CreativeTabs tab) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);
        setMaxDamage(1200);

        SolItems.ITEMS.add(this);
    }

//	@Nonnull
//	@Override
//	public String getItemStackDisplayName(ItemStack stack)
//	{
//		if (stack.getSubCompound("BlockEntityTag") != null)
//		{
//			EnumDyeColor enumdyecolor = TileEntityBanner.getColor(stack);
//			return I18n.format("item.shield." + enumdyecolor.getTranslationKey() + ".name");
//		}
//		else
//		{
//			return I18n.format("item.shield.name");
//		}
//	}

    @Nonnull
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.format(this.getItemStackDisplayName(stack) + ".name").trim();
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public void registerModels() {
        TheSol.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
