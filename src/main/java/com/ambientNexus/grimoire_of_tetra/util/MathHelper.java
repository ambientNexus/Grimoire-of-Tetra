package com.ambientNexus.grimoire_of_tetra.util;

public class MathHelper {
    public static int doubleToIntWithChance(Double num) {
        int res = num.intValue();
        if (num - res < 0.05d) { // handle perfectly divisible and "close enough" values
            return res;
        }
        return res + (num - res <= Math.random() ? 1 : 0); // seems like a good way to not truncate decimals for exp and chunk drops
    }
}
