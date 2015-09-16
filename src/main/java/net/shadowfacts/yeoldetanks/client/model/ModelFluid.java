package net.shadowfacts.yeoldetanks.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Fluid - Shadowfacts
 * Created using Tabula 4.1.1
 */
public class ModelFluid extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape6;
    public ModelRenderer shape7;
    public ModelRenderer shape8;
    public ModelRenderer shape9;
    public ModelRenderer shape10;
    public ModelRenderer shape11;
    public ModelRenderer shape12;
    public ModelRenderer shape13;

    public ModelFluid() {
        this.textureWidth = 16;
        this.textureHeight = 512;
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(-7.0F, 23.0F, -4.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 14, 1, 8, 0.0F);
        this.shape7 = new ModelRenderer(this, 0, 0);
        this.shape7.setRotationPoint(-6.0F, 23.0F, 4.0F);
        this.shape7.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.shape12 = new ModelRenderer(this, 0, 0);
        this.shape12.setRotationPoint(4.0F, 23.0F, -6.0F);
        this.shape12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape9 = new ModelRenderer(this, 0, 0);
        this.shape9.setRotationPoint(4.0F, 23.0F, -5.0F);
        this.shape9.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.shape13 = new ModelRenderer(this, 0, 0);
        this.shape13.setRotationPoint(4.0F, 23.0F, 5.0F);
        this.shape13.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape10 = new ModelRenderer(this, 0, 0);
        this.shape10.setRotationPoint(-5.0F, 23.0F, 5.0F);
        this.shape10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape11 = new ModelRenderer(this, 0, 0);
        this.shape11.setRotationPoint(-5.0F, 23.0F, -6.0F);
        this.shape11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 0);
        this.shape6.setRotationPoint(4.0F, 23.0F, 4.0F);
        this.shape6.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(-4.0F, 23.0F, 4.0F);
        this.shape2.addBox(0.0F, 0.0F, 0.0F, 8, 1, 3, 0.0F);
        this.shape3 = new ModelRenderer(this, 0, 0);
        this.shape3.setRotationPoint(-4.0F, 23.0F, -7.0F);
        this.shape3.addBox(0.0F, 0.0F, 0.0F, 8, 1, 3, 0.0F);
        this.shape8 = new ModelRenderer(this, 0, 0);
        this.shape8.setRotationPoint(-6.0F, 23.0F, -5.0F);
        this.shape8.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape1.render(f5);
        this.shape7.render(f5);
        this.shape12.render(f5);
        this.shape9.render(f5);
        this.shape13.render(f5);
        this.shape10.render(f5);
        this.shape11.render(f5);
        this.shape6.render(f5);
        this.shape2.render(f5);
        this.shape3.render(f5);
        this.shape8.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

	public void renderAll() {
		float f5 = 0.0625f; // I have no idea what this does, I found it one of Blay09's CookingForBlockheads models :V
		this.shape1.render(f5);
		this.shape7.render(f5);
		this.shape12.render(f5);
		this.shape9.render(f5);
		this.shape13.render(f5);
		this.shape10.render(f5);
		this.shape11.render(f5);
		this.shape6.render(f5);
		this.shape2.render(f5);
		this.shape3.render(f5);
		this.shape8.render(f5);
	}
}
