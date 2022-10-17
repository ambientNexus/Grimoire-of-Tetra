package com.ambientNexus.grimoire_of_tetra;

import com.ambientNexus.grimoire_of_tetra.effects.*;
import com.ambientNexus.grimoire_of_tetra.util.IEventBusListener;
import com.ambientNexus.grimoire_of_tetra.util.tetra_definitions.IHoloDescription;
import com.ambientNexus.grimoire_of_tetra.util.tetra_definitions.ITetraEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import se.mickelus.tetra.items.modular.impl.toolbelt.ToolbeltHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public enum Mods {
	TETRA("tetra", () -> WitheredEffect::new, () -> FrostbiteEffect::new, () -> ThrenodyEffect::new, () -> RotbaneEffect::new, () -> FieryEffect::new); // useful effects outside of specific compat

	public final boolean isLoaded;
	private final Set<Object> loadedListeners = new HashSet<>();

	@SafeVarargs
	Mods(String modid, Supplier<Supplier<Object>>... eventListeners) {
		isLoaded = ModList.get().isLoaded(modid);
		if (isLoaded) {
			Arrays.stream(eventListeners)
				.map(Supplier::get)
				.map(Supplier::get)
				.filter(Objects::nonNull)
				.forEach(loadedListeners::add);
		}
	}

	private static Stream<Object> getLoadedListenersStream() {
		return Arrays.stream(Mods.values())
			.flatMap(mods -> mods.loadedListeners.stream());
	}

	public static void registerEventListeners() {
		getLoadedListenersStream()
			.filter(IEventBusListener.class::isInstance)
			.map(IEventBusListener.class::cast)
			.forEach(IEventBusListener::register);
		MinecraftForge.EVENT_BUS.register(Mods.class);
	}

	public static void clientSetup(FMLClientSetupEvent event) {
		getLoadedListenersStream()
			.filter(IHoloDescription.class::isInstance)
			.map(IHoloDescription.class::cast)
			.forEach(IHoloDescription::clientInit);
	}

	@SubscribeEvent
	public static void handlebeltTick(LivingEvent.LivingUpdateEvent event) {
		if (!(event.getEntityLiving() instanceof Player player))
			return;
		ItemStack belt = ToolbeltHelper.findToolbelt(player);

		getLoadedListenersStream()
			.filter(ITetraEffect.class::isInstance)
			.map(ITetraEffect.class::cast)
			.forEach(tetraEffect -> tetraEffect.doBeltTick(player, belt));
	}

}
