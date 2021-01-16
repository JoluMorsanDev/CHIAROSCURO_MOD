// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelpsyche_warper extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer main_body;
	private final ModelRenderer tail;
	private final ModelRenderer cube_r1;
	private final ModelRenderer head;
	private final ModelRenderer antennae;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_arm;

	public Modelpsyche_warper() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);

		main_body = new ModelRenderer(this);
		main_body.setRotationPoint(0.0F, -15.0F, 0.0F);
		body.addChild(main_body);
		main_body.setTextureOffset(0, 29).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -3.0F, -1.0F);
		body.addChild(tail);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -2.0F, 0.0F);
		tail.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.4363F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(0, 16).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 3.0F, 10.0F, -0.01F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-6.0F, -21.0F, -5.0F, 12.0F, 6.0F, 10.0F, 0.0F, false);

		antennae = new ModelRenderer(this);
		antennae.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(antennae);
		antennae.setTextureOffset(0, 0).addBox(3.0F, -29.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
		antennae.setTextureOffset(0, 0).addBox(-5.0F, -29.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
		antennae.setTextureOffset(0, 51).addBox(-7.0F, -32.0F, 0.0F, 14.0F, 7.0F, 0.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(cube_r4);
		cube_r4.setTextureOffset(42, 16).addBox(-2.0F, -29.0F, -3.0F, 4.0F, 8.0F, 0.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(3.0F, -21.0F, 3.0F);
		head.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.6109F, 0.0F);
		cube_r2.setTextureOffset(42, 16).addBox(-2.0F, -8.0F, 0.0F, 4.0F, 8.0F, 0.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-3.0F, -21.0F, 3.0F);
		head.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, -0.6109F, 0.0F);
		cube_r3.setTextureOffset(42, 16).addBox(-2.0F, -8.0F, 0.0F, 4.0F, 8.0F, 0.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(4.0F, 12.0F, 0.0F);
		left_arm.setTextureOffset(28, 32).addBox(0.0F, -1.0F, -11.0F, 3.0F, 3.0F, 11.0F, 0.0F, false);
		left_arm.setTextureOffset(30, 0).addBox(0.0F, 2.0F, -15.0F, 3.0F, 0.0F, 4.0F, 0.0F, false);
		left_arm.setTextureOffset(30, 4).addBox(0.0F, -1.0F, -15.0F, 3.0F, 0.0F, 4.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-4.0F, 12.0F, 0.0F);
		right_arm.setTextureOffset(25, 18).addBox(-3.0F, -1.0F, -11.0F, 3.0F, 3.0F, 11.0F, 0.0F, false);
		right_arm.setTextureOffset(30, 0).addBox(-3.0F, 2.0F, -15.0F, 3.0F, 0.0F, 4.0F, 0.0F, false);
		right_arm.setTextureOffset(30, 4).addBox(-3.0F, -1.0F, -15.0F, 3.0F, 0.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
		right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}