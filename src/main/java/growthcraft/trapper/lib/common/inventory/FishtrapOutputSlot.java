package growthcraft.trapper.lib.common.inventory;

import growthcraft.trapper.init.config.GrowthcraftTrapperConfig;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class FishtrapOutputSlot extends Slot {

    public FishtrapOutputSlot(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition){
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack){
        return GrowthcraftTrapperConfig.fishtrapOutputSlotsUnlocked();
    }

}
