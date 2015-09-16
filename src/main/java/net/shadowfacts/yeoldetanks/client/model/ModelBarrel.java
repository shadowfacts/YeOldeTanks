package net.shadowfacts.yeoldetanks.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Barrel - shadowfacts
 * Created using Tabula 4.1.1
 */
public class ModelBarrel extends ModelBase {
    public ModelRenderer North;
    public ModelRenderer South;
    public ModelRenderer West;
    public ModelRenderer East;
    public ModelRenderer shape5;
    public ModelRenderer shape6;
    public ModelRenderer shape8;
    public ModelRenderer shape9;
    public ModelRenderer shape10;
    public ModelRenderer shape11;
    public ModelRenderer shape12;
    public ModelRenderer shape13;
    public ModelRenderer shape14;
    public ModelRenderer shape15;
    public ModelRenderer shape16;
    public ModelRenderer shape17;
    public ModelRenderer shape18;
    public ModelRenderer shape19;
    public ModelRenderer shape21;
    public ModelRenderer shape22;
    public ModelRenderer shape23;
    public ModelRenderer shape24;
    public ModelRenderer shape26;
    public ModelRenderer shape27;
    public ModelRenderer shape28;
    public ModelRenderer shape29;
    public ModelRenderer shape7;
    public ModelRenderer lid1;
    public ModelRenderer lid2;
    public ModelRenderer lid3;
    public ModelRenderer lid4;
    public ModelRenderer lid5;
    public ModelRenderer lid6;
    public ModelRenderer lid7;
    public ModelRenderer lid8;
    public ModelRenderer lid9;
    public ModelRenderer lid10;
    public ModelRenderer lid11;

