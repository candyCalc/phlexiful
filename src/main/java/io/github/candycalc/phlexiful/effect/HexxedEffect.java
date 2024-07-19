package io.github.candycalc.phlexiful.effect;

import io.github.candycalc.phlexiful.Phlexiful;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

import java.util.List;

public class HexxedEffect extends StatusEffect {
    protected HexxedEffect(StatusEffectType statusEffectType, int color) {
		super(statusEffectType, color);
    }

	@Override
	public void applyUpdateEffect(LivingEntity target, int amplifier) {
		if (!target.world.isClient()) {
			Phlexiful.logMessage("Lmao you got hexxed. {} seconds left");
		}

		super.applyUpdateEffect(target,amplifier);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return duration % 20 == 0;
	}

	public StatusEffect addCastingData() {

		return this;
	}
}
