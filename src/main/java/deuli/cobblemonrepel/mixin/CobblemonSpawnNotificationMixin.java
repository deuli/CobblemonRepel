package deuli.cobblemonrepel.mixin;

import com.cobblemon.mod.common.api.events.entity.SpawnEvent;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.timinc.mc.cobblemon.spawnnotification.SpawnNotification;

@Mixin(SpawnNotification.class)
public class CobblemonSpawnNotificationMixin {
    @Inject(method = "broadcastSpawn", at = @At("HEAD"), remap = false, cancellable = true)
    private void broadcastSpawn(SpawnEvent<PokemonEntity> evt, CallbackInfo ci) {
        if (evt.isCanceled()) ci.cancel();
    }
}