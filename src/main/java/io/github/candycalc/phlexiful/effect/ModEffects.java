package io.github.candycalc.phlexiful.effect;

import io.github.candycalc.phlexiful.Phlexiful;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEffects extends StatusEffects {
    public static StatusEffect HEXXED;

	//collecting the yellow squiggles in intellij is my fav pastime
    private static StatusEffect register(String name, StatusEffect entry) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(Phlexiful.MOD_ID, name), entry);
    }

    public static void registerEffect() {
        HEXXED = register("hexxed", (new HexxedEffect(StatusEffectType.HARMFUL, 13524717)));
    }
}
