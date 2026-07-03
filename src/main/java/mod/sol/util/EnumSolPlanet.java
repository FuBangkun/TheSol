package mod.sol.util;

import lombok.Getter;

@Getter
public enum EnumSolPlanet {
    MERCURY("mercury", mod.sol.entities.boss.EntityMercuryBossBlaze.class),
    JUPITER("jupiter", mod.sol.entities.boss.EntityJupiterBossGhast.class),
    SATURN("saturn", mod.sol.entities.boss.EntitySaturnBossStray.class),
    URANUS("uranus", mod.sol.entities.boss.EntityUranusBossSlime.class),
    NEPTUNE("neptune", mod.sol.entities.boss.EntityNeptuneBossSpider.class),
    PLUTO("pluto", mod.sol.entities.boss.EntityBossSilverfish.class),
    SEDNA("sedna", mod.sol.entities.boss.EntityBossMagmaCube.class);

    private final String name;
    private final Class<? extends net.minecraft.entity.EntityLiving> bossClass;

    EnumSolPlanet(String name, Class<? extends net.minecraft.entity.EntityLiving> bossClass) {
        this.name = name;
        this.bossClass = bossClass;
    }
}