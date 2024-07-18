package io.github.candycalc.phlexiful.effect;

import io.github.candycalc.phlexiful.Phlexiful;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class HexxedEffect extends StatusEffect {
    protected HexxedEffect(StatusEffectType statusEffectType, int color) {
		super(statusEffectType, color);
    }

	//@Override
	public void applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
		if (!pLivingEntity.world.isClient()) {
			Phlexiful.logMessage("Lmao you got hexxed");
		}

		super.applyUpdateEffect(pLivingEntity,pAmplifier);
	}

	@Override
	public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
		return true;
	}
}
