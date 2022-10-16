package com.ambientNexus.grimoire_of_tetra.predicate.damage_source;

import com.ambientNexus.grimoire_of_tetra.predicate.AbstractPredicate;
import net.minecraft.world.damagesource.DamageSource;

public abstract class AbstractDamageSourcePredicate extends AbstractPredicate<DamageSource, AbstractDamageSourcePredicate> {
	@Override
	protected String getKey() {
		return "damage type";
	}
}
