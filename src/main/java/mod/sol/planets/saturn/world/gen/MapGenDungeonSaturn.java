package mod.sol.planets.saturn.world.gen;

import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonConfiguration;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.world.gen.structure.MapGenStructureIO;

public class MapGenDungeonSaturn extends MapGenDungeon {
    private static boolean initialized;

    static {
        try {
            MapGenDungeonSaturn.initiateStructures();
        } catch (Throwable ignored) {

        }
    }

    public MapGenDungeonSaturn(DungeonConfiguration configuration) {
        super(configuration);
    }

    public static void initiateStructures() {
        if (!MapGenDungeonSaturn.initialized) {
            MapGenStructureIO.registerStructureComponent(RoomTreasureSaturn.class, "SaturnDungeonTreasureRoom");
        }

        MapGenDungeonSaturn.initialized = true;
    }
}
