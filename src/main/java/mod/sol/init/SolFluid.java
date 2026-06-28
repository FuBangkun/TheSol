package mod.sol.init;

import mod.sol.fluid.FluidBase;
import mod.sol.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.awt.*;

public class SolFluid {
    public static final Fluid METHANE = new FluidBase("liquid_methane", new ResourceLocation(Reference.MOD_ID, "blocks/fluid/liquid_methane_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluid/liquid_methane_flow")).setColor(new Color(39, 120, 142));

    public static void registerFluids() {
        registerFluid(SolFluid.METHANE);
    }

    private static void registerFluid(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }
}
