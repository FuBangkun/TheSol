package mod.sol.client.gui.container;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicResultPage;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.core.client.gui.container.GuiPositionedContainer;
import mod.sol.Tags;
import mod.sol.inventory.ContainerSchematicRocket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class GuiSchematicRocket extends GuiPositionedContainer implements ISchematicResultPage {
    private final ResourceLocation schematicTexture;
    private final Item rocketItem;
    private int pageIndex;

    public GuiSchematicRocket(InventoryPlayer par1InventoryPlayer, BlockPos pos, int tier, Item rocketItem) {
        super(new ContainerSchematicRocket(par1InventoryPlayer, pos, tier), pos);
        this.ySize = 238;
        this.schematicTexture = new ResourceLocation(Tags.MOD_ID, "textures/gui/schematic_rocket_t" + tier + ".png");
        this.rocketItem = rocketItem;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 130, this.height / 2 - 110, 40, 20, I18n.format("gui.button.back.name")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 130, this.height / 2 - 110 + 25, 40, 20, I18n.format("gui.button.next.name")));
    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        if (par1GuiButton.enabled) {
            switch (par1GuiButton.id) {
                case 0:
                    SchematicRegistry.flipToLastPage(this, this.pageIndex);
                    break;
                case 1:
                    SchematicRegistry.flipToNextPage(this, this.pageIndex);
                    break;
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString(rocketItem.getItemStackDisplayName(new ItemStack(rocketItem, 1, 0)), 7, -20 + 27, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, 220 - 104 + 2 + 27, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(schematicTexture);
        final int var5 = (this.width - this.xSize) / 2;
        final int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void setPageIndex(int index) {
        this.pageIndex = index;
    }
}
