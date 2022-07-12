package growthcraft.trapper.init;

import growthcraft.trapper.common.block.entity.FishtrapBlockEntity;
import growthcraft.trapper.shared.Reference;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftTrapperBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITIES, Reference.MODID
    );

    public static final RegistryObject<BlockEntityType<FishtrapBlockEntity>> FISHTRAP_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.FISHTRAP,
            () -> BlockEntityType.Builder.of(FishtrapBlockEntity::new,
                    GrowthcraftTrapperBlocks.FISHTRAP_OAK.get()
            ).build(null)
    );

    private GrowthcraftTrapperBlockEntities() {
        /* Disable automative default public constructor */
    }
}
