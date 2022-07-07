package growthcraft.trapper.common.block.entity;

import growthcraft.lib.utils.BlockStateUtils;
import growthcraft.lib.utils.TickUtils;
import growthcraft.trapper.GrowthcraftTrapper;
import growthcraft.trapper.init.GrowthcraftBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

public class FishtrapBlockEntity extends BlockEntity implements BlockEntityTicker<FishtrapBlockEntity> {

    // TODO: Add configuration setting for setting the server wide min/max ticking bounds.
    private final int minTickFishing = TickUtils.toTicks(10, "seconds");
    private final int maxTickFishing = TickUtils.toTicks(1, "minutes");
    private int tickClock = 0;
    private int tickCooldown = 0;

    public FishtrapBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(GrowthcraftBlockEntities.FISHTRAP_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, FishtrapBlockEntity fishtrapBlockEntity) {
        if (level.isClientSide) return;

        tickClock++;
        if (tickClock >= tickCooldown && canDoFishing(level, blockPos)) {
            this.doFishing();
            tickClock = 0;
            tickCooldown = TickUtils.getRandomTickCooldown(minTickFishing, maxTickFishing);
        }

        GrowthcraftTrapper.LOGGER.error("ticking ...");

    }

    /**
     * Determine if this entity is surrounded by fluid blocks.
     *
     * @param level World level
     * @param pos   BlockPos
     * @return Returns true if surrounded by proper Fluids.
     */
    @ParametersAreNonnullByDefault
    private boolean canDoFishing(Level level, BlockPos pos) {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(level, pos);

        // Scenario 1 - BlockUp and BlockDown are water.
        if (blockMap.get("down") instanceof LiquidBlock
                && blockMap.get("up") instanceof LiquidBlock) {
            return true;
        }

        // Scenario 2 - BlockNorth, BlockEast, BlockSouth, and BlockWest are water.
        if (blockMap.get("north") instanceof LiquidBlock
                && blockMap.get("east") instanceof LiquidBlock
                && blockMap.get("south") instanceof LiquidBlock
                && blockMap.get("west") instanceof LiquidBlock) {
            return true;
        }

        // Scenario 3 - Horizontal blocks are Water and Block is WATERLOGGED.
        boolean eastWest = blockMap.get("east") instanceof LiquidBlock
                && blockMap.get("west") instanceof LiquidBlock;
        boolean northSouth = blockMap.get("north") instanceof LiquidBlock
                && blockMap.get("south") instanceof LiquidBlock;

        return (eastWest || northSouth) && this.getBlockState().getValue(BlockStateProperties.WATERLOGGED).equals(Boolean.TRUE);
    }

    private void doFishing() {
        this.getLevel().playSound((Player) null, this.worldPosition, SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.BLOCKS, 1.0f, 1.0f);

    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.serializeNBT();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
    }
}
