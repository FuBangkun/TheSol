package mod.sol.planets.kuiper_belt.world.gen;

import net.minecraft.block.Block;

import java.util.ArrayList;

public class SpecialKuiperBeltBlock {
    public static ArrayList<SpecialKuiperBeltBlock> register = new ArrayList();
    public Block block;
    public byte meta;
    public int probability;
    public double thickness;
    public int index;

    public SpecialKuiperBeltBlock(Block block, byte meta, int probability, double thickness) {
        this.block = block;
        this.meta = meta;
        this.probability = probability;
        this.thickness = thickness;
        this.index = register.size();
        register.add(this);
    }
}
