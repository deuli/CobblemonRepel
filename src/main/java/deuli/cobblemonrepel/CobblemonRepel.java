package deuli.cobblemonrepel;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import kotlin.Unit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;

public class CobblemonRepel implements ModInitializer {

    public static final String MOD_ID = "cobblemonrepel";

    public static final RepelBlock REPEL_BLOCK = Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "repel"), new RepelBlock());
    public static final RepelBlockItem REPEL_BLOCK_ITEM = Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "repel"), new RepelBlockItem());
    public static final GameRules.Key<GameRules.IntRule> REPEL_RANGE = GameRuleRegistry.register("repelRange", GameRules.Category.SPAWNING, GameRuleFactory.createIntRule(32, 0));

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(g -> g.add(REPEL_BLOCK_ITEM));

        CobblemonEvents.POKEMON_ENTITY_SPAWN.subscribe(Priority.HIGHEST, event -> {
            ServerWorld world = event.getCtx().getWorld();

            if (event.isCanceled() || world.getGameRules().getInt(REPEL_RANGE) == 0) return Unit.INSTANCE;

            BlockPos spawnPos = event.getCtx().getPosition();
            if (isRepelNearby(world, spawnPos)) {
                event.cancel();
            }

            return Unit.INSTANCE;
        });
    }

    public static boolean isRepelNearby(ServerWorld world, BlockPos pos) {
        int repelRange = world.getGameRules().getInt(REPEL_RANGE);
        return BlockPos.findClosest(pos, repelRange, repelRange, p -> world.getBlockState(p).getBlock().equals(REPEL_BLOCK)).isPresent();
    }
}
