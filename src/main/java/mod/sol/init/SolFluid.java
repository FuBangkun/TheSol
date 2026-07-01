package mod.sol.init;

import mod.sol.Tags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.awt.*;

public class SolFluid {
    public static final Fluid METHANE;

    static {
        METHANE = registerFluid(new Fluid("liquid_methane", new ResourceLocation(Tags.MOD_ID, "blocks/fluid/liquid_methane_still"), new ResourceLocation(Tags.MOD_ID, "blocks/fluid/liquid_methane_flow")).setColor(new Color(39, 120, 142)));
    }

    private static Fluid registerFluid(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        return fluid;
    }
}
