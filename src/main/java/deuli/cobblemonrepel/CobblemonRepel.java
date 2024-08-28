package deuli.cobblemonrepel;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import kotlin.Unit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class CobblemonRepel implements ModInitializer {

    public static final String MOD_ID = "cobblemonrepel";

    public static final int REPEL_RANGE = 32;

    public static final RepelBlock REPEL_BLOCK = Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "repel_block"), new RepelBlock());
    public static final RepelBlockItem REPEL_BLOCK_ITEM = Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "repel_block"), new RepelBlockItem());

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(g -> g.add(REPEL_BLOCK_ITEM));

        CobblemonEvents.POKEMON_ENTITY_SPAWN.subscribe(Priority.NORMAL, event -> {
            ServerWorld world = event.getCtx().getWorld();
            BlockPos spawnPos = event.getCtx().getPosition();
            if (BlockPos.findClosest(spawnPos, REPEL_RANGE, REPEL_RANGE,
                    pos -> world.getBlockState(pos).getBlock().equals(REPEL_BLOCK)).isPresent()) {
                event.cancel();
            }

            return Unit.INSTANCE;
        });
    }
}
