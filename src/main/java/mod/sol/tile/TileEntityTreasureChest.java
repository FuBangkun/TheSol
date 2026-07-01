package mod.sol.tile;

public class TileEntityTreasureChest extends micdoodle8.mods.galacticraft.core.tile.TileEntityTreasureChest {
    public TileEntityTreasureChest() {
        super(4);
    }

    public TileEntityTreasureChest(int tier) {
        super(tier);
    }

    public int getTier() {
        return this.tier;
    }
}