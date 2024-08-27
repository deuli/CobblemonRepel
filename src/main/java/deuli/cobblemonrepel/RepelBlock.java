package deuli.cobblemonrepel;

import eu.pb4.polymer.core.api.block.PolymerHeadBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class RepelBlock extends Block implements PolymerHeadBlock {
    public RepelBlock() {
        super(Settings.create().nonOpaque().strength(2.5F, 3.5F));
    }

    @Override
    public String getPolymerSkinValue(BlockState state, BlockPos pos, ServerPlayerEntity player) {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTg1Njg5MTgyOWY1ZTEyNWMyNTIwMTZhZTdlNDg0NmVmMzllNzBmZTBlYzEzMmI1YTUxMjZjZWFhMGE2NWY0NCJ9fX0=";
    }

    @Override
    public Block getPolymerBlock(BlockState state) {
        return Blocks.PLAYER_HEAD;
    }
}
