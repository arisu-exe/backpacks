package io.github.falOut015.backpacks.item;

import io.github.falOut015.backpacks.MainBackpacks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemsBackpacks {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MainBackpacks.MODID);



    public static final RegistryObject<Item> KNAPSACK = ITEMS.register("knapsack", () -> new KnapsackItem(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1)));
    public static final RegistryObject<Item> SMALL_BACKPACK = ITEMS.register("small_backpack", () -> new SmallBackpackItem(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    //public static final Item LARGE_BACKPACK = register("backpack", new BackpackItem(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
    //backpack -> wearable, another tab of items
    //fanny pack -> wearable, displays extra row
    //potion sack -> displays extra potion slots
    //quiver -> displays extra arrow slots



    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}