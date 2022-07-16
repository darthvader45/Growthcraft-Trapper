package growthcraft.trapper.init;

import growthcraft.trapper.GrowthcraftTrapper;
import growthcraft.trapper.block.FishtrapBlock;
import growthcraft.trapper.shared.Reference;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftTrapperBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<FishtrapBlock> FISHTRAP_OAK = BLOCKS.register(
            Reference.UnlocalizedName.FISHTRAP_OAK,
            FishtrapBlock::new
    );

    public static final RegistryObject<FishtrapBlock> FISHTRAP_ACACIA = BLOCKS.register(
            Reference.UnlocalizedName.FISHTRAP_ACACIA,
            FishtrapBlock::new
    );

    public static final RegistryObject<FishtrapBlock> FISHTRAP_DARK_OAK = BLOCKS.register(
            Reference.UnlocalizedName.FISHTRAP_DARK_OAK,
            FishtrapBlock::new
    );

    public static final RegistryObject<FishtrapBlock> FISHTRAP_BIRCH = BLOCKS.register(
            Reference.UnlocalizedName.FISHTRAP_BIRCH,
            FishtrapBlock::new
    );

    public static final RegistryObject<FishtrapBlock> FISHTRAP_SPRUCE = BLOCKS.register(
            Reference.UnlocalizedName.FISHTRAP_SPRUCE,
            FishtrapBlock::new
    );

    public static final RegistryObject<FishtrapBlock> FISHTRAP_JUNGLE = BLOCKS.register(
            Reference.UnlocalizedName.FISHTRAP_JUNGLE,
            FishtrapBlock::new
    );

    /**
     * Dynamically register Growthcraft Decorations BlockItems.
     *
     * @param itemRegistry IForgeRegistry<Item> reference for registering items.
     * @param properties   Item properties with item group for creative tab.
     */
    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        GrowthcraftTrapper.LOGGER.debug("<Growthcraft-Deco> Registration of itemBlocks started ...");

        String.format("%s registration of  BlockItems started ...", Reference.NAME);

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

        String.format("%s registration of  BlockItems finished.", Reference.NAME);

    }
}
