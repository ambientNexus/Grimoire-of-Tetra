package com.ambientNexus.grimoire_of_tetra;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(grimoire_of_tetra.MOD_ID)
public class grimoire_of_tetra {

    public static final String MOD_ID = "grimoire_of_tetra";

    public grimoire_of_tetra () {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
