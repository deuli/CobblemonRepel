package deuli.cobblemonrepel.mixin;

import com.cobblemon.mod.common.api.events.entity.SpawnEvent;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import kotlin.jvm.functions.Function1;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import us.timinc.mc.cobblemon.spawnnotification.SpawnNotification;

@Mixin(SpawnNotification.class)
public abstract class CobblemonSpawnNotificationMixin {
    @ModifyArg(method = "onInitialize", at = @At(value = "INVOKE", target = "Lcom/cobblemon/mod/common/api/reactive/Observable$DefaultImpls;subscribe$default(Lcom/cobblemon/mod/common/api/reactive/Observable;Lcom/cobblemon/mod/common/api/Priority;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Lcom/cobblemon/mod/common/api/reactive/ObservableSubscription;", ordinal = 0),
            index = 2, remap = false)
    private Function1<SpawnEvent<PokemonEntity>, Void> onInitialize(Function1<SpawnEvent<PokemonEntity>, Void> par3) {
        return event -> {
            if (!event.isCanceled()) par3.invoke(event);
            return null;
        };
    }
}