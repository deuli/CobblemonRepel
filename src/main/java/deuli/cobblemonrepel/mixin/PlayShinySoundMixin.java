package deuli.cobblemonrepel.mixin;

import com.cobblemon.mod.common.api.events.entity.SpawnEvent;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.timinc.mc.cobblemon.spawnnotification.events.PlayShinySound;

@Mixin(PlayShinySound.class)
public abstract class PlayShinySoundMixin {
    @Inject(method = "handle", at = @At("HEAD"), remap = false, cancellable = true)
    private void onInitialize(SpawnEvent<PokemonEntity> event, CallbackInfo ci) {
        if (event.isCanceled()) ci.cancel();
    }
}