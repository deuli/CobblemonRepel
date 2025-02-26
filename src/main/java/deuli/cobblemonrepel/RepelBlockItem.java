package deuli.cobblemonrepel;

import eu.pb4.polymer.core.api.block.PolymerHeadBlock;
import eu.pb4.polymer.core.api.item.PolymerHeadBlockItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;

public class RepelBlockItem extends PolymerHeadBlockItem {
    private final String repelType;

    public static int RANGE;
    public static HashMap<String, Integer> MULTIPLIERS = new HashMap<>();

    public <T extends Block & PolymerHeadBlock> RepelBlockItem(T block, String repelType) {
        super(block, new Item.Settings());
        this.repelType = repelType;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        int totalRange = RANGE * MULTIPLIERS.getOrDefault(repelType, 1);
        if (totalRange > 0) {
            MutableText rangeText = Text.literal(String.valueOf(totalRange)).formatted(Formatting.LIGHT_PURPLE);
            tooltip.add(Text.translatable("block.cobblemonrepel.repel.info", rangeText).formatted(Formatting.GRAY));
        } else
            tooltip.add(Text.translatable("block.cobblemonrepel.repel.disabled").formatted(Formatting.RED));
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
