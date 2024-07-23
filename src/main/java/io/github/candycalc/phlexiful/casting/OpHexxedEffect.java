package io.github.candycalc.phlexiful.casting;

import at.petrak.hexcasting.api.utils.NBTHelper;
import at.petrak.hexcasting.api.misc.MediaConstants;
import at.petrak.hexcasting.api.spell.*;
import at.petrak.hexcasting.api.spell.casting.CastingContext;
import at.petrak.hexcasting.api.spell.casting.eval.SpellContinuation;
import at.petrak.hexcasting.api.spell.iota.Iota;

import io.github.candycalc.phlexiful.Phlexiful;
import io.github.candycalc.phlexiful.util.IEntityDataSaver;
import io.github.candycalc.phlexiful.util.SpellLacedData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;

import kotlin.Triple;
import java.util.List;

import org.jetbrains.annotations.NotNull;

public class OpHexxedEffect implements SpellAction {
	private StatusEffect statusEffect;
	private int amplifier;

	public OpHexxedEffect(
		StatusEffect statusEffect,
		int amplifier
	) {
		this.statusEffect = statusEffect;
		this.amplifier = amplifier;
	}

	@Override
	public int getArgc() {return 4;}
	@Override
	public boolean hasCastingSound(@NotNull CastingContext context) {return true;}
	@Override
	public boolean awardsCastingStat(@NotNull CastingContext context) {return true;}
	@Override
	public boolean isGreat() {return false;}
	@Override
	public boolean getAlwaysProcessGreatSpell() {return false;}
	@Override
	public boolean getCausesBlindDiversion() {return false;}
	@Override @NotNull
	public Text getDisplayName() {return DefaultImpls.getDisplayName(this);}

	@Override
	public Triple<RenderedSpell, Integer, List<ParticleSpray>> execute(@NotNull List<? extends Iota> args, @NotNull CastingContext context) {
		// Pattern arguments
		LivingEntity target = OperatorUtils.getLivingEntityButNotArmorStand(args, 0, getArgc());
		List<SpellList> spell = List.of(OperatorUtils.getList(args, 1, getArgc()));
		double duration = OperatorUtils.getPositiveDouble(args, 2, getArgc());
		double battery = OperatorUtils.getPositiveDouble(args, 3, getArgc());

		//check if target is in bounds
		context.assertEntityInRange(target);

		//calculate cost
		int cost = MediaConstants.DUST_UNIT * (((int) battery) + 10);

		return new Triple<RenderedSpell, Integer, List<ParticleSpray>>(
			new Spell(target, spell, duration, battery),
			cost,
			List.of(ParticleSpray.cloud(target.getPos(), 1.0, 50))
		);
	}

	public class Spell implements RenderedSpell {
		private LivingEntity target;
		private List<SpellList> spell;
		private double duration;
		private double battery;

		public Spell(LivingEntity target, List<SpellList> spell, double duration, double battery) {
			this.target = target;
			this.spell = spell;
			this.duration = duration;
			this.battery = battery;
		}

		@Override
		public void cast(@NotNull CastingContext context) {
			//make particles slightly transparent if spell targets yourself
			boolean ambient = (context.getCaster() == target);
			//create effect instance
			StatusEffectInstance effectInstance = new StatusEffectInstance(statusEffect, (int) duration * 20, amplifier, ambient, true); // context.getCaster(), spell, battery);
			//apply effect
			target.addStatusEffect(effectInstance);

			//write data to nbt
			SpellLacedData.setCaster(target, context.getCaster());
			SpellLacedData.setSpell(target, spell);
			SpellLacedData.setBattery(target, battery);
		}
	}

	@Override @NotNull
	public OperationResult operate(@NotNull SpellContinuation continuation, @NotNull List<Iota> stack, Iota ravenmind, @NotNull CastingContext castingContext) {
		return DefaultImpls.operate(this, continuation, stack, ravenmind, castingContext);
	}
}
