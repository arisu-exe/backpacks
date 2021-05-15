package io.github.falOut015.backpacks.inventory.container;

import io.github.falOut015.backpacks.MainBackpacks;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainersBackpacks {
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MainBackpacks.MODID);



    public static final RegistryObject<ContainerType<KnapsackContainer>> KNAPSACK = CONTAINERS.register("knapsack", () -> new ContainerType<>(KnapsackContainer::new));



    public static void register(IEventBus bus) {
        CONTAINERS.register(bus);
    }
}
