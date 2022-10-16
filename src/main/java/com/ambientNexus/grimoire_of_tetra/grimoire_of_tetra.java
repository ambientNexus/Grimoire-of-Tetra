package com.ambientNexus.grimoire_of_tetra;

import com.ambientNexus.grimoire_of_tetra.networking.Packets;
import net.minecraft.core.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import com.ambientNexus.grimoire_of_tetra.predicate.PredicateManagers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BuildConfig.MODID)
public class grimoire_of_tetra {
    public static final Logger LOGGER = LogManager.getLogger(BuildConfig.MODID);

    public grimoire_of_tetra() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(BuildConfig.MODID + "-common.toml"));
        Mods.registerEventListeners();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(Mods::clientSetup);
        modEventBus.addListener((FMLCommonSetupEvent event) -> Packets.registerPackets());
        PredicateManagers.register();
    }

    public static final String MOD_ID = "grimoire_of_tetra";

    public static ResourceLocation asId(String name) {
        return new ResourceLocation(BuildConfig.MODID, name);
    }
}
