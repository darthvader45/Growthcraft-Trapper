package growthcraft.trapper.init.client;

import growthcraft.trapper.init.GrowthcraftTrapperBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class GrowthcraftTrapperBlockRenders {

    public static void registerBlockRenders() {
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftTrapperBlocks.FISHTRAP_ACACIA.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftTrapperBlocks.FISHTRAP_BIRCH.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftTrapperBlocks.FISHTRAP_DARK_OAK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftTrapperBlocks.FISHTRAP_JUNGLE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftTrapperBlocks.FISHTRAP_OAK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftTrapperBlocks.FISHTRAP_SPRUCE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftTrapperBlocks.SPAWNEGGTRAP.get(), RenderType.translucent());
    }

    private GrowthcraftTrapperBlockRenders() {
        /* Disable default public constructor */
    }
}
