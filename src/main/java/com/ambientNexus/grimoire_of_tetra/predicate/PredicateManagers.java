package com.ambientNexus.grimoire_of_tetra.predicate;

import com.ambientNexus.grimoire_of_tetra.predicate.damage_source.AbstractDamageSourcePredicate;
import com.ambientNexus.grimoire_of_tetra.predicate.damage_source.TestWeaponPredicate;
import com.ambientNexus.grimoire_of_tetra.predicate.entity.AbstractEntityPredicate;
import com.ambientNexus.grimoire_of_tetra.predicate.entity.CreatureAttributePredicate;

public class PredicateManagers {
	public static final AbstractPredicateManager<AbstractEntityPredicate> ENTITY_PREDICATES =
		new AbstractPredicateManager.Builder<>(AbstractEntityPredicate.class, "entitypredicates")
			.queue("creature_attribute", CreatureAttributePredicate::new)
			.build();

	public static final AbstractPredicateManager<AbstractDamageSourcePredicate> DAMAGE_SOURCE_PREDICATES =
		new AbstractPredicateManager.Builder<>(AbstractDamageSourcePredicate.class, "damagesourcepredicates")
			.queue("weapon", TestWeaponPredicate::new)
			.build();

	private PredicateManagers() {
	}

	public static void register() {
		// classloading
	}
}
