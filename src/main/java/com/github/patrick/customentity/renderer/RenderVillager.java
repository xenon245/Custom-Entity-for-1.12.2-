package com.github.patrick.customentity.renderer;

import com.github.patrick.customentity.client.CustomEntity;
import com.github.patrick.customentity.client.CustomEntityManager;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVillager extends RenderLiving<EntityVillager>
{
    private static final ResourceLocation VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/villager.png");
    private static final ResourceLocation FARMER_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/farmer.png");
    private static final ResourceLocation LIBRARIAN_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/librarian.png");
    private static final ResourceLocation PRIEST_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/priest.png");
    private static final ResourceLocation SMITH_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/smith.png");
    private static final ResourceLocation BUTCHER_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/butcher.png");

    public RenderVillager(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelVillager(0.0F), 0.5F);
        this.addLayer(new LayerCustomHead(this.getMainModel().villagerHead));
    }

    public ModelVillager getMainModel()
    {
        return (ModelVillager)super.getMainModel();
    }

    protected ResourceLocation getEntityTexture(EntityVillager entity)
    {
        return entity.getProfessionForge().getSkin();
    }

    protected void preRenderCallback(EntityVillager entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.9375F;

        if (entitylivingbaseIn.getGrowingAge() < 0)
        {
            f = (float)((double)f * 0.5D);
            this.shadowSize = 0.25F;
        }
        else
        {
            this.shadowSize = 0.5F;
        }

        GlStateManager.scale(f, f, f);
        CustomEntity custom = CustomEntityManager.getOrCreateInstance().getCustomEntity(entitylivingbaseIn.getEntityId());
        if(custom != null) {
            this.shadowSize = custom.getShadowSize();
            custom.applyGraphic(entitylivingbaseIn);
        }
    }
}