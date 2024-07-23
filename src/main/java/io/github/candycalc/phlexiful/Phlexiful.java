package io.github.candycalc.phlexiful;

import io.github.candycalc.phlexiful.casting.PhlexifulRegisterPatterns;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.candycalc.phlexiful.effect.ModEffects;

public class Phlexiful implements ModInitializer {
    public static final String MOD_ID = "phlexiful";
    public static final Logger LOGGER = LoggerFactory.getLogger("Phlexiful");

	public static void logMessage(String message) {
		LOGGER.info(message);
	}

    @Override
    public void onInitialize(ModContainer mod) {
        LOGGER.info("{} ficliogn workings", mod.metadata().name());

        ModEffects.registerEffect();
		PhlexifulRegisterPatterns.registerPatterns();
    }
}
