package deuli.cobblemonrepel;

import eu.pb4.polymer.core.api.item.PolymerHeadBlockItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RepelBlockItem extends PolymerHeadBlockItem {
    public RepelBlockItem() {
        super(CobblemonRepel.REPEL_BLOCK, new Item.Settings());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (world != null) {
            int repelRange = world.getGameRules().getInt(CobblemonRepel.REPEL_RANGE);
            if (repelRange > 0) {
                MutableText rangeText = Text.literal(String.valueOf(repelRange)).formatted(Formatting.LIGHT_PURPLE);
                tooltip.add(Text.translatable("block.cobblemonrepel.repel.info", rangeText).formatted(Formatting.GRAY));
            } else
                tooltip.add(Text.translatable("block.cobblemonrepel.repel.disabled").formatted(Formatting.RED));
        }
        tooltip.add(Text.translatable("block.cobblemonrepel.repel.info.right_click").formatted(Formatting.GRAY));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack activeItem = user.getStackInHand(hand);

        if (world.isClient) return TypedActionResult.pass(activeItem);

        if (CobblemonRepel.isRepelNearby((ServerWorld) world, user.getBlockPos()))
            user.sendMessage(Text.translatable("block.cobblemonrepel.repel.nearby"), true);
        else
            user.sendMessage(Text.translatable("block.cobblemonrepel.repel.not_found"), true);

        return TypedActionResult.success(activeItem);
    }
}
