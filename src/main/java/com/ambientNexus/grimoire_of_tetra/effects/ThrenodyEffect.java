package com.ambientNexus.grimoire_of_tetra.effects;

import com.ambientNexus.grimoire_of_tetra.util.IEventBusListener;
import com.ambientNexus.grimoire_of_tetra.util.tetra_definitions.IHoloDescription;
import com.ambientNexus.grimoire_of_tetra.util.tetra_definitions.ITetraEffect;
import net.minecraft.client.resources.language.I18n;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.gui.stats.getter.IStatGetter;
import se.mickelus.tetra.gui.stats.getter.ITooltipGetter;

public class ThrenodyEffect implements IHoloDescription, IEventBusListener {
    @SubscribeEvent
    public void attackEvent(LivingHurtEvent event) {
        float damage = event.getAmount();
        if (hasEffect(event.getSource())) {
            if(event.getEntityLiving().getType().getRegistryName().getNamespace().equals("undergarden")) {
                event.setAmount(damage * 1.5F);
            }
        }
    }

    @Override
    public ITooltipGetter getStatTooltipGetter(IStatGetter statGetter) {
        return (player, itemStack) -> I18n.get(getTooltipPath(),
                statGetter.getValue(player, itemStack), statGetter.getValue(player, itemStack));
    }

    @Override
    public ItemEffect getEffect() {
        return ITetraEffect.get("threnody");
    }
}
