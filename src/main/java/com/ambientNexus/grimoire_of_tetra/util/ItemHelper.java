package com.ambientNexus.grimoire_of_tetra.util;

import com.ambientNexus.grimoire_of_tetra.mixin.invoker.AbstractArrowInvoker;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ItemHelper {

	private ItemHelper() {
	}

	@Nullable
	public static ItemStack getThrownItemStack(@Nullable Entity e) {
		if (!(e instanceof AbstractArrow))
			return null;
		return ((AbstractArrowInvoker) e).callGetPickupItem();
	}

	public static ItemStack smelt(ItemStack stack, Level world) { // this is just forge example code switched over to my mappings but we won't talk about that
		return world.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), world)
			.map(SmeltingRecipe::getResultItem)
			.filter(itemStack -> !itemStack.isEmpty())
			.map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
			.orElse(stack);
	}

	public static ItemStack getItemOfDamageSource(@Nullable DamageSource source) {
		if (source == null)
			return ItemStack.EMPTY;
		ItemStack thrownItem = ItemHelper.getThrownItemStack(source.getDirectEntity());
		if (thrownItem != null)
			return thrownItem;
		if (!(source.getEntity() instanceof LivingEntity livingEntity))
			return ItemStack.EMPTY;
		return livingEntity.getMainHandItem();
	}
}
