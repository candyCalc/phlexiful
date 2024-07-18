package io.github.candycalc.phlexiful.casting;

import at.petrak.hexcasting.api.misc.MediaConstants;
import at.petrak.hexcasting.api.spell.OperationResult;
import at.petrak.hexcasting.api.spell.ParticleSpray;
import at.petrak.hexcasting.api.spell.OperatorUtils;
import at.petrak.hexcasting.api.spell.RenderedSpell;
import at.petrak.hexcasting.api.spell.SpellAction;
import at.petrak.hexcasting.api.spell.casting.CastingContext;
import at.petrak.hexcasting.api.spell.casting.eval.SpellContinuation;
import at.petrak.hexcasting.api.spell.iota.Iota;

import kotlin.Triple;
import java.util.List;

import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class OpHexxedEffect implements SpellAction {
	public OpHexxedEffect() {}

	@Override
	public int getArgc() {return 1;}
	@Override
	public boolean hasCastingSound(@NotNull CastingContext ctx) {return true;}
	@Override
	public boolean awardsCastingStat(@NotNull CastingContext ctx) {return true;}
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

		return new Triple<RenderedSpell, Integer, List<ParticleSpray>>(
			new Spell(),
			MediaConstants.DUST_UNIT,
			List.of()
		);
	}

	private class Spell implements RenderedSpell {
		public Spell() {}

		@Override
		public void cast(@NotNull CastingContext ctx) {}
	}

	@Override @NotNull
	public OperationResult operate(@NotNull SpellContinuation continuation, @NotNull List<Iota> stack, Iota ravenmind, @NotNull CastingContext castingContext) {
		return DefaultImpls.operate(this, continuation, stack, ravenmind, castingContext);
	}
}
