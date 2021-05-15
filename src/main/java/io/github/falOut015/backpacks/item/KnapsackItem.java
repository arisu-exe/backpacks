package io.github.falOut015.backpacks.item;

import io.github.falOut015.backpacks.inventory.KnapsackInventory;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class KnapsackItem extends Item implements IVanishable {
    public KnapsackItem(Properties properties) {
        super(properties);
//		this.addPropertyOverride(new ResourceLocation("two", "open"), (itemStack, world, livingEntity) -> {
//			if(livingEntity != null && livingEntity.getActiveItemStack().getItem() instanceof KnapsackItem)
//				if(livingEntity instanceof PlayerEntity)
//					if(livingEntity.getActiveItemStack().hasTag() && livingEntity.getActiveItemStack().getTag().getBoolean("open") == true) {
//						return 1.0f;
//					}
//			return 0.0f;
//		});
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return !stack.getOrCreateTag().getList("Items", 10).isEmpty();
    }
    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return (18.0 - (double) stack.getOrCreateTag().getList("Items", 10).size()) / 18.0;
    }
    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return (int) Math.round(MathHelper.lerp(65280d, 16711680d, this.getDurabilityForDisplay(stack)));
    }
    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        super.use(worldIn, playerIn, handIn);

        ItemStack itemStack = playerIn.getItemInHand(handIn);

        if(itemStack.hasTag()) {
            itemStack.getTag().putBoolean("open", true);
        } else {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putBoolean("open", true);
            itemStack.setTag(nbt);
        }

        INamedContainerProvider knapsackContainerProvider = new KnapsackInventory(playerIn, itemStack);
        playerIn.openMenu(knapsackContainerProvider);
        playerIn.awardStat(Stats.ITEM_USED.get(this));

        return ActionResult.success(itemStack);
    }
	/*@Override
	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);

		CompoundNBT compoundnbt = stack.getChildTag("BlockEntityTag");
		if (compoundnbt != null) {
			if (compoundnbt.contains("LootTable", 8)) {
				tooltip.add(new StringTextComponent("???????"));
			}

			if (compoundnbt.contains("Items", 9)) {
				NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
	            ItemStackHelper.loadAllItems(compoundnbt, nonnulllist);
	            int i = 0;
	            int j = 0;

	            for(ItemStack itemstack : nonnulllist) {
	            	if (!itemstack.isEmpty()) {
	            		++j;
	            		if (i <= 4) {
	            			++i;
	            			ITextComponent itextcomponent = itemstack.getDisplayName().deepCopy();
	            			itextcomponent.appendText(" x").appendText(String.valueOf(itemstack.getCount()));
	            			tooltip.add(itextcomponent);
	            		}
	            	}
	            }

	            if (j - i > 0) {
	            	tooltip.add((new TranslationTextComponent("container.knapsack.more", j - i)).applyTextStyle(TextFormatting.ITALIC));
	            }
			}
		}

		/// TODO
		 * CUSTOM NAME STUFF AT SOME POINT
		 * SHOWING WHAT IS IN A KNAPSACK
		 * LOOTTABLE SUPPORT
		 * ANIMATION
		 * LOOK INTO REPLACING THE KNAPSACK INVENTORY WITH AN IITEMHANDLER
	}*/
}