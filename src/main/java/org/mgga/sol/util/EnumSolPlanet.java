package org.mgga.sol.util;

import lombok.Getter;

@Getter
public enum EnumSolPlanet {
    MERCURY("mercury", org.mgga.sol.entities.boss.EntityMercuryBossBlaze.class),
    JUPITER("jupiter", org.mgga.sol.entities.boss.EntityJupiterBossGhast.class),
    SATURN("saturn", org.mgga.sol.entities.boss.EntitySaturnBossStray.class),
    URANUS("uranus", org.mgga.sol.entities.boss.EntityUranusBossSlime.class),
    NEPTUNE("neptune", org.mgga.sol.entities.boss.EntityNeptuneBossSpider.class),
    PLUTO("pluto", org.mgga.sol.entities.boss.EntityBossSilverfish.class),
    SEDNA("sedna", org.mgga.sol.entities.boss.EntityBossMagmaCube.class);

    private final String name;
    private final Class<? extends net.minecraft.entity.EntityLiving> bossClass;

    EnumSolPlanet(String name, Class<? extends net.minecraft.entity.EntityLiving> bossClass) {
        this.name = name;
        this.bossClass = bossClass;
    }
}