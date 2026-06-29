package mod.sol.items;

public class ItemRocketPart extends ItemMetadataBase {
    private final boolean t4;

    public ItemRocketPart(String registryName, boolean t4) {
        super(registryName, t4 ? new String[]{"t4", "t5", "t6", "t7", "t8", "t9"} : new String[]{"t5", "t6", "t7", "t8", "t9"});
        this.t4 = t4;
    }

    @Override
    protected String getModelSuffix(int meta) {
        int tier = meta + (t4 ? 4 : 5);
        return "t" + tier;
    }
}