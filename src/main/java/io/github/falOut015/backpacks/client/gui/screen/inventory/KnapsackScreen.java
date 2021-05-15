package io.github.falOut015.backpacks.client.gui.screen.inventory;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.falOut015.backpacks.inventory.container.KnapsackContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KnapsackScreen extends ContainerScreen<KnapsackContainer> {
    private static final ResourceLocation KNAPSACK_GUI_TEXTURE = new ResourceLocation("minecraft", "textures/gui/container/generic_54.png");

    public KnapsackScreen(KnapsackContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        this.imageHeight = 150;
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(KNAPSACK_GUI_TEXTURE);
        int i = (this.leftPos - this.width) / 2;
        int j = (this.topPos - this.height) / 2;
        this.fillGradient(matrixStack, i, j, 0, 0, this.imageWidth, 53);
        this.fillGradient(matrixStack, i, j + 53, 0, 126, this.imageWidth, 96);
    }
    @Override
    public void render(MatrixStack stack, int x, int y, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, x, y, partialTicks);
        this.renderTooltip(stack, x, y);
    }
}