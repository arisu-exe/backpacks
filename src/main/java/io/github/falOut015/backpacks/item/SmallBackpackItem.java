package io.github.falOut015.backpacks.item;

import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SmallBackpackItem extends Item implements IVanishable {
    public SmallBackpackItem(Properties properties) {
        super(properties);

        // TODO AAAAAAAAAAAAAAAAAAaaa

//		this.addPropertyOverride(new ResourceLocation("count"), (itemStack, world, livingEntity) -> {
//			if(livingEntity != null && livingEntity.getActiveItemStack().getItem() instanceof BackpackItem)
//				return 1.0f;
//			return 0.0f;
//			// return 0.0 for empty
//			// return 0.2 for 1
//			// return 0.4 for 2
//			// return 0.6 for 3
//			// return 0.8 for 4 or more
//		});
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        EquipmentSlotType equipmentSlotType = MobEntity.getEquipmentSlotForItem(itemStack);
        ItemStack stack = playerIn.getItemBySlot(equipmentSlotType);
        if (stack.isEmpty()) {
            playerIn.setItemSlot(equipmentSlotType, itemStack.copy());
            itemStack.setCount(0);
            return ActionResult.success(itemStack);
        } else {
            return ActionResult.fail(itemStack);
        }
    }
    @Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
        return EquipmentSlotType.CHEST;
    }
}