    public ModelBarrel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.lid6 = new ModelRenderer(this, 0, 28);
        this.lid6.setRotationPoint(4.0F, 7.0F, 4.0F);
        this.lid6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.shape29 = new ModelRenderer(this, 0, 0);
        this.shape29.setRotationPoint(-5.0F, 23.0F, -6.0F);
        this.shape29.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.lid1 = new ModelRenderer(this, 4, 4);
        this.lid1.setRotationPoint(-4.0F, 7.0F, -7.0F);
        this.lid1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 14, 0.0F);
        this.shape18 = new ModelRenderer(this, 0, 0);
        this.shape18.setRotationPoint(-4.0F, 23.0F, 4.0F);
        this.shape18.addBox(0.0F, 0.0F, 0.0F, 8, 1, 3, 0.0F);
        this.lid9 = new ModelRenderer(this, 0, 25);
        this.lid9.setRotationPoint(5.0F, 7.0F, -5.0F);
        this.lid9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape12 = new ModelRenderer(this, 0, 7);
        this.shape12.setRotationPoint(5.0F, 8.0F, 5.0F);
        this.shape12.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.West = new ModelRenderer(this, 0, 0);
        this.West.setRotationPoint(4.0F, 8.0F, -8.0F);
        this.West.addBox(0.0F, 0.0F, 0.0F, 1, 16, 8, 0.0F);
        this.setRotateAngle(West, 0.0F, -1.5707963267948966F, 0.0F);
        this.shape21 = new ModelRenderer(this, 0, 0);
        this.shape21.setRotationPoint(-6.0F, 23.0F, 4.0F);
        this.shape21.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.shape17 = new ModelRenderer(this, 14, 19);
        this.shape17.setRotationPoint(-7.0F, 23.0F, -4.0F);
        this.shape17.addBox(0.0F, 0.0F, 0.0F, 14, 1, 8, 0.0F);
        this.lid2 = new ModelRenderer(this, 34, 0);
        this.lid2.setRotationPoint(-7.0F, 7.0F, -4.0F);
        this.lid2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 8, 0.0F);
        this.shape24 = new ModelRenderer(this, 0, 0);
        this.shape24.setRotationPoint(4.0F, 23.0F, 5.0F);
        this.shape24.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape11 = new ModelRenderer(this, 0, 7);
        this.shape11.setRotationPoint(6.0F, 8.0F, 4.0F);
        this.shape11.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.lid4 = new ModelRenderer(this, 0, 28);
        this.lid4.setRotationPoint(-5.0F, 7.0F, 4.0F);
        this.lid4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.shape28 = new ModelRenderer(this, 0, 0);
        this.shape28.setRotationPoint(-6.0F, 23.0F, -5.0F);
        this.shape28.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.shape22 = new ModelRenderer(this, 0, 0);
        this.shape22.setRotationPoint(-5.0F, 23.0F, 5.0F);
        this.shape22.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape10 = new ModelRenderer(this, 0, 7);
        this.shape10.setRotationPoint(6.0F, 8.0F, -5.0F);
        this.shape10.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.lid7 = new ModelRenderer(this, 0, 25);
        this.lid7.setRotationPoint(5.0F, 7.0F, 4.0F);
        this.lid7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.South = new ModelRenderer(this, 0, 0);
        this.South.setRotationPoint(7.0F, 8.0F, -4.0F);
        this.South.addBox(0.0F, 0.0F, 0.0F, 1, 16, 8, 0.0F);
        this.shape9 = new ModelRenderer(this, 0, 7);
        this.shape9.setRotationPoint(5.0F, 8.0F, -6.0F);
        this.shape9.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.shape19 = new ModelRenderer(this, 0, 0);
        this.shape19.setRotationPoint(-4.0F, 23.0F, -7.0F);
        this.shape19.addBox(0.0F, 0.0F, 0.0F, 8, 1, 3, 0.0F);
        this.lid11 = new ModelRenderer(this, 0, 25);
        this.lid11.setRotationPoint(-6.0F, 7.0F, -5.0F);
        this.lid11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape5 = new ModelRenderer(this, 0, 7);
        this.shape5.setRotationPoint(-7.0F, 8.0F, -5.0F);
        this.shape5.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.lid8 = new ModelRenderer(this, 0, 28);
        this.lid8.setRotationPoint(4.0F, 7.0F, -6.0F);
        this.lid8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.shape8 = new ModelRenderer(this, 0, 7);
        this.shape8.setRotationPoint(4.0F, 8.0F, -7.0F);
        this.shape8.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.shape14 = new ModelRenderer(this, 0, 7);
        this.shape14.setRotationPoint(-5.0F, 8.0F, 6.0F);
        this.shape14.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.shape27 = new ModelRenderer(this, 0, 0);
        this.shape27.setRotationPoint(4.0F, 23.0F, -6.0F);
        this.shape27.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape7 = new ModelRenderer(this, 0, 7);
        this.shape7.setRotationPoint(-5.0F, 8.0F, -7.0F);
        this.shape7.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.East = new ModelRenderer(this, 0, 0);
        this.East.setRotationPoint(4.0F, 8.0F, 7.0F);
        this.East.addBox(0.0F, 0.0F, 0.0F, 1, 16, 8, 0.0F);
        this.setRotateAngle(East, 0.0F, -1.5707963267948966F, 0.0F);
        this.North = new ModelRenderer(this, 0, 0);
        this.North.setRotationPoint(-8.0F, 8.0F, -4.0F);
        this.North.addBox(0.0F, 0.0F, 0.0F, 1, 16, 8, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 7);
        this.shape6.setRotationPoint(-6.0F, 8.0F, -6.0F);
        this.shape6.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.lid5 = new ModelRenderer(this, 0, 25);
        this.lid5.setRotationPoint(-6.0F, 7.0F, 4.0F);
        this.lid5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape16 = new ModelRenderer(this, 0, 7);
        this.shape16.setRotationPoint(-7.0F, 8.0F, 4.0F);
        this.shape16.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.shape15 = new ModelRenderer(this, 0, 7);
        this.shape15.setRotationPoint(-6.0F, 8.0F, 5.0F);
        this.shape15.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.shape23 = new ModelRenderer(this, 0, 0);
        this.shape23.setRotationPoint(4.0F, 23.0F, 4.0F);
        this.shape23.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.lid3 = new ModelRenderer(this, 34, 0);
        this.lid3.setRotationPoint(4.0F, 7.0F, -4.0F);
        this.lid3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 8, 0.0F);
        this.shape13 = new ModelRenderer(this, 0, 7);
        this.shape13.setRotationPoint(4.0F, 8.0F, 6.0F);
        this.shape13.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.shape26 = new ModelRenderer(this, 0, 0);
        this.shape26.setRotationPoint(4.0F, 23.0F, -5.0F);
        this.shape26.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.lid10 = new ModelRenderer(this, 0, 28);
        this.lid10.setRotationPoint(-5.0F, 7.0F, -6.0F);
        this.lid10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.lid6.render(f5);
        this.shape29.render(f5);
        this.lid1.render(f5);
        this.shape18.render(f5);
        this.lid9.render(f5);
        this.shape12.render(f5);
        this.West.render(f5);
        this.shape21.render(f5);
        this.shape17.render(f5);
        this.lid2.render(f5);
        this.shape24.render(f5);
        this.shape11.render(f5);
        this.lid4.render(f5);
        this.shape28.render(f5);
        this.shape22.render(f5);
        this.shape10.render(f5);
        this.lid7.render(f5);
        this.South.render(f5);
        this.shape9.render(f5);
        this.shape19.render(f5);
        this.lid11.render(f5);
        this.shape5.render(f5);
        this.lid8.render(f5);
        this.shape8.render(f5);
        this.shape14.render(f5);
        this.shape27.render(f5);
        this.shape7.render(f5);
        this.East.render(f5);
        this.North.render(f5);
        this.shape6.render(f5);
        this.lid5.render(f5);
        this.shape16.render(f5);
        this.shape15.render(f5);
        this.shape23.render(f5);
        this.lid3.render(f5);
        this.shape13.render(f5);
        this.shape26.render(f5);
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

	public void renderAll(boolean renderLid) {
		float f5 = 0.0625f; // I have no idea what this does, I found it one of Blay09's CookingForBlockheads models :V

		this.shape29.render(f5);
		this.shape18.render(f5);
		this.shape12.render(f5);
		this.West.render(f5);
		this.shape21.render(f5);
		this.shape17.render(f5);
		this.shape24.render(f5);
		this.shape11.render(f5);
		this.shape28.render(f5);
		this.shape22.render(f5);
		this.shape10.render(f5);
		this.South.render(f5);
		this.shape9.render(f5);
		this.shape19.render(f5);
		this.shape5.render(f5);
		this.shape8.render(f5);
		this.shape14.render(f5);
		this.shape27.render(f5);
		this.shape7.render(f5);
		this.East.render(f5);
		this.North.render(f5);
		this.shape6.render(f5);
		this.shape16.render(f5);
		this.shape15.render(f5);
		this.shape23.render(f5);
		this.shape13.render(f5);
		this.shape26.render(f5);

		if (renderLid) {
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
}
