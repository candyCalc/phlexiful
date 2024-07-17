package io.github.candycalc.phlexiful;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.candycalc.phlexiful.effect.ModEffects;

public class Phlexiful implements ModInitializer {
    public static final String MOD_ID = "phlexiful";
    public static final Logger LOGGER = LoggerFactory.getLogger("phlexiful");

    @Override
    public void onInitialize(ModContainer mod) {
        LOGGER.info("Hello Quilt world from {}! Stay fresh!", mod.metadata().name());

        ModEffects.registerEffect();
    }
}