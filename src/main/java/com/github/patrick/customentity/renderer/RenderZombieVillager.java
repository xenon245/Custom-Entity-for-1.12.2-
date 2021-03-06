package com.github.patrick.customentity.renderer;

import com.github.patrick.customentity.client.CustomEntity;
import com.github.patrick.customentity.client.CustomEntityManager;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerVillagerArmor;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZombieVillager extends RenderBiped<EntityZombieVillager>
{
    private static final ResourceLocation ZOMBIE_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/zombie_villager/zombie_villager.png");
    private static final ResourceLocation ZOMBIE_VILLAGER_FARMER_LOCATION = new ResourceLocation("textures/entity/zombie_villager/zombie_farmer.png");
    private static final ResourceLocation ZOMBIE_VILLAGER_LIBRARIAN_LOC = new ResourceLocation("textures/entity/zombie_villager/zombie_librarian.png");
    private static final ResourceLocation ZOMBIE_VILLAGER_PRIEST_LOCATION = new ResourceLocation("textures/entity/zombie_villager/zombie_priest.png");
    private static final ResourceLocation ZOMBIE_VILLAGER_SMITH_LOCATION = new ResourceLocation("textures/entity/zombie_villager/zombie_smith.png");
    private static final ResourceLocation ZOMBIE_VILLAGER_BUTCHER_LOCATION = new ResourceLocation("textures/entity/zombie_villager/zombie_butcher.png");

    public RenderZombieVillager(RenderManager p_i47186_1_)
    {
        super(p_i47186_1_, new ModelZombieVillager(), 0.5F);
        this.addLayer(new LayerVillagerArmor(this));
    }

    protected ResourceLocation getEntityTexture(EntityZombieVillager entity)
    {
        if (true) return entity.getForgeProfession().getZombieSkin();
        switch (entity.getProfession())
        {
            case 0:
                return ZOMBIE_VILLAGER_FARMER_LOCATION;
            case 1:
                return ZOMBIE_VILLAGER_LIBRARIAN_LOC;
            case 2:
                return ZOMBIE_VILLAGER_PRIEST_LOCATION;
            case 3:
                return ZOMBIE_VILLAGER_SMITH_LOCATION;
            case 4:
                return ZOMBIE_VILLAGER_BUTCHER_LOCATION;
            case 5:
            default:
                return ZOMBIE_VILLAGER_TEXTURES;
        }
    }

    protected void applyRotations(EntityZombieVillager entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        if (entityLiving.isConverting())
        {
            rotationYaw += (float)(Math.cos((double)entityLiving.ticksExisted * 3.25D) * Math.PI * 0.25D);
        }

        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityZombieVillager entitylivingbaseIn, float partialTickTime) {
        CustomEntity custom = CustomEntityManager.getOrCreateInstance().getCustomEntity(entitylivingbaseIn.getEntityId());
        if(custom != null) {
            this.shadowSize = custom.getShadowSize();
            custom.applyGraphic(entitylivingbaseIn);
        }
    }
}