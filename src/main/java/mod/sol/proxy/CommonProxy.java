package mod.sol.proxy;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import mod.sol.Tags;
import mod.sol.TheSol;
import mod.sol.config.ConfigManager;
import mod.sol.init.SolBiomes;
import mod.sol.init.SolDimensions;
import mod.sol.init.SolPlanets;
import mod.sol.init.SolRegistries;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        SolBiomes.init();
        SolPlanets.registerCelestialBodies();
    }

    public void init(FMLInitializationEvent event) {
        SolRegistries.register();
    }

    public void postInit(FMLPostInitializationEvent event) {
        SolDimensions.Mercury = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidMercury);
        SolDimensions.Io = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidIo);
        SolDimensions.Europa = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidEuropa);
        SolDimensions.Mimas = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidMimas);
        SolDimensions.Titan = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidTitan);
        SolDimensions.Ariel = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidAriel);
        SolDimensions.Triton = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidTriton);
        SolDimensions.Pluto = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidPluto);
        SolDimensions.Sedna = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidSedna);
    }

    public void registerNonMobEntity(Class<? extends Entity> var0, String var1, int trackingDistance, int updateFreq, boolean sendVel) {
        ResourceLocation registryName = new ResourceLocation(Tags.MOD_ID, var1);
        EntityRegistry.registerModEntity(registryName, var0, var1, GCCoreUtil.nextInternalID(), TheSol.instance, trackingDistance, updateFreq, sendVel);
    }

    public void registerEntityCreature(Class<? extends Entity> clazz, String name, int back, int fore) {
        this.registerNonMobEntity(clazz, name, 80, 3, true);
        int nextEggID = GCCoreUtil.getNextValidID();
        if (nextEggID < 65536) {
            ResourceLocation resourcelocation = new ResourceLocation(Tags.MOD_ID, name);
            EntityList.ENTITY_EGGS.put(resourcelocation, new EntityList.EntityEggInfo(resourcelocation, back, fore));
        }
    }
}