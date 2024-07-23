package io.github.candycalc.phlexiful.effect;

import at.petrak.hexcasting.api.spell.casting.CastingContext;
import at.petrak.hexcasting.api.spell.casting.CastingHarness;
import at.petrak.hexcasting.api.spell.iota.Iota;
import io.github.candycalc.phlexiful.util.SpellLacedData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;

import java.util.List;
import java.util.UUID;


public class HexxedEffect extends StatusEffect {
    protected HexxedEffect(StatusEffectType statusEffectType, int color) {
		super(statusEffectType, color);
    }

	@Override
	public void applyUpdateEffect(LivingEntity target, int amplifier) {
		if (!target.world.isClient()) {
			if (SpellLacedData.hasLacedSpell(target)) {
				UUID casterUuid = SpellLacedData.getCasterUuid(target);
				ServerWorld sWorld = (ServerWorld) target.world;
				ServerPlayerEntity sPlayer = (ServerPlayerEntity) sWorld.getPlayerByUuid(casterUuid);

				if (sPlayer != null) {
					List<Iota> spell = SpellLacedData.getSpell(target, sWorld);

					double battery = SpellLacedData.getBattery(target);

					var ctx = new CastingContext(sPlayer, Hand.MAIN_HAND, CastingContext.CastSource.PACKAGED_HEX);
					var harness = new CastingHarness(ctx);
					var info = harness.executeIotas(spell, sWorld);
				}
			}
		}
		super.applyUpdateEffect(target,amplifier);
	}


	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return duration % 20 == 0;
	}
}
