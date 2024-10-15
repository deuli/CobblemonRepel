package deuli.cobblemonrepel;

import eu.pb4.polymer.core.api.block.PolymerHeadBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import org.jetbrains.annotations.Nullable;

public class RepelBlock extends Block implements PolymerHeadBlock {
    public static BooleanProperty ATTACHED = Properties.ATTACHED;
    public static final IntProperty ROTATION = Properties.ROTATION;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private final String POLYMER_SKIN_VALUE;
    private final int REPEL_LEVEL;

    public RepelBlock(String polymerSkinValue, int repelLevel) {
        super(Settings.create().nonOpaque().strength(2.5F, 3.5F));
        POLYMER_SKIN_VALUE = polymerSkinValue;
        REPEL_LEVEL = repelLevel;
    }

    public int getRepelLevel() {
        return REPEL_LEVEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(ATTACHED, ROTATION, FACING);
    }

    @Override
    public String getPolymerSkinValue(BlockState state, BlockPos pos, ServerPlayerEntity player) {
        return POLYMER_SKIN_VALUE;
    }

    @Override
    public Block getPolymerBlock(BlockState state) {
        return Blocks.PLAYER_HEAD;
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, ServerPlayerEntity player) {
        if (state.get(ATTACHED))
            return Blocks.PLAYER_WALL_HEAD.getDefaultState().with(FACING, state.get(FACING));
        else
            return Blocks.PLAYER_HEAD.getDefaultState().with(Properties.ROTATION, state.get(Properties.ROTATION));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getPlacementDirections()[0];
        if (direction == Direction.UP || direction == Direction.DOWN)
            return this.getDefaultState().with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw())).with(ATTACHED, false);
        else
            return this.getDefaultState().with(FACING, direction.getOpposite()).with(ATTACHED, true);
    }
}
