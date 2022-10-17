package com.ambientNexus.grimoire_of_tetra.effects;

import com.ambientNexus.grimoire_of_tetra.util.IEventBusListener;
import com.ambientNexus.grimoire_of_tetra.util.tetra_definitions.IHoloDescription;
import com.ambientNexus.grimoire_of_tetra.util.tetra_definitions.ITetraEffect;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.gui.stats.getter.IStatGetter;
import se.mickelus.tetra.gui.stats.getter.ITooltipGetter;

import java.util.Optional;

public class FieryEffect implements IHoloDescription, IEventBusListener {

    @SubscribeEvent
    public void attackEvent(LivingHurtEvent event) {
        if (hasEffect(event.getSource())) {
            LivingEntity targetEntity = event.getEntityLiving();
            targetEntity.setSecondsOnFire(15);
        }
    }
    @Override
    public ITooltipGetter getStatTooltipGetter(IStatGetter statGetter) {
        return (player, itemStack) -> I18n.get(getTooltipPath(),
                statGetter.getValue(player, itemStack), statGetter.getValue(player, itemStack));
    }

    @Override
    public ItemEffect getEffect() {
        return ITetraEffect.get("fiery");
    }
}