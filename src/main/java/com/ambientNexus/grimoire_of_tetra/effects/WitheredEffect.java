package com.ambientNexus.grimoire_of_tetra.effects;

import com.ambientNexus.grimoire_of_tetra.util.IEventBusListener;
import com.ambientNexus.grimoire_of_tetra.util.tetra_definitions.IPercentageHoloDescription;
import com.ambientNexus.grimoire_of_tetra.util.tetra_definitions.ITetraEffect;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.gui.stats.getter.IStatGetter;
import se.mickelus.tetra.gui.stats.getter.ITooltipGetter;

import java.util.Optional;

public class WitheredEffect implements IPercentageHoloDescription, IEventBusListener {

    @SubscribeEvent
    public void attackEvent(LivingHurtEvent event) {
        if (hasEffect(event.getSource())) {
            LivingEntity targetEntity = event.getEntityLiving();
            double efficiency = getEffectEfficiency(event.getSource());
            if (Math.random() < efficiency / 100f) {
                int currentAmplifier = Optional.ofNullable(targetEntity.getEffect(MobEffects.WITHER))
                        .map(MobEffectInstance::getAmplifier)
                        .orElse(-1);

                targetEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, Math.min(currentAmplifier + 1, 3), false, false));
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
        return ITetraEffect.get("withered");
    }
}
