package net.shadowfacts.yeoldetanks.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Barrel - shadowfacts
 * Created using Tabula 4.1.1
 */
public class ModelLid extends ModelBase {
    private ModelRenderer lid1;
    private ModelRenderer lid2;
    private ModelRenderer lid3;
    private ModelRenderer lid4;
    private ModelRenderer lid5;
    private ModelRenderer lid6;
    private ModelRenderer lid7;
    private ModelRenderer lid8;
    private ModelRenderer lid9;
    private ModelRenderer lid10;
    private ModelRenderer lid11;

    public ModelLid() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.lid6 = new ModelRenderer(this, 0, 28);
        this.lid6.setRotationPoint(4.0F, 7.0F, 4.0F);
        this.lid6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.lid1 = new ModelRenderer(this, 4, 4);
        this.lid1.setRotationPoint(-4.0F, 7.0F, -7.0F);
        this.lid1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 14, 0.0F);
        this.lid9 = new ModelRenderer(this, 0, 25);
        this.lid9.setRotationPoint(5.0F, 7.0F, -5.0F);
        this.lid9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.lid2 = new ModelRenderer(this, 34, 0);
        this.lid2.setRotationPoint(-7.0F, 7.0F, -4.0F);
        this.lid2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 8, 0.0F);
        this.lid4 = new ModelRenderer(this, 0, 28);
        this.lid4.setRotationPoint(-5.0F, 7.0F, 4.0F);
        this.lid4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.lid7 = new ModelRenderer(this, 0, 25);
        this.lid7.setRotationPoint(5.0F, 7.0F, 4.0F);
        this.lid7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.lid11 = new ModelRenderer(this, 0, 25);
        this.lid11.setRotationPoint(-6.0F, 7.0F, -5.0F);
        this.lid11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.lid8 = new ModelRenderer(this, 0, 28);
        this.lid8.setRotationPoint(4.0F, 7.0F, -6.0F);
        this.lid8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.lid5 = new ModelRenderer(this, 0, 25);
        this.lid5.setRotationPoint(-6.0F, 7.0F, 4.0F);
        this.lid5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.lid3 = new ModelRenderer(this, 34, 0);
        this.lid3.setRotationPoint(4.0F, 7.0F, -4.0F);
        this.lid3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 8, 0.0F);
        this.lid10 = new ModelRenderer(this, 0, 28);
        this.lid10.setRotationPoint(-5.0F, 7.0F, -6.0F);
        this.lid10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.lid6.render(f5);
        this.lid1.render(f5);
        this.lid9.render(f5);
        this.lid2.render(f5);
        this.lid4.render(f5);
        this.lid7.render(f5);
        this.lid11.render(f5);
        this.lid8.render(f5);
        this.lid5.render(f5);
        this.lid3.render(f5);
        this.lid10.render(f5);
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
        this.lid1.render(f5);
        this.lid2.render(f5);
        this.lid3.render(f5);
        this.lid4.render(f5);
        this.lid5.render(f5);
        this.lid6.render(f5);
        this.lid7.render(f5);
        this.lid9.render(f5);
        this.lid8.render(f5);
        this.lid10.render(f5);
        this.lid11.render(f5);
	}
}
