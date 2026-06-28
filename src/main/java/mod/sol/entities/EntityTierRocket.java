package mod.sol.entities;

import mod.sol.init.SolItems;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityTierRocket extends EntityRocketBase {
    private static final DataParameter<Integer> TIER = EntityDataManager.createKey(EntityTierRocket.class, DataSerializers.VARINT);

    private int tier;

    public EntityTierRocket(World world) {
        super(world);
    }

    public EntityTierRocket(World world, double x, double y, double z, int tier, EnumRocketType type) {
        super(world, x, y, z, type);
        this.tier = tier;
        this.dataManager.set(TIER, tier);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(TIER, 0);
    }

    @Override
    public Item getRocketItem() {
        int t = getRocketTier();
        switch (t) {
            case 4: return SolItems.ROCKET_T4;
            case 5: return SolItems.ROCKET_T5;
            case 6: return SolItems.ROCKET_T6;
            case 7: return SolItems.ROCKET_T7;
            case 8: return SolItems.ROCKET_T8;
            case 9: return SolItems.ROCKET_T9;
            default: return SolItems.ROCKET_T4;
        }
    }

    @Override
    public int getRocketTier() {
        if (this.world != null && this.world.isRemote) {
            return this.dataManager.get(TIER);
        }
        return tier;
    }

    @Override
    public int getFuelTankCapacity() {
        return 2000 + Math.max(0, getRocketTier() - 4) * 500;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        tier = nbt.getInteger("RocketTier");
        if (this.world != null && !this.world.isRemote) {
            this.dataManager.set(TIER, tier);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("RocketTier", tier);
    }
}
