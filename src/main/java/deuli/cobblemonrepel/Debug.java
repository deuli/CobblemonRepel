package deuli.cobblemonrepel;

import com.cobblemon.mod.common.api.events.entity.SpawnEvent;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class Debug {
    public static void handle(SpawnEvent<PokemonEntity> event) {
        ServerWorld world = event.getCtx().getWorld();
        BlockPos spawnPos = event.getEntity().getBlockPos();
        
        ArmorStandEntity armorStand = new ArmorStandEntity(world, spawnPos.getX(), spawnPos.getY() + 1, spawnPos.getZ());
//        armorStand.setGlowing(true);
        if (event.isCanceled())
            armorStand.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.RED_CONCRETE));
        else
            armorStand.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.GREEN_CONCRETE));
        world.spawnEntity(armorStand);

        if (!event.isCanceled()) event.cancel();
    }
}
