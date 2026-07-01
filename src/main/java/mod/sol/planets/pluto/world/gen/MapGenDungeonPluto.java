package mod.sol.planets.pluto.world.gen;

import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonConfiguration;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.world.gen.structure.MapGenStructureIO;

public class MapGenDungeonPluto extends MapGenDungeon {
    private static boolean initialized;

    static {
        try {
            MapGenDungeonPluto.initiateStructures();
        } catch (Throwable ignored) {

        }
    }

    public MapGenDungeonPluto(DungeonConfiguration configuration) {
        super(configuration);
    }

    public static void initiateStructures() {
        if (!MapGenDungeonPluto.initialized) {
            MapGenStructureIO.registerStructureComponent(RoomTreasurePluto.class, "PlutoDungeonTreasureRoom");
        }

        MapGenDungeonPluto.initialized = true;
    }
}
