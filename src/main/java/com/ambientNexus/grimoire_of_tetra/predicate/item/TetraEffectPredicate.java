package com.ambientNexus.grimoire_of_tetra.predicate.item;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.ambientNexus.grimoire_of_tetra.BuildConfig;
import com.ambientNexus.grimoire_of_tetra.grimoire_of_tetra;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.ModularItem;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = BuildConfig.MODID)
public class TetraEffectPredicate extends ItemPredicate {
	private static final ItemPredicate ANY = new TetraEffectPredicate(null);
	private static final String JSON_EFFECT_KEY = "effect";
	private static final ResourceLocation ID = grimoire_of_tetra.asId("has_effect");

	@Nullable
	private final ItemEffect effect;

	public TetraEffectPredicate(@Nullable ItemEffect effect) {
		this.effect = effect;
	}

	private static ItemPredicate fromJson(@Nullable JsonObject jsonObject) {
		if (jsonObject != null && !jsonObject.isJsonNull()) {
			if (jsonObject.has(JSON_EFFECT_KEY)) {
				return new TetraEffectPredicate(ItemEffect.get(GsonHelper.getAsString(jsonObject, JSON_EFFECT_KEY)));
			}

			return ANY;
		} else {
			return ItemPredicate.ANY;
		}
	}

	@SubscribeEvent
	public static void register(FMLCommonSetupEvent event) {
		ItemPredicate.register(ID, TetraEffectPredicate::fromJson);
	}

	@Override
	public boolean matches(ItemStack tool) {
		if (tool.isEmpty() || !(tool.getItem() instanceof ModularItem))
			return false;
		if (effect == null)
			return true;
		return ((ModularItem) tool.getItem()).getEffectLevel(tool, effect) > 0;
	}

	@Override
	public JsonElement serializeToJson() {
		if (effect == null) {
			return JsonNull.INSTANCE;
		} else {
			JsonObject jsonobject = new JsonObject();
			jsonobject.addProperty(JSON_EFFECT_KEY, effect.getKey());
			return jsonobject;
		}
	}
}
