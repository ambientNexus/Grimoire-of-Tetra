package com.ambientNexus.grimoire_of_tetra.predicate.entity;

import com.ambientNexus.grimoire_of_tetra.predicate.AbstractPredicate;
import net.minecraft.world.entity.Entity;

public abstract class AbstractEntityPredicate extends AbstractPredicate<Entity, AbstractEntityPredicate> {
	@Override
	protected String getKey() {
		return "entity";
	}
}
