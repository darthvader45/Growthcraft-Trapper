package growthcraft.trapper;

import growthcraft.trapper.init.GrowthcraftBlockEntities;
import growthcraft.trapper.init.GrowthcraftTrapperBlocks;
import growthcraft.trapper.init.client.GrowthcraftTrapperBlockRenders;
import growthcraft.trapper.shared.Reference;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftTrapper {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public GrowthcraftTrapper() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetupEvent);

        GrowthcraftTrapperBlocks.BLOCKS.register(modEventBus);
        GrowthcraftBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Do nothing for now ...
    }

    private void clientSetupEvent(final FMLClientSetupEvent event) {
        GrowthcraftTrapperBlockRenders.registerBlockRenders();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info(String.format("%s is starting ...", Reference.NAME));
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> itemRegistry = event.getRegistry();
        final Item.Properties properties = new Item.Properties().tab(Reference.CREATIVE_TAB);
        GrowthcraftTrapperBlocks.registerBlockItems(itemRegistry, properties);
    }

}
