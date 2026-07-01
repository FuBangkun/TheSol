package mod.sol.planets.neptune.world.gen;

import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonConfiguration;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.world.gen.structure.MapGenStructureIO;

public class MapGenDungeonNeptune extends MapGenDungeon {
    private static boolean initialized;

    static {
        try {
            MapGenDungeonNeptune.initiateStructures();
        } catch (Throwable ignored) {

        }
    }

    public MapGenDungeonNeptune(DungeonConfiguration configuration) {
        super(configuration);
    }

    public static void initiateStructures() {
        if (!MapGenDungeonNeptune.initialized) {
            MapGenStructureIO.registerStructureComponent(RoomTreasureNeptune.class, "NeptuneDungeonTreasureRoom");
        }

        MapGenDungeonNeptune.initialized = true;
    }
}
