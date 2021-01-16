// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelredrager extends EntityModel<Entity> {
	private final ModelRenderer Rotation;
	private final ModelRenderer Body;
	private final ModelRenderer Leg1;
	private final ModelRenderer Leg2;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_arm;
	private final ModelRenderer shoulder_mushroom;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer Head;
	private final ModelRenderer head_mushroom;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer roots;
	private final ModelRenderer roots2_r1;

	public Modelredrager() {
		textureWidth = 128;
		textureHeight = 128;

		Rotation = new ModelRenderer(this);
		Rotation.setRotationPoint(0.0F, 19.4F, -0.45F);

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		Rotation.addChild(Body);
		setRotationAngle(Body, 0.0F, 3.1416F, 0.0F);
		Body.setTextureOffset(92, 22).addBox(-6.0F, -15.4F, -3.55F, 12.0F, 5.0F, 6.0F, 0.0F, false);
		Body.setTextureOffset(80, 0).addBox(-8.0F, -26.4F, -4.55F, 16.0F, 11.0F, 8.0F, 0.0F, false);

		Leg1 = new ModelRenderer(this);
		Leg1.setRotationPoint(4.0F, -10.4F, -0.55F);
		Body.addChild(Leg1);
		Leg1.setTextureOffset(0, 109).addBox(-2.0F, 0.0F, -2.0F, 5.0F, 15.0F, 4.0F, 0.0F, false);
		Leg1.setTextureOffset(92, 103).addBox(-2.0F, 0.0F, -2.0F, 5.0F, 13.0F, 4.0F, 0.2F, true);

		Leg2 = new ModelRenderer(this);
		Leg2.setRotationPoint(-4.0F, -10.4F, -0.55F);
		Body.addChild(Leg2);
		Leg2.setTextureOffset(0, 109).addBox(-3.0F, 0.0F, -2.0F, 5.0F, 15.0F, 4.0F, 0.0F, true);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(8.0F, -23.4F, -0.55F);
		Body.addChild(right_arm);
		right_arm.setTextureOffset(110, 103).addBox(0.0F, -4.0F, -2.0F, 5.0F, 21.0F, 4.0F, 0.0F, false);
		right_arm.setTextureOffset(92, 103).addBox(0.0F, -4.0F, -2.0F, 5.0F, 21.0F, 4.0F, 0.2F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(-8.0F, -23.4F, -0.55F);
		Body.addChild(left_arm);
		left_arm.setTextureOffset(110, 103).addBox(-5.0F, -4.0F, -2.0F, 5.0F, 21.0F, 4.0F, 0.0F, true);

		shoulder_mushroom = new ModelRenderer(this);
		shoulder_mushroom.setRotationPoint(-3.0F, -1.0F, -1.0F);
		left_arm.addChild(shoulder_mushroom);
		setRotationAngle(shoulder_mushroom, 0.0F, -0.48F, 0.0F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		shoulder_mushroom.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.7854F, 0.0F);
		cube_r1.setTextureOffset(0, 42).addBox(-3.7456F, -12.0F, -0.653F, 10.0F, 12.0F, 0.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		shoulder_mushroom.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.7854F, 0.0F);
		cube_r2.setTextureOffset(0, 42).addBox(-4.347F, -12.0F, 1.2544F, 10.0F, 12.0F, 0.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -26.9F, 0.825F);
		Body.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-6.0F, -8.5F, -6.375F, 12.0F, 9.0F, 10.0F, 0.0F, false);
		Head.setTextureOffset(0, 20).addBox(-5.0F, -7.5F, 3.625F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		head_mushroom = new ModelRenderer(this);
		head_mushroom.setRotationPoint(0.0F, -8.5F, -1.375F);
		Head.addChild(head_mushroom);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		head_mushroom.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, -0.7854F, 0.0F);
		cube_r3.setTextureOffset(0, 30).addBox(-5.0F, -12.0F, 0.0F, 10.0F, 12.0F, 0.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		head_mushroom.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.7854F, 0.0F);
		cube_r4.setTextureOffset(0, 30).addBox(-5.0F, -12.0F, 0.0F, 10.0F, 12.0F, 0.0F, 0.0F, false);

		roots = new ModelRenderer(this);
		roots.setRotationPoint(0.0F, 4.6F, 0.45F);
		Body.addChild(roots);
		roots.setTextureOffset(22, 21).addBox(-6.0F, -52.0F, 0.0F, 12.0F, 12.0F, 0.0F, 0.0F, false);

		roots2_r1 = new ModelRenderer(this);
		roots2_r1.setRotationPoint(0.0F, -40.0F, 0.0F);
		roots.addChild(roots2_r1);
		setRotationAngle(roots2_r1, 0.0F, 1.5708F, 0.0F);
		roots2_r1.setTextureOffset(20, 21).addBox(-7.0F, -12.0F, 0.0F, 12.0F, 12.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		Rotation.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.Leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.Leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.Rotation.rotateAngleZ = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}