// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelwarper_model extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer tentacles;
	private final ModelRenderer eyes;
	private final ModelRenderer bigT_left2;
	private final ModelRenderer cube;
	private final ModelRenderer bigT_right2;
	private final ModelRenderer cube2;
	private final ModelRenderer bb_main;
	private final ModelRenderer cube_r1;

	public Modelwarper_model() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(6.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-13.0F, -24.0F, -7.0F, 14.0F, 3.0F, 13.0F, 0.0F, false);
		body.setTextureOffset(0, 16).addBox(-12.0F, -31.0F, -6.0F, 12.0F, 7.0F, 11.0F, 0.0F, false);

		tentacles = new ModelRenderer(this);
		tentacles.setRotationPoint(-1.0F, 23.0F, 2.5F);
		tentacles.setTextureOffset(14, 27).addBox(-2.0F, -21.0F, -6.5F, 0.0F, 21.0F, 7.0F, 0.0F, false);
		tentacles.setTextureOffset(0, 27).addBox(4.0F, -21.0F, -6.5F, 0.0F, 21.0F, 7.0F, 0.0F, false);
		tentacles.setTextureOffset(42, 46).addBox(-2.5F, -21.0F, -6.0F, 7.0F, 21.0F, 0.0F, 0.0F, false);
		tentacles.setTextureOffset(28, 46).addBox(-2.5F, -21.0F, 0.0F, 7.0F, 21.0F, 0.0F, 0.0F, false);

		eyes = new ModelRenderer(this);
		eyes.setRotationPoint(0.0F, 24.0F, 0.0F);

		bigT_left2 = new ModelRenderer(this);
		bigT_left2.setRotationPoint(5.5F, 4.0F, -4.0F);

		cube = new ModelRenderer(this);
		cube.setRotationPoint(0.0F, 0.0F, 0.0F);
		bigT_left2.addChild(cube);
		setRotationAngle(cube, 0.0F, 0.0F, -0.0873F);
		cube.setTextureOffset(0, 21).addBox(-2.5F, 8.0F, 0.5F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		cube.setTextureOffset(4, 0).addBox(-2.0F, -2.0F, 1.0F, 1.0F, 10.0F, 1.0F, 0.0F, false);

		bigT_right2 = new ModelRenderer(this);
		bigT_right2.setRotationPoint(-5.5F, 4.0F, -4.0F);

		cube2 = new ModelRenderer(this);
		cube2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bigT_right2.addChild(cube2);
		setRotationAngle(cube2, 0.0F, 0.0F, 0.0873F);
		cube2.setTextureOffset(0, 16).addBox(0.5F, 8.0F, 0.5F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		cube2.setTextureOffset(0, 0).addBox(1.0F, -2.0F, 1.0F, 1.0F, 10.0F, 1.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(46, 20).addBox(0.0F, -41.0F, -5.5F, 0.0F, 10.0F, 10.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(1.0F, -31.0F, -0.5F);
		bb_main.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 1.5708F, 0.0F);
		cube_r1.setTextureOffset(46, 20).addBox(0.0F, -10.0F, -6.0F, 0.0F, 10.0F, 10.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacles.render(matrixStack, buffer, packedLight, packedOverlay);
		eyes.render(matrixStack, buffer, packedLight, packedOverlay);
		bigT_left2.render(matrixStack, buffer, packedLight, packedOverlay);
		bigT_right2.render(matrixStack, buffer, packedLight, packedOverlay);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.body.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.body.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}