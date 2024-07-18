package io.github.candycalc.phlexiful.casting;

import io.github.candycalc.phlexiful.effect.ModEffects;

import at.petrak.hexcasting.api.misc.MediaConstants;
import at.petrak.hexcasting.api.spell.*;
import at.petrak.hexcasting.api.spell.casting.CastingContext;
import at.petrak.hexcasting.api.spell.casting.eval.SpellContinuation;
import at.petrak.hexcasting.api.spell.iota.Iota;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.text.Text;

import kotlin.Triple;
import java.util.List;

import org.jetbrains.annotations.NotNull;

public class OpHexxedEffect implements SpellAction {
	public OpHexxedEffect() {}

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
		Double duration = OperatorUtils.getPositiveDouble(args, 2, getArgc());
		Double battery = OperatorUtils.getPositiveDouble(args, 3, getArgc());

		//check if target is in bounds
		context.assertEntityInRange(target);

		//calculate cost
		int cost = MediaConstants.DUST_UNIT * (battery.intValue() + 10);

		return new Triple<RenderedSpell, Integer, List<ParticleSpray>>(
			new Spell(target, spell, duration, battery),
			cost,
			List.of(ParticleSpray.cloud(target.getPos(), 1.0, 50))
		);
	}

	public class Spell implements RenderedSpell {
		private LivingEntity target;
		private List<SpellList> spell;
		private Double duration;
		private Double battery;

		public Spell(LivingEntity target, List<SpellList> spell, Double duration, Double battery) {
			this.target = target;
			this.spell = spell;
			this.duration = duration;
			this.battery = battery;
		}

		@Override
		public void cast(@NotNull CastingContext context) {
			StatusEffectInstance effectInstance = new StatusEffectInstance(ModEffects.HEXXED, duration.intValue() * 20, 0);
			target.addStatusEffect(effectInstance);
		}
	}

	@Override @NotNull
	public OperationResult operate(@NotNull SpellContinuation continuation, @NotNull List<Iota> stack, Iota ravenmind, @NotNull CastingContext castingContext) {
		return DefaultImpls.operate(this, continuation, stack, ravenmind, castingContext);
	}
}
