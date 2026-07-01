package mod.sol.entities;

import micdoodle8.mods.galacticraft.api.prefab.entity.EntityTieredRocket;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import mod.sol.init.SolItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class EntityTierRocket extends EntityTieredRocket {
    private static final DataParameter<Integer> TIER = EntityDataManager.createKey(EntityTierRocket.class, DataSerializers.VARINT);
    private int tier;

    public EntityTierRocket(World par1World) {
        super(par1World);
        this.setSize(1.8F, 6F);
    }

    public EntityTierRocket(World par1World, double par2, double par4, double par6, int tier, EnumRocketType rocketType) {
        super(par1World, par2, par4, par6);
        this.rocketType = rocketType;
        this.stacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        this.tier = tier;
        this.dataManager.set(TIER, tier);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(TIER, 0);
    }

    public Item getRocketItem() {
        int t = getRocketTier();
        switch (t) {
            case 5:
                return SolItems.ROCKET_T5;
            case 6:
                return SolItems.ROCKET_T6;
            case 7:
                return SolItems.ROCKET_T7;
            case 8:
                return SolItems.ROCKET_T8;
            case 9:
                return SolItems.ROCKET_T9;
            case 4:
            default:
                return SolItems.ROCKET_T4;
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
    public double getYOffset() {
        return 1.5F;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(getRocketItem(), 1, this.rocketType.getIndex());
    }

    @Override
    public double getMountedYOffset() {
        return 1.75D;
    }

    @Override
    public float getRotateOffset() {
        return 1.5F;
    }

    @Override
    public double getOnPadYOffset() {
        return 0.0D;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        int i = this.timeUntilLaunch >= 100 ? Math.abs(this.timeUntilLaunch / 100) : 1;

        if ((this.getLaunched() || this.launchPhase == EnumLaunchPhase.IGNITED.ordinal() && this.rand.nextInt(i) == 0) && !ConfigManagerCore.disableSpaceshipParticles && this.hasValidFuel()) {
            if (this.world.isRemote) {
                this.spawnParticles(this.getLaunched());
            }
        }

        if (this.launchPhase >= EnumLaunchPhase.LAUNCHED.ordinal() && this.hasValidFuel()) {
            if (this.launchPhase == EnumLaunchPhase.LAUNCHED.ordinal()) {
                double d = this.timeSinceLaunch / 150;
                if (this.world.provider instanceof IGalacticraftWorldProvider && ((IGalacticraftWorldProvider) this.world.provider).hasNoAtmosphere()) {
                    d = Math.min(d * 1.2, 2);
                } else {
                    d = Math.min(d, 1.4);
                }
                if (d != 0.0) {
                    this.motionY = -d * 2.5D * Math.cos((this.rotationPitch - 180) / Constants.RADIANS_TO_DEGREES);
                }
            } else {
                this.motionY -= 0.008D;
            }

            double multiplier = 1.0D;
            if (this.world.provider instanceof IGalacticraftWorldProvider) {
                multiplier = ((IGalacticraftWorldProvider) this.world.provider).getFuelUsageMultiplier();
                if (multiplier <= 0) multiplier = 1;
            }

            if (this.timeSinceLaunch % MathHelper.floor(2 * (1 / multiplier)) == 0) {
                this.removeFuel(1);
                if (!this.hasValidFuel()) this.stopRocketSound();
            }
        } else if (!this.hasValidFuel() && this.getLaunched() && !this.world.isRemote) {
            if (Math.abs(Math.sin(this.timeSinceLaunch / 1000)) / 10 != 0.0) {
                this.motionY -= Math.abs(Math.sin(this.timeSinceLaunch / 1000)) / 20;
            }
        }
    }

    @Override
    public void onTeleport(EntityPlayerMP player) {
        EntityPlayerMP playerBase = PlayerUtil.getPlayerBaseServerFromPlayer(player, false);
        if (playerBase != null) {
            GCPlayerStats stats = GCPlayerStats.get(playerBase);
            if (this.stacks == null || this.stacks.isEmpty()) {
                stats.setRocketStacks(NonNullList.withSize(2, ItemStack.EMPTY));
            } else {
                stats.setRocketStacks(this.stacks);
            }
            stats.setRocketType(this.rocketType.getIndex());
            stats.setRocketItem(getRocketItem());
            stats.setFuelLevel(this.fuelTank.getFluidAmount());
        }
    }

    @Override
    public int getFuelTankCapacity() {
        return 2000 + Math.max(0, getRocketTier() - 4) * 500;
    }

    @Override
    public int getPreLaunchWait() {
        return 400;
    }

    @Override
    public float getCameraZoom() {
        return 15.0F;
    }

    @Override
    public boolean defaultThirdPerson() {
        return true;
    }

    @Override
    public List<ItemStack> getItemsDropped(List<ItemStack> droppedItems) {
        super.getItemsDropped(droppedItems);
        ItemStack rocket = new ItemStack(getRocketItem(), 1, this.rocketType.getIndex());
        rocket.setTagCompound(new NBTTagCompound());
        rocket.getTagCompound().setInteger("RocketFuel", this.fuelTank.getFluidAmount());
        droppedItems.add(rocket);
        return droppedItems;
    }

    @Override
    public float getRenderOffsetY() {
        return -1F;
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

    protected void spawnParticles(boolean launched) {
        if (!this.isDead) {
            double sinPitch = Math.sin(this.rotationPitch / Constants.RADIANS_TO_DEGREES_D);
            double x1 = 3.2 * Math.cos(this.rotationYaw / Constants.RADIANS_TO_DEGREES_D) * sinPitch;
            double z1 = 3.2 * Math.sin(this.rotationYaw / Constants.RADIANS_TO_DEGREES_D) * sinPitch;
            double y1 = 3.2 * Math.cos((this.rotationPitch - 180) / Constants.RADIANS_TO_DEGREES_D);
            if (this.launchPhase == EnumLaunchPhase.LANDING.ordinal() && this.targetVec != null) {
                double modifier = this.posY - this.targetVec.getY();
                modifier = Math.max(modifier, 180.0);
                x1 *= modifier / 200.0D;
                y1 *= Math.min(modifier / 200.0D, 2.5D);
                z1 *= modifier / 200.0D;
            }
            final double y2 = this.prevPosY + (this.posY - this.prevPosY) + y1 - 0.75 * this.motionY - 0.3 + 1.2D;
            final double x2 = this.posX + x1 + this.motionX;
            final double z2 = this.posZ + z1 + this.motionZ;
            Vector3 motionVec = new Vector3(x1 + this.motionX, y1 + this.motionY, z1 + this.motionZ);
            Vector3 d1 = new Vector3(y1 * 0.1D, -x1 * 0.1D, z1 * 0.1D).rotate(315 - this.rotationYaw, motionVec);
            Vector3 d2 = new Vector3(x1 * 0.1D, -z1 * 0.1D, y1 * 0.1D).rotate(315 - this.rotationYaw, motionVec);
            Vector3 d3 = new Vector3(-y1 * 0.1D, x1 * 0.1D, z1 * 0.1D).rotate(315 - this.rotationYaw, motionVec);
            Vector3 d4 = new Vector3(x1 * 0.1D, z1 * 0.1D, -y1 * 0.1D).rotate(315 - this.rotationYaw, motionVec);
            EntityLivingBase riddenByEntity = this.getPassengers().isEmpty() || !(this.getPassengers().get(0) instanceof EntityLivingBase) ? null : (EntityLivingBase) this.getPassengers().get(0);
            Object[] rider = new Object[]{riddenByEntity};
            makeFlame(x2 + d1.x, y2 + d1.y, z2 + d1.z, motionVec.clone().translate(d1), this.getLaunched(), rider);
            makeFlame(x2 + d2.x, y2 + d2.y, z2 + d2.z, motionVec.clone().translate(d2), this.getLaunched(), rider);
            makeFlame(x2 + d3.x, y2 + d3.y, z2 + d3.z, motionVec.clone().translate(d3), this.getLaunched(), rider);
            makeFlame(x2 + d4.x, y2 + d4.y, z2 + d4.z, motionVec.clone().translate(d4), this.getLaunched(), rider);
        }
    }

    private void makeFlame(double x2, double y2, double z2, Vector3 motionVec, boolean getLaunched, Object[] rider) {
        if (getLaunched) {
            for (int i = 0; i < 2; i++) {
                GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 + 0.4 - this.rand.nextDouble() / 10D, y2, z2 + 0.4 - this.rand.nextDouble() / 10D), motionVec, rider);
                GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 - 0.4 + this.rand.nextDouble() / 10D, y2, z2 - 0.4 + this.rand.nextDouble() / 10D), motionVec, rider);
            }
            return;
        }
        if (this.ticksExisted % 2 == 0) return;
        y2 += 1.6D;
        GalacticraftCore.proxy.spawnParticle("launchFlameIdle", new Vector3(x2 + 0.4, y2, z2), new Vector3(motionVec.x + 0.3D, motionVec.y - 0.3D, motionVec.z), rider);
    }
}