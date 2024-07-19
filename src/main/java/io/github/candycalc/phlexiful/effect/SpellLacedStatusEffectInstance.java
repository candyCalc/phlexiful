package io.github.candycalc.phlexiful.effect;

import at.petrak.hexcasting.api.spell.SpellList;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SpellLacedStatusEffectInstance extends StatusEffectInstance {
	private ServerPlayerEntity caster;
	private List<SpellList> spell;
	private double battery;

	//for convenience
	public SpellLacedStatusEffectInstance(
		StatusEffect type,
		int duration,
		int amplifier,
		boolean ambient,
		boolean showParticles,
		ServerPlayerEntity caster,
		List<SpellList> spell,
		double battery
	) {
		this(type, duration, amplifier, ambient, showParticles, caster, spell, battery, true, (StatusEffectInstance) null, type.getFactorData());
	}

	public SpellLacedStatusEffectInstance(
		StatusEffect type,
		int duration,
		int amplifier,
		boolean ambient,
		boolean showParticles,
		ServerPlayerEntity caster,
		List<SpellList> spell,
		double battery,
		boolean showIcon,
		@Nullable StatusEffectInstance hiddenEffect,
		Optional<FactorData> optional
	) {
		super(type, duration, amplifier, ambient, showParticles, showIcon, hiddenEffect, optional);
		this.caster = caster;
		this.spell = spell;
		this.battery = battery;
	}
}
