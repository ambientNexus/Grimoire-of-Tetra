package com.ambientNexus.grimoire_of_tetra.predicate.damage_source;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.ambientNexus.grimoire_of_tetra.util.ItemHelper;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.damagesource.DamageSource;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class TestWeaponPredicate extends AbstractDamageSourcePredicate {
	@Nullable
	@Override
	protected Predicate<DamageSource> readInternal(JsonObject data) throws JsonSyntaxException {
		ItemPredicate itemPredicate = ItemPredicate.fromJson(data);
		return damageSource -> itemPredicate.matches(ItemHelper.getItemOfDamageSource(damageSource));
	}
}
