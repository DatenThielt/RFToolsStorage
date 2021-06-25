package mcjty.rftoolsstorage.modules.scanner.items;

import com.mojang.blaze3d.matrix.MatrixStack;
import mcjty.lib.client.RenderHelper;
import mcjty.lib.varia.DimensionId;
import mcjty.rftoolsbase.api.screens.IClientScreenModule;
import mcjty.rftoolsbase.api.screens.IModuleRenderHelper;
import mcjty.rftoolsbase.api.screens.ITextRenderHelper;
import mcjty.rftoolsbase.api.screens.ModuleRenderInfo;
import mcjty.rftoolsbase.api.screens.data.IModuleData;
import mcjty.rftoolsbase.tools.ScreenTextHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import mcjty.rftoolsbase.api.screens.IClientScreenModule.TransformMode;

public class DumpClientScreenModule implements IClientScreenModule<IModuleData> {
    private String line = "";
    private int color = 0xffffff;
    private ItemStack[] stacks = new ItemStack[DumpScreenModule.COLS * DumpScreenModule.ROWS];
    private ITextRenderHelper buttonCache = new ScreenTextHelper();

    @Override
    public TransformMode getTransformMode() {
        return TransformMode.TEXT;
    }

    @Override
    public int getHeight() {
        return 14;
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, IModuleRenderHelper renderHelper, FontRenderer fontRenderer, int currenty, IModuleData screenData, ModuleRenderInfo renderInfo) {
//        GlStateManager.disableLighting();
//        GlStateManager.enableDepthTest();
//        GlStateManager.depthMask(false);
        int xoffset = 7 + 5;

        RenderHelper.drawBeveledBox(matrixStack, buffer, xoffset - 5, currenty, 130 - 7, currenty + 12, 0xffeeeeee, 0xff333333, 0xff448866,
                renderInfo.getLightmapValue());
        buttonCache.setup(line, 490, renderInfo);
        buttonCache.renderText(matrixStack, buffer, xoffset -10, currenty + 2, color, renderInfo);
    }

    @Override
    public void mouseClick(World world, int x, int y, boolean clicked) {
    }


    @Override
    public void setupFromNBT(CompoundNBT tagCompound, DimensionId dim, BlockPos pos) {
        if (tagCompound != null) {
            line = tagCompound.getString("text");
            if (tagCompound.contains("color")) {
                color = tagCompound.getInt("color");
            } else {
                color = 0xffffff;
            }
            for (int i = 0 ; i < stacks.length ; i++) {
                if (tagCompound.contains("stack"+i)) {
                    stacks[i] = ItemStack.of(tagCompound.getCompound("stack" + i));
                }
            }
        }
    }

    @Override
    public boolean needsServerData() {
        return true;
    }
}
