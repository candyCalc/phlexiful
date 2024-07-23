package io.github.candycalc.phlexiful.casting;

import at.petrak.hexcasting.api.PatternRegistry;
import at.petrak.hexcasting.api.spell.math.HexDir;
import at.petrak.hexcasting.api.spell.math.HexPattern;
import io.github.candycalc.phlexiful.Phlexiful;
import io.github.candycalc.phlexiful.effect.ModEffects;
import net.minecraft.util.Identifier;

public class PhlexifulRegisterPatterns {
	public static void registerPatterns() {
		try {
			PatternRegistry.mapPattern(
				HexPattern.fromAngles("qawadawwawwaawdwwdwdw", HexDir.WEST),
				new Identifier(Phlexiful.MOD_ID, "hexxed_nadir"),
				new OpHexxedEffect(ModEffects.HEXXED, 1));
		} catch (PatternRegistry.RegisterPatternException oopsLol) {
			oopsLol.printStackTrace();
		}
	}
}
