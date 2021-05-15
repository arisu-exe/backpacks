package io.github.falOut015.backpacks.client.gui;

import io.github.falOut015.backpacks.client.gui.screen.inventory.KnapsackScreen;
import io.github.falOut015.backpacks.inventory.container.ContainersBackpacks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ScreenManagerBackpacks {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        ScreenManager.register(ContainersBackpacks.KNAPSACK.get(), KnapsackScreen::new);
    }
}