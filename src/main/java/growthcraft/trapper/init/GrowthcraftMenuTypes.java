package growthcraft.trapper.init;

import growthcraft.trapper.screen.FishtrapMenu;
import growthcraft.trapper.shared.Reference;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(
            ForgeRegistries.CONTAINERS, Reference.MODID
    );

    public static final RegistryObject<MenuType<FishtrapMenu>> FISHTRAP_MENU =
            registerMenuType(Reference.UnlocalizedName.FISHTRAP, FishtrapMenu::new);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(
            String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

}
